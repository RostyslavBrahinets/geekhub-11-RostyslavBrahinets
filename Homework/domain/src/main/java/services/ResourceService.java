package services;

import models.Resource;
import models.ResourceType;
import repository.ResourcesRepository;
import validators.ResourceValidator;

import java.util.List;
import java.util.Optional;

public class ResourceService {
    private final ResourcesRepository resourcesRepository = ResourcesRepository.getInstance();
    private final ResourceValidator validator = new ResourceValidator();

    public Optional<List<Resource>> getResources() {
        return Optional.ofNullable(resourcesRepository.getResources());
    }

    public void addResource(String name, String type, String data) {
        validator.validate(name, type, data);
        resourcesRepository.addResource(new Resource(name, ResourceType.valueOf(type), data));
    }

    public void deleteResource(int id) {
        validator.validate(id);
        resourcesRepository.deleteResource(id);
    }

    public Optional<Resource> getResource(int id) {
        validator.validate(id);
        return Optional.ofNullable(resourcesRepository.getResource(id));
    }
}
