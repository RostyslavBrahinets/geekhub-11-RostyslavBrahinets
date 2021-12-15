package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Resource;
import sources.LectionSource;

import java.util.List;

public class LectionValidator {
    public void validate(String name, List<Resource> resources) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of lection is invalid");
        } else if (resources == null) {
            throw new ValidationException("Resources are invalid");
        }
    }

    public void validate(int id) {
        LectionSource lectionSource = LectionSource.getInstance();
        if (id < 0 || id >= lectionSource.getLections().size()) {
            throw new NotFoundException("Lection not found");
        }
    }
}
