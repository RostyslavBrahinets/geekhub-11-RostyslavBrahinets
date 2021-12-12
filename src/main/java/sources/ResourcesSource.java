package sources;

import exceptions.ResourceNotFoundException;
import exceptions.ValidationException;
import models.Resource;
import models.ResourceType;

import java.util.Arrays;

public class ResourcesSource {
    private static ResourcesSource instance;
    private Resource[] resources;

    public ResourcesSource() {
        resources = new Resource[0];
    }

    public Resource[] getResources() {
        return resources;
    }

    public void addResource(Resource resource) {
        if (resource == null || resource.getName().isBlank() || resource.getData() == null) {
            throw new ValidationException("Resource is invalid");
        }

        boolean invalidType = true;

        for (ResourceType type : ResourceType.values()) {
            if (type == resource.getType()) {
                invalidType = false;
                break;
            }
        }

        if (invalidType) {
            throw new ValidationException("Resource is invalid");
        }

        resources = Arrays.copyOf(resources, resources.length + 1);
        resources[resources.length - 1] = resource;
    }

    public void deleteResource(int id) {
        if (id < 0 || id >= resources.length) {
            throw new ResourceNotFoundException("Resource not found");
        }

        Resource[] newResources = new Resource[resources.length - 1];

        System.arraycopy(resources, 0, newResources, 0, id);

        if (resources.length - (id + 1) >= 0) {
            System.arraycopy(resources, id + 1, newResources, id, resources.length - (id + 1));
        }

        resources = newResources;
    }

    public Resource getResourceById(int id) {
        if (id < 0 || id >= resources.length) {
            throw new ResourceNotFoundException("Resource not found");
        }

        return resources[id];
    }

    public static ResourcesSource getInstance() {
        if (instance == null) {
            instance = new ResourcesSource();
        }

        return instance;
    }
}
