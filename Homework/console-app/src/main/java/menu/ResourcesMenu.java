package menu;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Resource;
import services.ResourceService;

import java.util.List;
import java.util.Optional;

public class ResourcesMenu extends Menu {
    private final ResourceService resourceService = new ResourceService();

    @Override
    public void runMenu() {
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
            default -> throw new NotFoundException("Command not found");
        }
    }

    private void showResources() {
        Optional<List<Resource>> resources = resourceService.getResources();
        if (resources.isPresent()) {
            for (Resource resource : resources.get()) {
                System.out.printf(
                    "%s: %s, %s%n",
                    resource.getName(),
                    resource.getType(),
                    resource.getData()
                );
            }
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
                resourceService.addResource(name, type, data);
            }
        } catch (InvalidArgumentException | ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteResource() {
        try {
            resourceService.deleteResource(getId());
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showResource() {
        try {
            Optional<Resource> resource = resourceService.getResource(getId());
            if (resource.isPresent()) {
                System.out.printf(
                    "%s: %s, %s%n",
                    resource.get().getName(),
                    resource.get().getType(),
                    resource.get().getData()
                );
            }
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}