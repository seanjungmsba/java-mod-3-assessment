package builderservices;

import hospital.Doctor;
import hospital.Specialty;
import ioservices.Logger;
import ioservices.ScannerInputService;

public class DoctorBuilderService {
    private final ScannerInputService scannerUserInputService;
    private final Logger logger;

    public DoctorBuilderService(ScannerInputService scannerUserInputService) {
        this.scannerUserInputService = scannerUserInputService;
        logger = Logger.getInstance();
    }

    public Doctor buildDoctor() {
        String name = scannerUserInputService.getUserInput("Enter name of doctor: ");
        printSpecialties();
        int specialtyInput = scannerUserInputService.getUserInput("Select specialty: ", 1, 4);
        Specialty specialty = null;
        switch (specialtyInput) {
            case 1:
                specialty = Specialty.DERMATOLOGY;
                break;
            case 2:
                specialty = Specialty.PHYSICAL_MEDICINE;
                break;
            case 3:
                specialty = Specialty.NEUROLOGY;
                break;
            case 4:
                specialty = Specialty.SURGERY;
                break;
            default:
                logger.log("Invalid department number.");
        }
        int maxHealingPower = scannerUserInputService.getUserInput("Enter max healing capability of doctor: ",
                0, 100);
        return new Doctor(name, specialty, maxHealingPower);
    }

    private void printSpecialties() {
        logger.log("1. DERMATOLOGY");
        logger.log("2. PHYSICAL_MEDICINE");
        logger.log("3. NEUROLOGY");
        logger.log("4. SURGERY");
    }

}
