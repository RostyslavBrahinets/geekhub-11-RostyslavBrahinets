package use;

import logger.Logger;
import services.ResourceService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UseResourceService {
    private final Scanner scanner = new Scanner(System.in);

    public void addResources() {
        ResourceService resourceService = new ResourceService();
        int count = getCountOf("resources");

        for (int i = 0; i < count; i++) {
            resourceService.addResource(
                getNameOfResource(),
                getResourceType(),
                getResourceData()
            );
        }
    }

    public void closeScanner() {
        scanner.close();
    }

    private String getNameOfResource() {
        System.out.print("\nInput name of resource: ");
        return scanner.nextLine();
    }

    private String getResourceType() {
        System.out.print("Input type of resource: ");
        return scanner.nextLine().toUpperCase();
    }

    private String getResourceData() {
        System.out.print("Input data of resource: ");
        return scanner.nextLine();
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
}
