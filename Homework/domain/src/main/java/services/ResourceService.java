package services;

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
        validator.validate(name, type, data);
        resourcesSource.addResource(new Resource(name, ResourceType.valueOf(type), data));
    }

    public void deleteResource(int id) {
        validator.validate(id);
        resourcesSource.deleteResource(id);
    }

    public Optional<Resource> getResource(int id) {
        validator.validate(id);
        return Optional.ofNullable(resourcesSource.getResource(id));
    }
}
