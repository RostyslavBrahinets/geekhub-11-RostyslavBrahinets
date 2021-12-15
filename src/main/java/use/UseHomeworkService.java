package use;

import logger.Logger;
import services.HomeworkService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UseHomeworkService {
    private final Scanner scanner = new Scanner(System.in);

    public void addHomeworks(int countOfHomeworks) {
        HomeworkService homeworkService = new HomeworkService();

        for (int i = 0; i < countOfHomeworks; i++) {
            homeworkService.addHomework(
                getHomeworkTasks()
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
            Logger.warning(getClass().getName(), String.format("Count of %s is invalid", s));
            count = 0;
            scanner.nextLine();
        }

        return count;
    }

    private List<String> getHomeworkTasks() {
        List<String> tasks = new ArrayList<>();

        for (int i = 0; i < getCountOf("homeworks"); i++) {
            System.out.print("Input task: ");
            tasks.add(scanner.nextLine());
        }

        return tasks;
    }
}
