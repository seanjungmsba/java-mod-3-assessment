import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class HospitalWorld {

    static Hospital hospital;

    /* HospitalWorld main method */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            showBeginningOptions(scanner);
        } catch (Exception e) {
            log(e.toString());
        }
    }

    private static void showBeginningOptions(Scanner sc) throws JsonProcessingException {
        System.out.println("ENTER [1] to open a hospital fresh \nENTER [2] to read-in from existing hospital");
        int userOptions = getInputInt(sc, 1, 2);
        switch (userOptions) {
            case 1:
                try (Scanner scanner = new Scanner(System.in);) {
                    enteringHospitalInfo(scanner);
                    enteringDoctorInfo(scanner);
                    enteringPatientInfo(scanner);
                    treatingPatientOption(scanner);
                    showOptions(scanner);
                } catch (Exception e) {
                    log(e.toString());
                } finally {
                    break;
                }
            case 2:
                try {
                    hospital = Hospital.readJson();
                    showOptions(sc);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("There exist no Hospital file");
                    showBeginningOptions(sc);
                } finally {
                    break;
                }
        }
    }
    private static void showOptions(Scanner sc) throws JsonProcessingException {
        System.out.println("ENTER [1] to finish the program with Hospital info \nENTER [2] to keep treating patient \nENTER [3] to export as JSON file");
        int userOptions = getInputInt(sc, 1, 3);
        switch (userOptions) {
            case 1:
                hospital.printHospitalInfo();
                break;
            case 2:
                treatingPatientOption(sc);
                showOptions(sc);
                break;
            case 3:
                try {
                    hospital.writeJson(hospital);
                } catch (JsonProcessingException jsonProcessingException) {
                    jsonProcessingException.printStackTrace();
                }
                break;
        }
    }
    private static void enteringHospitalInfo(Scanner sc) {
        System.out.println("Welcome to virtual hospital World!");
        System.out.print("To start off, please enter the name of the hospital: ");
        String hospitalName = getInputString(sc);
        hospital = new Hospital(hospitalName);
    }

    private static void enteringDoctorInfo(Scanner sc) {
        System.out.println("=============== ENTERING DOCTOR INFO ===============");
        System.out.print("How many doctor(s) do you want in '" + hospital + "' hospital? ");
        int numberOfDoctors = getInputInt(sc, 1, 2147483647);
        for (int i = 0; i < numberOfDoctors; i++) {
            int num = i + 1;
            System.out.println("---------- Doctor " + num + " ----------");
            Doctor doc = createDoctor(sc);
            hospital.addDoctor(doc);
        }
    }

    private static void enteringPatientInfo(Scanner sc) {
        System.out.println("=============== ENTERING PATIENT INFO ===============");
        System.out.print("How many patient(s) do you want in '" + hospital + "' hospital? ");

        int numberOfPatients = getInputInt(sc, 1, 2147483647);

        for (int i = 0; i < numberOfPatients; i++) {
            int num = i + 1;
            System.out.println("---------- Patient " + num + " ----------");
            hospital.addPatient(createPatient(sc));
        }
    }
    private static void treatingPatientOption(Scanner sc) throws JsonProcessingException {
        System.out.println("-------------- PRINTING ALL PATIENTS --------------");
        try {
            if (hospital.getPatients() == null) {
                System.out.println("There is no patient in this hospital!");
                showOptions(sc);
            }
            System.out.println(hospital.getPatients());
            System.out.print("Enter the name of the patient you'd like to treat: ");
            Patient patientToTreat = getPatientFromUserInput(sc);
            hospital.treatPatient(patientToTreat);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }

    }

    /* creating a Patient object from user input */
    private static Patient createPatient(Scanner sc) {
        String name;
        int symptomIndex;
        Symptom symptom;
        HospitalWorld.log("Enter the name of the patient: ");
        name = HospitalWorld.getInputString(sc);

        HospitalWorld.log("What is the symptom the patient is experiencing? ");
        HospitalWorld.log("\n");
        for (Symptom s : Symptom.values()) {
            System.out.println(s.getIndex() + ": " + s);
        }

        HospitalWorld.log("Enter the symptom of the patient by the number (1-5): ");
        symptomIndex = HospitalWorld.getInputInt(sc, 1, 5);
        symptom = Symptom.values()[symptomIndex - 1];
        return new Patient(name, symptom);
    }

    /* creating a Doctor object from user input */
    private static Doctor createDoctor(Scanner sc) {
        String name;
        Specialty specialty;
        HospitalWorld.log("Enter the name of the doctor: ");
        name = HospitalWorld.getInputString(sc);
        HospitalWorld.log("Which of the following specialty the doctor is specialized in? ");
        HospitalWorld.log("\n");
        for (Specialty s : Specialty.values()) {
            System.out.println(s.getIndex() + ": " + s);
        }
        HospitalWorld.log("Enter the specialty of the doctor by the number (1-5): ");
        int specialIndex = HospitalWorld.getInputInt(sc, 1, 5);
        specialty = Specialty.values()[specialIndex - 1];
        return new Doctor(name, specialty);
    }

    /* exception handling from user input for integer values */
    public static int getInputInt(Scanner sc, int min, int max) {
        try {
            String s = sc.nextLine();
            int i = Integer.parseInt(s);
            if (!(i >= min && i <= max)) {
                log("ERROR: Re-enter the valid input: ");
                return getInputInt(sc, min, max);
            }
            return i;
        } catch (Exception e) {
            log("ERROR: Re-enter the valid input: ");
            return getInputInt(sc, min, max);
        }
    }

    /* exception handling from user input for string values */
    public static String getInputString(Scanner sc) {
        try {
            String s = sc.nextLine();
            if (s.equals("")) {
                System.out.print("ERROR: ENTER THE VALID INPUT: ");
                return getInputString(sc);
            }
            return s;
        } catch (Exception e) {
            System.out.print("ERROR: ENTER THE VALID INPUT: ");
            return getInputString(sc);
        }
    }

    /* exception handling from user input for Patient values */
    public static Patient getPatientFromUserInput(Scanner sc) {
        try {
            String s = sc.nextLine();
            if (s.equals("")) {
                System.out.print("ERROR: ENTER THE VALID PATIENT: ");
                return getPatientFromUserInput(sc);
            }
            if (hospital.findPatientByName(s) == null) {
                System.out.print("ERROR: ENTER THE VALID PATIENT: ");
                return getPatientFromUserInput(sc);
            }
            Patient p = hospital.findPatientByName(s);
            return p;
        } catch (Exception e) {
            System.out.print("ERROR: ENTER THE VALID PATIENT: ");
            return getPatientFromUserInput(sc);
        }
    }

    // System.out.print() helper
    public static void log(String str) {
        System.out.print(str);
    }


}