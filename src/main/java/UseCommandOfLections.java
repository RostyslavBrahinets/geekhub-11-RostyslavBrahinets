import exceptions.ValidationException;
import logger.Logger;
import models.Lection;
import models.Resource;
import models.ResourceType;
import services.LectionService;
import services.ResourceService;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UseCommandOfLections {
    private final Scanner scanner = new Scanner(System.in);
    LectionService lectionService = new LectionService();

    public void showAllLections() {
        for (Lection lection : lectionService.getLections()) {
            System.out.printf("%s: %s%n", lection.getName(), Arrays.toString(lection.getResources()));
        }
    }

    public void addNewLection() {
        try {
            Lection lection = new Lection(getNameOfLection(), getResources());
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

    private Resource[] getResources() {
        ResourceService resourceService = new ResourceService();

        try {
            System.out.print("Input count of resources: ");
            int count = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < count; i++) {
                Resource resource = new Resource(getNameOfResource(), getResourceType(),
                    getResourceData());
                resourceService.addResource(resource);
            }
        } catch (InputMismatchException e) {
            scanner.nextLine();
            Logger.error(getClass().getName(), "Invalid count");
        }

        return resourceService.getResources();
    }

    private String getNameOfResource() {
        System.out.print("\nInput name of resource: ");
        return scanner.nextLine();
    }

    private ResourceType getResourceType() {
        System.out.print("Input type of resource: ");
        String type = scanner.nextLine().toUpperCase();

        if (!type.equalsIgnoreCase("URL")
            && !type.equalsIgnoreCase("BOOK")
            && !type.equalsIgnoreCase("VIDEO")) {
            throw new ValidationException("Invalid Type");
        }

        return ResourceType.valueOf(type);
    }

    private String getResourceData() {
        System.out.print("Input data of resource: ");
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
