import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Hospital {

    private String hospitalName;
    private List<Doctor> doctors;
    private List<Patient> patients;
    private Map<Symptom, Specialty> symptomToSpecialty; // matching symptom and specialty
    private Map<Doctor, List<Patient>> patientMap; // matching doctors with patients
    private Map<Specialty, List<Doctor>> doctorMap; // return doctors based on speciality
    public static final String JSON_FILE = "myHospital.json";

    public Hospital(String hospitalName) {
        this.hospitalName = hospitalName;
        this.doctors = new LinkedList<Doctor>();
        this.patients = new LinkedList<Patient>();
        this.symptomToSpecialty = new HashMap<Symptom, Specialty>();
        symptomToSpecialty.put(Symptom.ATOPY, Specialty.DERMATOLOGY);
        symptomToSpecialty.put(Symptom.CYSTITIS, Specialty.UROLOGY);
        symptomToSpecialty.put(Symptom.DWARFISM, Specialty.PEDIATRICS);
        symptomToSpecialty.put(Symptom.OSTEOARTHRITIS, Specialty.ORTHOPEDICS);
        symptomToSpecialty.put(Symptom.EPILEPSY, Specialty.NEUROLOGY);
        patientMap = new HashMap<>();
        doctorMap = new HashMap<>();
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        if (patients.isEmpty()) {
            return null;
        }
        return patients;
    }

    public void addDoctor(Doctor doctor) {
        Specialty specialty = doctor.getSpecialty();
        this.doctors.add(doctor);
        List<Doctor> doctorList = new LinkedList<Doctor>();
        doctorList.add(doctor);
        doctorMap.put(specialty, doctorList);
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
        Specialty specialty = findSpecialty(patient.getSymptom()); // find specialty based on patient symptom
        Doctor doctor = findDoctor(specialty); // find doctor based on specialty

        if (patientMap.containsKey(doctor)) { // handling a doctor who needs to take care of more than one patient
            List<Patient> patientList = patientMap.get(doctor);
            patientList.add(patient);
            if (doctor != null) { // if the key is not null, add it to patientMap
                patientMap.put(doctor, patientList);
            }
        } else { // handling a doctor who needs to take care of new patient
            List<Patient> newPatientList = new LinkedList<Patient>();
            newPatientList.add(patient);
            if (doctor != null) { // if the key is not null, add it to patientMap
                patientMap.put(doctor, newPatientList);
            }
        }
    }

    public Doctor findDoctor(Specialty specialty) {
        List<Doctor> doctorList = doctorMap.get(specialty);
        if (isEmpty(doctorList)) {
            return null;
        }
        for (Doctor doctor : doctorList) {
            return doctor;
        }
        return null;
    }

    public Specialty findSpecialty(Symptom symptom) {
        return symptomToSpecialty.get(symptom);
    }

    public void returnSpecialty() {
        for (Map.Entry<Symptom, Specialty> entry : symptomToSpecialty.entrySet()) {
            System.out.println("Specialty: " + entry.getValue().toString());
        }
    }

    public void returnSymptom() {
        for (Map.Entry<Symptom, Specialty> entry : symptomToSpecialty.entrySet()) {
            System.out.println("Symptom: " + entry.getKey());
        }
    }


    public Map<Symptom, Specialty> getSymptomToSpecialty() {
        return symptomToSpecialty;
    }

    public Map<Doctor, List<Patient>> getPatientMap() {
        return patientMap;
    }

    public Map<Specialty, List<Doctor>> getDoctorMap() {
        return doctorMap;
    }


    // added
    public void treatPatient(Patient patient) {
        // once user enters patient, check if the patient is in a list
        if (!patients.contains(patient) || patients.isEmpty()) {
            System.out.println("Cannot treat patient who does not exist");
        }

        // check if the patient is a part of patients
        // to do this, we have to get a right doctor
        // a right doctor can be found by matching doctor's specialty with patient's symptom

        Specialty specialty = findSpecialty(patient.getSymptom()); // find specialty based on patient's symptom
        Doctor doctor = findDoctor(specialty); // find doctor based on specialty
        List<Patient> patientList = patientMap.get(doctor);

        if (patientList.contains(patient)) {
            // then, we can treat the patient
            // based on doctor's healingPower and patient's healing index
            if (patient.getHealthIndex() > 0 && patient.getHealthIndex() < 100) {
                System.out.print("We've found a match: ");
                System.out.println("doctor '" + doctor.getDoctorName() + "' is treating patient '" + patient.getName() + "'" + " with symptom of " + patient.getSymptom());
                int healingPower = doctor.getHealingPower();

                System.out.println("patient health index before treatment: " + patient.getHealthIndex());
                System.out.println("(doctor is treating patients)");
                patient.setHealthIndex(healingPower);
                System.out.println("patient health index after treatment: " + patient.getHealthIndex());
                System.out.println();
            } else {
                System.out.println("Based on patient's health index of " + patient.getHealthIndex() + " the treatment is not needed");
                removePatient(patients, patient);
                System.out.println("(removed the patient from the hospital)");
                System.out.println();
            }
        } else {
            System.out.println("there is no doctor who can treat this patient in this hospital.");
            System.out.println("Please enter the name of other patient(s).");
            treatPatient(patient);
        }
    }

    // added
    public void removePatient(List<Patient> patients, Patient patient) {
        patients.remove(patient);
    }


    public Patient findPatientByName(String name) {
        for (Patient patient: patients) {
//            System.out.println("printing patient.getName() " + patient.getName());
            if (patient.getName().equals(name))
                return patient;
        }
        return null;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    @Override
    public String toString() {
        return this.hospitalName;
    }

    public void printHospitalInfo() {
        System.out.println();
        System.out.println("=========== PRINTING HOSPITAL INFO ===========");
        System.out.println("Hospital Name: " + getHospitalName());
        System.out.println("Available Specialties: \n" + Arrays.asList(Specialty.values()));
        System.out.println("Available Symptoms: \n" + Arrays.asList(Symptom.values()));
        System.out.println("---------------------------");
        System.out.println("Doctors in the hospital: ");
        System.out.println(getDoctors());
        System.out.println("---------------------------");
        System.out.println("Patients in the hospital: ");
        System.out.println(getPatients());
        System.out.println("---------------------------");
        System.out.println("Matching patient(s) with doctor(s) based on the symptom and specialty:");
        System.out.println(patientMap);
    }


    public void writeJson(Hospital hospital) throws JsonProcessingException {
        String json = new ObjectMapper().writeValueAsString(hospital);
        System.out.println(json);
        //Write JSON to file.
        try {
            FileReader.writeToFile(JSON_FILE, json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Hospital readJson() throws JsonProcessingException {
        String json = FileReader.readFromFile(JSON_FILE);
        try {
            return new ObjectMapper().readValue(new File(JSON_FILE), Hospital.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

