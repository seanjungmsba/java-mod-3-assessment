import hospital.Doctor;
import hospital.Hospital;
import hospital.Patient;
import hospital.Specialty;
import ioservices.JsonIOService;
import ioservices.Logger;
import ioservices.ScannerInputService;

import java.util.ArrayList;
import java.util.List;

public class HospitalRunner {

    private final String fileName = "MyHospital.json";
    private final ScannerInputService scannerInputService;
    private final JsonIOService jsonIOService;
    private final Logger logger;
    private Hospital hospital;
    public HospitalRunner() {
        this.scannerInputService = new ScannerInputService();
        jsonIOService = new JsonIOService(fileName);
        hospital = new Hospital(scannerInputService);
        logger = Logger.getInstance();
    }

    public static void main(String[] args) {
        HospitalRunner hospitalRunner = new HospitalRunner();
        hospitalRunner.showMenu();
    }

    public boolean restoreFile() {
        return scannerInputService.getUserInput("Restore list from previous file? (y/n) ").equals("y");
    }

    public int createMenu() {
        logger.log("1. Add doctor(s)");
        logger.log("2. Add patient(s)");
        logger.log("3. Perform treatment");
        logger.log("4. Print Hospital");
        logger.log("5. Save and Exit");
        int selection = scannerInputService.getUserInput("Enter a selection from the menu above: ",
                1, 5);
        return selection;
    }

    public void showMenu() {
        logger.log("Welcome to Sean's Hospital");
        if (jsonIOService.fileExists()) {
            if (restoreFile()) {
                hospital = jsonIOService.readFromFile();
                hospital.initialize(scannerInputService);
            }
        }
        jsonIOService.resetFile();

        boolean running = true;
        while (running) {
            int selection = createMenu();
            switch (selection) {
                case 1:
                    hospital.addDoctors();
                    break;

                case 2:
                    hospital.enterPatients();
                    break;

                case 3:
                    List<Specialty> availSpecialties = new ArrayList<>(hospital.getDepartment().keySet());
                    if (availSpecialties.isEmpty()) {
                        logger.log("No departments available");
                        break;
                    }
                    for (int i = 0; i < availSpecialties.size(); i++) {
                        logger.log((i + 1) + ": " + availSpecialties.get(i));
                    }
                    Specialty specialty = availSpecialties.get(scannerInputService.getUserInput(
                            "Enter speciality selection: ", 1, availSpecialties.size()) - 1);

                    List<Doctor> availDoctors = hospital.getDepartment().get(specialty);
                    if (availDoctors.isEmpty()) {
                        logger.log("No doctors available");
                        break;
                    }
                    for (int j = 0; j < availDoctors.size(); j++) {
                        logger.log((j + 1) + ": " + availDoctors.get(j).getName());
                    }
                    Doctor doctor = availDoctors.get(scannerInputService.getUserInput(
                            "Enter doctor selection: ", 1, availDoctors.size()) - 1);

                    List<Patient> patients = doctor.getPatientList();
                    if (patients.isEmpty()) {
                        logger.log("No patients available");
                        break;
                    }
                    for (int k = 0; k < patients.size(); k++) {
                        logger.log((k + 1) + ": " + patients.get(k).getName());
                    }
                    Patient patient = patients.get(scannerInputService.getUserInput(
                            "Enter patient selection: ", 1, patients.size()) - 1);
                    doctor.treatPatient(patient);
                    break;

                case 4:
                    hospital.printHospital();
                    break;

                case 5:
                    jsonIOService.writeToFile(hospital);
                    running = false;
                    break;

                default:
                    logger.log("Invalid selection.");

            }

        }
        scannerInputService.close();
    }

}