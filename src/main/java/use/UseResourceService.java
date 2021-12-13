package use;

import exceptions.ValidationException;
import logger.Logger;
import models.Resource;
import models.ResourceType;
import services.ResourceService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UseResourceService {
    private final Scanner scanner = new Scanner(System.in);

    public void addResources() {
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
}
