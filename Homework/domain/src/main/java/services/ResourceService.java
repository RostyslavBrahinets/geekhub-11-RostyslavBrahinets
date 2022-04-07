package services;

import models.Resource;
import models.ResourceType;
import repository.ResourceRepository;
import validators.ResourceValidator;

import java.util.List;
import java.util.Optional;

public class ResourceService {
    private final ResourceRepository resourcesRepository;
    private final ResourceValidator validator;

    public ResourceService(
        ResourceRepository resourcesRepository,
        ResourceValidator validator
    ) {
        this.resourcesRepository = resourcesRepository;
        this.validator = validator;
    }

    public List<Resource> getResources() {
        return resourcesRepository.getResources();
    }

    public void addResource(String name, String type, String data, int lectionId) {
        validator.validate(name, type, data);
        resourcesRepository.addResource(
            new Resource(name, ResourceType.valueOf(type), data),
            lectionId
        );
    }

    public void deleteResource(int id) {
        validator.validate(id);
        resourcesRepository.deleteResource(id);
    }

    public Optional<Resource> getResource(int id) {
        validator.validate(id);
        return resourcesRepository.getResource(id);
    }
}
