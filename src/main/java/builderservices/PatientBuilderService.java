package builderservices;

import hospital.Ailment;
import hospital.Patient;
import ioservices.Logger;
import ioservices.ScannerInputService;

public class PatientBuilderService {

    private final ScannerInputService scannerUserInputService;
    private final Logger logger;

    public PatientBuilderService(ScannerInputService scannerUserInputService) {
        this.scannerUserInputService = scannerUserInputService;
        logger = Logger.getInstance();
    }

    public Patient buildPatient() {
        String name = scannerUserInputService.getUserInput("Enter name of patient: ");
        printAilements();
        int ailmentInput = scannerUserInputService.getUserInput("Select ailment: ", 1, 7);
        Ailment ailment = null;
        switch (ailmentInput) {
            case 1:
                ailment = Ailment.RASH;
                break;
            case 2:
                ailment = Ailment.SCARRING;
                break;
            case 3:
                ailment = Ailment.MUSCLE_ATROPHY;
                break;
            case 4:
                ailment = Ailment.PARKINSON;
                break;
            case 5:
                ailment = Ailment.BRAIN_TUMOR;
                break;
            case 6:
                ailment = Ailment.BULLET_WOUND;
                break;
            case 7:
                ailment = Ailment.BLOCKED_ARTERY;
                break;
            default:
                logger.log("Invalid ailment selection.");
        }
        return new Patient(name, ailment);
    }

    private void printAilements() {
        logger.log("1. RASH");
        logger.log("2. SCARRING");
        logger.log("3. MUSCLE ATROPHY");
        logger.log("4. PARKINSON");
        logger.log("5. BRAIN TUMOR");
        logger.log("6. BULLET WOUND");
        logger.log("7. BLOCKED ARTERY");
    }
}