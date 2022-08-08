package ioservices;


import java.util.Scanner;

public class ScannerInputService {
    private final Scanner scanner;
    private final Logger logger;

    public ScannerInputService() {
        this.scanner = new Scanner(System.in);
        logger = Logger.getInstance();
    }

    public String getUserInput(String prompt) {
        logger.log(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return getUserInput(prompt);
        }
        return input;
    }

    public int getUserInput(String prompt, int lowerBound, int upperBound) {
        logger.log(prompt);
        int input = -1;
        try {
            input = Integer.parseInt(scanner.nextLine());
            while (input < lowerBound || input > upperBound) {
                logger.log("Enter valid selection in range");
                input = Integer.parseInt(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Enter a valid input.");
            input = getUserInput(prompt, lowerBound, upperBound);
        }
        return input;
    }

    public void close() {
        scanner.close();
    }
}
