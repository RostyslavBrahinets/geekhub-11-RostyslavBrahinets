package menu;

import logger.Logger;
import logger.LoggerWithStorageInFile;
import logger.LoggerWithStorageInMemory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public abstract class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    protected Logger logger;

    protected Menu() {
        String applicationProperties = "Homework/domain/src/main/resources/application.properties";
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(applicationProperties)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String loggerStorageType = properties.getProperty("logger.storage.type");
        if (loggerStorageType.equals("file")) {
            logger = new LoggerWithStorageInFile();
        } else {
            logger = new LoggerWithStorageInMemory();
        }
    }

    protected abstract void runMenu();

    protected String getCommand() {
        System.out.print("\nInput command (1-4): ");
        return scanner.nextLine();
    }

    protected int getCount() {
        System.out.print("Input count: ");
        int count;

        try {
            count = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            logger.error(getClass().getName(), "Count is invalid");
            count = 0;
            scanner.nextLine();
        }

        return count;
    }

    protected String getFromScanner() {
        return scanner.nextLine();
    }

    protected List<String> getContacts() {
        System.out.println("\nContacts");
        List<String> contacts = new ArrayList<>();
        int count = getCount();
        for (int i = 0; i < count; i++) {
            System.out.print("Input contact: ");
            contacts.add(scanner.nextLine());
        }
        return contacts;
    }

    protected LocalDateTime getDeadLine() {
        try {
            System.out.println("Dead line");
            System.out.print("Input day: ");
            int day = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Input month: ");
            int month = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Input year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Input hour: ");
            int hour = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Input minute: ");
            int minute = scanner.nextInt();
            scanner.nextLine();
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (InputMismatchException e) {
            scanner.nextLine();
            logger.error(getClass().getName(), "Date is invalid");
        }

        return LocalDateTime.now();
    }

    protected int getId() {
        System.out.print("Input id: ");
        int id;

        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            id = -1;
            scanner.nextLine();
        }

        return id;
    }

    protected static void closeScanner() {
        scanner.close();
    }
}
