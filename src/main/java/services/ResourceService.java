package services;

import exceptions.ResourceNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Resource;
import models.ResourceType;
import sources.ResourcesSource;
import validators.ResourceValidator;

import java.util.List;

public class ResourceService {
    private final ResourcesSource resourcesSource = ResourcesSource.getInstance();
    private final ResourceValidator validator = new ResourceValidator();

    public List<Resource> getResources() {
        return resourcesSource.getResources();
    }

    public void addResource(String name, ResourceType type, String data) {
        try {
            validator.validate(name, type, data);
            resourcesSource.addResource(new Resource(name, type, data));
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void deleteResource(int id) {
        try {
            validator.validate(id);
            resourcesSource.deleteResource(id);
        } catch (ResourceNotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public Resource getResourceById(int id) {
        Resource resource = null;

        try {
            validator.validate(id);
            resource = resourcesSource.getResourceById(id);
        } catch (ResourceNotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return resource;
    }
}
