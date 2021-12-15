package use;

import logger.Logger;
import models.Lection;
import services.LectionService;
import services.ResourceService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UseLectionService {
    private final Scanner scanner = new Scanner(System.in);
    private final LectionService lectionService = new LectionService();
    private final UseResourceService useResourceService = new UseResourceService();

    public void showAllLections() {
        for (Lection lection : lectionService.getLections()) {
            Logger.info(getClass().getName(),
                String.format("%s: %s%n", lection.getName(), lection.getResources()));
        }
    }

    public void addNewLection() {
        String name = getNameOfLection();
        useResourceService.addResources(getCountOfResources());
        ResourceService resourceService = new ResourceService();
        lectionService.addLection(name, resourceService.getResources());
    }

    public void deleteLectionById() {
        lectionService.deleteLection(getIdOfLection());
    }

    public void showLectionById() {
        Lection lection = lectionService.getLection(getIdOfLection());
        if (lection != null) {
            Logger.info(getClass().getName(), lection.getName());
        }
    }

    public void exit() {
        scanner.close();
        useResourceService.closeScanner();
        System.exit(0);
    }

    private String getNameOfLection() {
        System.out.print("Input name of lection: ");
        return scanner.nextLine();
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
}
