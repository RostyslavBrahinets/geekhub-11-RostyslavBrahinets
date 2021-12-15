package validators;

import exceptions.ResourceNotFoundException;
import exceptions.ValidationException;
import models.ResourceType;
import sources.ResourcesSource;

public class ResourceValidator {
    public void validate(String name, ResourceType type, String data) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of resource is invalid");
        } else if (isInvalidType(type)) {
            throw new ValidationException("Type of resource is invalid");
        } else if (data == null || data.isBlank()) {
            throw new ValidationException("Data of are invalid");
        }
    }

    public void validate(int id) {
        ResourcesSource resourcesSource = ResourcesSource.getInstance();
        if (id < 0 || id >= resourcesSource.getResources().size()) {
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    private boolean isInvalidType(ResourceType type) {
        boolean invalidType = true;

        for (ResourceType resourceType : ResourceType.values()) {
            if (resourceType == type) {
                invalidType = false;
                break;
            }
        }

        return invalidType;
    }
}
