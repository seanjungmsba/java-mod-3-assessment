package hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Doctor {

    private final int MAX_FAIL_POWER = -10;
    private String name;
    private Specialty specialty;
    private List<Patient> patientList;
    private int maxHealingPower;

    public Doctor() {
    }

    public Doctor(String name, Specialty specialty, int maxHealingPower) {
        this.name = name;
        this.specialty = specialty;
        this.maxHealingPower = maxHealingPower;
        patientList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void addPatient(Patient patient) {
        patientList.add(patient);
    }

    public void removePatient(Patient patient) {
        patientList.remove(patient);
    }

    public Patient getPatient(int index) {
        return patientList.get(index);
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void treatPatient(Patient patient) {
        int maxIfCurable = (patient.getAilment().isCurable()) ? maxHealingPower : 0;
        int healingRange = ThreadLocalRandom.current().nextInt(MAX_FAIL_POWER, maxIfCurable + 1);
        patient.setHealthIndex(healingRange);
        if (patient.isDead() || patient.isHealed()) {
            removePatient(patient);
        }
    }
}