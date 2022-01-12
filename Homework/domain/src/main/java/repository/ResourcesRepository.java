package repository;

import models.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourcesRepository {
    private static ResourcesRepository instance;
    private final List<Resource> resources;

    public ResourcesRepository() {
        resources = new ArrayList<>();
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void deleteResource(int id) {
        resources.remove(id);
    }

    public Optional<Resource> getResource(int id) {
        return Optional.ofNullable(resources.get(id));
    }

    public static ResourcesRepository getInstance() {
        if (instance == null) {
            instance = new ResourcesRepository();
        }

        return instance;
    }
}
