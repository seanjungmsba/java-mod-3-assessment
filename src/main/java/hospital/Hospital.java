package hospital;

import builderservices.DoctorBuilderService;
import builderservices.PatientBuilderService;
import ioservices.Logger;
import ioservices.ScannerInputService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hospital {


    private Map<Specialty, List<Doctor>> department;

    private DoctorBuilderService doctorBuilderService;
    private PatientBuilderService patientBuilderService;

    private ScannerInputService scannerInputService;
    private Logger logger;

    public Hospital() {
    }

    public Hospital(ScannerInputService scannerInputService) {
        this.scannerInputService = scannerInputService;
        department = new HashMap<>();
        doctorBuilderService = new DoctorBuilderService(scannerInputService);
        patientBuilderService = new PatientBuilderService(scannerInputService);
        logger = Logger.getInstance();
    }

    public void initialize(ScannerInputService scannerInputService) {
        this.scannerInputService = scannerInputService;
        doctorBuilderService = new DoctorBuilderService(scannerInputService);
        patientBuilderService = new PatientBuilderService(scannerInputService);
        logger = Logger.getInstance();
    }

    public void addDoctors() {
        int numDoctors = scannerInputService.getUserInput("Enter number of doctors: ", 0,
                Integer.MAX_VALUE);
        for (int i = 0; i < numDoctors; i++) {
            logger.log("Input info for Doctor " + (i + 1));
            Doctor newDoctor = doctorBuilderService.buildDoctor();
            if (!department.containsKey(newDoctor.getSpecialty())) {
                department.put(newDoctor.getSpecialty(), new ArrayList<>());
            }
            department.get(newDoctor.getSpecialty()).add(newDoctor);
        }
    }

    public void enterPatients() {
        if (department.isEmpty()) {
            logger.log("No one is currently staffed. Add doctor first.");
            return;
        }
        int numPatients = scannerInputService.getUserInput("Enter number of patients: ", 0,
                Integer.MAX_VALUE);
        for (int i = 0; i < numPatients; i++) {
            logger.log("Input info for Patient " + (i + 1));
            Patient newPatient = patientBuilderService.buildPatient();
            if (!department.containsKey(newPatient.getAilment().getSpecialty())) {
                logger.log("No one is staffed to this department.  Please renter patient info.");
                i--;
            } else {
                int doctorIndWithLeastPatients = 0;
                int minPatients = Integer.MAX_VALUE;
                List<Doctor> doctorList = department.get(newPatient.getAilment().getSpecialty());
                for (int j = 0; j < doctorList.size(); j++) {
                    if (doctorList.get(j).getPatientList().size() < minPatients) {
                        doctorIndWithLeastPatients = j;
                        minPatients = doctorList.get(j).getPatientList().size();
                    }
                }
                Doctor availDoctor = department.get(newPatient.getAilment().getSpecialty())
                        .get(doctorIndWithLeastPatients);
                availDoctor.addPatient(newPatient);
            }
        }
    }

    public Map<Specialty, List<Doctor>> getDepartment() {
        return department;
    }

    public void setDepartment(Map<Specialty, List<Doctor>> department) {
        this.department = department;
    }

    public void printHospital() {
        logger.log("=========================");
        logger.log("==== Sean's Hospital ====");
        logger.log("=========================");

        logger.log(Specialty.DERMATOLOGY.name());
        printDepartment(Specialty.DERMATOLOGY);

        logger.log(Specialty.PHYSICAL_MEDICINE.name());
        printDepartment(Specialty.PHYSICAL_MEDICINE);

        logger.log(Specialty.NEUROLOGY.name());
        printDepartment(Specialty.NEUROLOGY);

        logger.log(Specialty.SURGERY.name());
        printDepartment(Specialty.SURGERY);
    }

    public void printDepartment(Specialty specialty) {
        if (department.containsKey(specialty)) {
            int numDoctors = department.get(specialty).size();
            logger.log("   Doctors:");
            for (int i = 0; i < numDoctors; i++) {
                Doctor doctor = department.get(specialty).get(i);
                logger.log("   " + doctor.getName());
                logger.log("       Patients:");
                for (Patient patient : doctor.getPatientList()) {
                    logger.log("       " + patient.printPatient());
                }
            }
        }
    }
}