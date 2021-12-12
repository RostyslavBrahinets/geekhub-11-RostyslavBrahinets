package services;

import exceptions.ResourceNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Resource;
import sources.ResourcesSource;

public class ResourceService {
    private final ResourcesSource resourcesSource = ResourcesSource.getInstance();

    public Resource[] getResources() {
        return resourcesSource.getResources();
    }

    public void addResource(Resource resource) {
        try {
            resourcesSource.addResource(resource);
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), "Resource is invalid", e);
        }
    }

    public void deleteResource(int id) {
        try {
            resourcesSource.deleteResource(id);
        } catch (ResourceNotFoundException e) {
            Logger.error(getClass().getName(), "Resource not found", e);
        }
    }

    public Resource getResource(int id) {
        Resource resource = null;

        try {
            resource = resourcesSource.getResourceById(id);
        } catch (ResourceNotFoundException e) {
            Logger.error(getClass().getName(), "Resource not found", e);
        }

        return resource;
    }
}
