package validators;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.ResourceType;
import sources.ResourcesSource;

public class ResourceValidator {
    public void validate(String name, String type, String data) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of resource is invalid");
        } else if (isInvalidType(type)) {
            throw new InvalidArgumentException("Type of resource is invalid");
        } else if (data == null || data.isBlank()) {
            throw new ValidationException("Data of are invalid");
        }
    }

    public void validate(int id) {
        ResourcesSource resourcesSource = ResourcesSource.getInstance();
        if (id < 0 || id >= resourcesSource.getResources().size()) {
            throw new NotFoundException("Resource not found");
        }
    }

    private boolean isInvalidType(String type) {
        boolean invalidType = true;

        for (ResourceType resourceType : ResourceType.values()) {
            if (type.equalsIgnoreCase(String.valueOf(resourceType))) {
                invalidType = false;
                break;
            }
        }

        return invalidType;
    }
}
