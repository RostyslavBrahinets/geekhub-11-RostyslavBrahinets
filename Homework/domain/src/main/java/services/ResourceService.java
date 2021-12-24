package services;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Resource;
import models.ResourceType;
import repository.ResourcesRepository;
import validators.ResourceValidator;

import java.util.List;
import java.util.Optional;

public class ResourceService {
    private final ResourcesRepository resourcesSource = ResourcesRepository.getInstance();
    private final ResourceValidator validator = new ResourceValidator();

    public Optional<List<Resource>> getResources() {
        return Optional.ofNullable(resourcesSource.getResources());
    }

    public void addResource(String name, String type, String data) {
        try {
            validator.validate(name, type, data);
            resourcesSource.addResource(new Resource(name, ResourceType.valueOf(type), data));
        } catch (ValidationException | InvalidArgumentException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void deleteResource(int id) {
        try {
            validator.validate(id);
            resourcesSource.deleteResource(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public Optional<Resource> getResource(int id) {
        Resource resource = null;

        try {
            validator.validate(id);
            resource = resourcesSource.getResource(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return Optional.ofNullable(resource);
    }
}
