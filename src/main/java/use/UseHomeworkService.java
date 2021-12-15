package use;

import services.HomeworkService;

import java.util.ArrayList;
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

    private List<String> getHomeworkTasks() {
        System.out.print("\nInput count of tasks: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<String> tasks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.print("Input task: ");
            tasks.add(scanner.nextLine());
        }

        return tasks;
    }
}
