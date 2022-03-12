package menu;

import logger.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class Menu {
    protected static final String COMMAND_NOT_FOUND = "Command not found";
    private static final Scanner scanner = new Scanner(System.in);
    protected static Logger logger;

    protected Menu() {
        logger = new Logger();
    }

    protected static void closeScanner() {
        scanner.close();
    }

    protected abstract void runMenu() throws SQLException, IOException;

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
        } catch (Exception e) {
            logger.error(getClass().getName(), "Count is invalid");
            count = 0;
            scanner.nextLine();
        }

        return count;
    }

    protected String getFromScanner() {
        return scanner.nextLine();
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            id = -1;
            scanner.nextLine();
        }

        return id;
    }
}
