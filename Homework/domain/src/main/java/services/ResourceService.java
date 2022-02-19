package services;

import models.Resource;
import models.ResourceType;
import repository.ResourcesRepository;
import validators.ResourceValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ResourceService {
    private final ResourcesRepository resourcesRepository;
    private final ResourceValidator validator;

    public ResourceService() throws SQLException, IOException {
        resourcesRepository = ResourcesRepository.getInstance();
        validator = new ResourceValidator();
    }

    public List<Resource> getResources() throws SQLException {
        return resourcesRepository.getResources();
    }

    public void addResource(String name, String type, String data) throws SQLException {
        validator.validate(name, type, data);
        resourcesRepository.addResource(new Resource(name, ResourceType.valueOf(type), data));
    }

    public void deleteResource(int id) throws SQLException, IOException {
        validator.validate(id);
        resourcesRepository.deleteResource(id);
    }

    public Optional<Resource> getResource(int id) throws SQLException, IOException {
        validator.validate(id);
        return resourcesRepository.getResource(id);
    }
}
