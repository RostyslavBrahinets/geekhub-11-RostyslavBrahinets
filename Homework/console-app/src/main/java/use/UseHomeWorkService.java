package use;

import logger.Logger;
import services.HomeWorkService;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UseHomeWorkService {
    private final Scanner scanner = new Scanner(System.in);

    public void addHomeWorks() {
        HomeWorkService homeWorkService = new HomeWorkService();
        int count = getCountOf("home works");

        for (int i = 0; i < count; i++) {
            homeWorkService.addHomeWork(
                getHomeWorkTask(),
                getDeadLine()
            );
        }
    }

    public void closeScanner() {
        scanner.close();
    }

    private int getCountOf(String s) {
        System.out.printf("Input count of %s: ", s);
        int count;

        try {
            count = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            Logger.error(getClass().getName(), String.format("Count of %s is invalid", s));
            count = 0;
            scanner.nextLine();
        }

        return count;
    }

    private String getHomeWorkTask() {
        System.out.print("Input task: ");
        return scanner.nextLine();
    }

    private LocalDateTime getDeadLine() {
        System.out.println("Dead line");
        System.out.print("Input year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Input month: ");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Input day: ");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Input hour: ");
        int hour = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Input minute: ");
        int minute = scanner.nextInt();
        scanner.nextLine();

        return LocalDateTime.of(year, month, day, hour, minute);
    }
}
