package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import sources.HomeWorkSource;

public class HomeWorkValidator {
    public void validate(String task) {
        if (task == null || task.isBlank()) {
            throw new ValidationException("Task of homework is invalid");
        }
    }

    public void validate(int id) {
        HomeWorkSource homeworkSource = HomeWorkSource.getInstance();
        if (id < 0 || id >= homeworkSource.getHomeWorks().size()) {
            throw new NotFoundException("HomeWork not found");
        }
    }
}
