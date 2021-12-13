package use;

import exceptions.ValidationException;
import logger.Logger;
import models.Lection;
import services.LectionService;
import services.ResourceService;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UseLectionService {
    private final Scanner scanner = new Scanner(System.in);
    LectionService lectionService = new LectionService();

    public void showAllLections() {
        for (Lection lection : lectionService.getLections()) {
            System.out.printf("%s: %s%n", lection.getName(), Arrays.toString(lection.getResources()));
        }
    }

    public void addNewLection() {
        try {
            String name = getNameOfLection();
            UseResourceService useResourceService = new UseResourceService();
            useResourceService.addResources();
            ResourceService resourceService = new ResourceService();
            Lection lection = new Lection(name, resourceService.getResources());
            lectionService.addLection(lection);
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), "Invalid type of resource", e);
        }
    }

    public void deleteLectionByNumber() {
        lectionService.deleteLection(getNumberOfLection());
    }

    public void showLectionByNumber() {
        Lection lection = lectionService.getLection(getNumberOfLection());
        if (lection != null) {
            System.out.println(lection.getName());
        }
    }

    public void exit() {
        scanner.close();
        System.exit(0);
    }

    private String getNameOfLection() {
        System.out.print("Input name of lection: ");
        return scanner.nextLine();
    }

    private int getNumberOfLection() {
        System.out.print("Input number of lection: ");
        int number;

        try {
            number = scanner.nextInt();
        } catch (InputMismatchException e) {
            number = -1;
            scanner.nextLine();
        }

        return number;
    }
}
