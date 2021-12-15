package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import sources.HomeworkSource;

public class HomeworkValidator {
    public void validate(String task) {
        if (task == null || task.isBlank()) {
            throw new ValidationException("Task of homework is invalid");
        }
    }

    public void validate(int id) {
        HomeworkSource homeworkSource = HomeworkSource.getInstance();
        if (id < 0 || id >= homeworkSource.getHomeworks().size()) {
            throw new NotFoundException("Homework not found");
        }
    }
}
