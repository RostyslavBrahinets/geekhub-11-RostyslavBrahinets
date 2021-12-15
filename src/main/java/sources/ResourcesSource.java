package sources;

import models.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourcesSource {
    private static ResourcesSource instance;
    private final List<Resource> resources;

    public ResourcesSource() {
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

    public Resource getResourceById(int id) {
        return resources.get(id);
    }

    public static ResourcesSource getInstance() {
        if (instance == null) {
            instance = new ResourcesSource();
        }

        return instance;
    }
}
