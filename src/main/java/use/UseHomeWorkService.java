package use;

import logger.Logger;
import services.HomeWorkService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UseHomeWorkService {
    private final Scanner scanner = new Scanner(System.in);

    public void addHomeWorks() {
        HomeWorkService homeWorkService = new HomeWorkService();
        int count = getCountOf("home works");

        for (int i = 0; i < count; i++) {
            homeWorkService.addHomeWork(
                getHomeWorkTask()
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
}
