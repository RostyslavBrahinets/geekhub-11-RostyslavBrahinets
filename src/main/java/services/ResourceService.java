package services;

import exceptions.ResourceNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Resource;
import models.ResourceType;
import sources.ResourcesSource;

import java.util.List;

public class ResourceService {
    private final ResourcesSource resourcesSource = ResourcesSource.getInstance();

    public List<Resource> getResources() {
        return resourcesSource.getResources();
    }

    public void addResource(String name, ResourceType type, String data) {
        try {
            if (name == null || name.isBlank() || data == null || data.isBlank()) {
                throw new ValidationException("Resource is invalid");
            }

            boolean invalidType = true;

            for (ResourceType resourceType : ResourceType.values()) {
                if (resourceType == type) {
                    invalidType = false;
                    break;
                }
            }

            if (invalidType) {
                throw new ValidationException("Resource is invalid");
            }

            resourcesSource.addResource(new Resource(name, type, data));
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), "Resource is invalid", e);
        }
    }

    public void deleteResource(int id) {
        try {
            if (id < 0 || id >= resourcesSource.getResources().size()) {
                throw new ResourceNotFoundException("Resource not found");
            }

            resourcesSource.deleteResource(id);
        } catch (ResourceNotFoundException e) {
            Logger.error(getClass().getName(), "Resource not found", e);
        }
    }

    public Resource getResourceById(int id) {
        Resource resource = null;

        try {
            if (id < 0 || id >= resourcesSource.getResources().size()) {
                throw new ResourceNotFoundException("Resource not found");
            }

            resource = resourcesSource.getResourceById(id);
        } catch (ResourceNotFoundException e) {
            Logger.error(getClass().getName(), "Resource not found", e);
        }

        return resource;
    }
}
