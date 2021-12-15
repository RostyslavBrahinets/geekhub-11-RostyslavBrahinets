package use;

import services.ResourceService;

import java.util.Scanner;

public class UseResourceService {
    private final Scanner scanner = new Scanner(System.in);

    public void addResources(int countOfResources) {
        ResourceService resourceService = new ResourceService();

        for (int i = 0; i < countOfResources; i++) {
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
}
