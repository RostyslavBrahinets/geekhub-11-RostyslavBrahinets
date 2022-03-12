package menu;

import exceptions.NotFoundException;
import models.Resource;
import services.ResourceService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ResourcesMenu extends Menu {
    private final ResourceService resourceService;

    public ResourcesMenu(ResourceService resourceService) {
        super();
        this.resourceService = resourceService;
    }

    @Override
    public void runMenu() throws SQLException, IOException {
        System.out.println(
            """

                ###################################

                Resources Menu
                1 - Show Resources
                2 - Add Resource
                3 - Delete Resource
                4 - Show Resource"""
        );

        switch (getCommand()) {
            case "1" -> showResources();
            case "2" -> addResource();
            case "3" -> deleteResource();
            case "4" -> showResource();
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    private void showResources() throws SQLException, IOException {
        List<Resource> resources = resourceService.getResources();
        for (Resource resource : resources) {
            System.out.printf(
                "%s: %s, %s%n",
                resource.getName(),
                resource.getType(),
                resource.getData()
            );
        }

    }

    public void addResource() {
        try {
            System.out.println("\nNew Resources");
            int count = getCount();
            for (int i = 0; i < count; i++) {
                System.out.print("\nName: ");
                String name = getFromScanner();
                System.out.print("Type: ");
                String type = getFromScanner().toUpperCase();
                System.out.print("Data: ");
                String data = getFromScanner();
                System.out.print("Lection id: ");
                String id = getFromScanner();
                resourceService.addResource(name, type, data, Integer.parseInt(id));
            }
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteResource() {
        try {
            resourceService.deleteResource(getId());
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showResource() {
        try {
            Optional<Resource> resource = resourceService.getResource(getId());
            resource.ifPresent(value -> System.out.printf(
                "%s: %s, %s%n",
                value.getName(),
                value.getType(),
                value.getData()
            ));
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}
