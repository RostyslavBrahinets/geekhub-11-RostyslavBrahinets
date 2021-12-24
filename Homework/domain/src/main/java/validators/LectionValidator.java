package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.HomeWork;
import models.Person;
import models.Resource;
import sources.LectionSource;

import java.util.List;

public class LectionValidator {
    public void validate(String name, String describe, List<Resource> resources, Person lecturer,
                         List<HomeWork> homeWorks) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of lection is invalid");
        } else if (describe == null || describe.isBlank()) {
            throw new ValidationException("Describe of lection is invalid");
        } else if (resources == null) {
            throw new ValidationException("Resources are invalid");
        } else if (lecturer == null) {
            throw new ValidationException("Lecturer is invalid");
        } else if (homeWorks == null) {
            throw new ValidationException("Home works are invalid");
        }
    }

    public void validate(int id) {
        LectionSource lectionSource = LectionSource.getInstance();
        if (id < 0 || id >= lectionSource.getLections().size()) {
            throw new NotFoundException("Lection not found");
        }
    }
}
