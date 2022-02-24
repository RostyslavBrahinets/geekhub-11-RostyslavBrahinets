package services;

import models.Resource;
import models.ResourceType;
import repository.ResourceRepository;
import validators.ResourceValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ResourceService {
    private final ResourceRepository resourcesRepository;
    private final ResourceValidator validator;

    public ResourceService(
        ResourceRepository resourcesRepository,
        ResourceValidator validator
    ) throws SQLException {
        this.resourcesRepository = resourcesRepository;
        this.validator = validator;
    }

    public List<Resource> getResources() throws SQLException, IOException {
        return resourcesRepository.getResources();
    }

    public void addResource(String name, String type, String data, int lectionId)
        throws SQLException, IOException {
        validator.validate(name, type, data);
        resourcesRepository.addResource(
            new Resource(name, ResourceType.valueOf(type), data),
            lectionId
        );
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
