package use;

import logger.Logger;
import models.Homework;
import models.Lection;
import models.Person;
import services.HomeworkService;
import services.LectionService;
import services.PersonService;
import services.ResourceService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UseLectionService {
    private final Scanner scanner = new Scanner(System.in);
    private final LectionService lectionService = new LectionService();
    private final UseResourceService useResourceService = new UseResourceService();
    private final UsePersonService usePersonService = new UsePersonService();
    private final UseHomeworkService useHomeworkService = new UseHomeworkService();

    public void showAllLections() {
        for (Lection lection : lectionService.getLections()) {
            Logger.info(getClass().getName(),
                String.format("%s: %s; %s; %s %s; %s%n",
                    lection.getName(),
                    lection.getDescribe(),
                    lection.getResources(),
                    lection.getLecturer().getFirstName(),
                    lection.getLecturer().getLastName(),
                    lection.getHomework().getTasks()
                ));
        }
    }

    public void addNewLection() {
        String name = getNameOfLection();
        String describe = getDescribeOfLection();
        useResourceService.addResources(getCountOfResources());
        Person lecturer = getLecturer();
        Homework homework = getHomework();

        ResourceService resourceService = new ResourceService();
        lectionService.addLection(name, describe, resourceService.getResources(),
            lecturer, homework);
    }

    public void deleteLectionById() {
        lectionService.deleteLection(getIdOfLection());
    }

    public void showLectionById() {
        Lection lection = lectionService.getLection(getIdOfLection());
        if (lection != null) {
            Logger.info(getClass().getName(),
                String.format("%s: %s; %s; %s %s; %s%n",
                    lection.getName(),
                    lection.getDescribe(),
                    lection.getResources(),
                    lection.getLecturer().getFirstName(),
                    lection.getLecturer().getLastName(),
                    lection.getHomework().getTasks()
                ));
        }
    }

    public void exit() {
        scanner.close();
        useResourceService.closeScanner();
        usePersonService.closeScanner();
        useHomeworkService.closeScanner();
        System.exit(0);
    }

    private String getNameOfLection() {
        System.out.print("\nInput name of lection: ");
        return scanner.nextLine();
    }

    private String getDescribeOfLection() {
        System.out.print("Input describe of lection: ");
        return scanner.nextLine();
    }

    private int getCountOfResources() {
        System.out.print("Input count of resources: ");
        int count;

        try {
            count = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            Logger.warning(getClass().getName(), "Count of resources is invalid");
            count = 0;
            scanner.nextLine();
        }

        return count;
    }

    private Person getLecturer() {
        usePersonService.addPeople(1);
        return new PersonService().getPerson(0);
    }

    private Homework getHomework() {
        useHomeworkService.addHomeworks(1);
        return new HomeworkService().getHomework(0);
    }

    private int getIdOfLection() {
        System.out.print("Input id of lection: ");
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
}
