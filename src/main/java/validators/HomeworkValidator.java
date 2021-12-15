package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import sources.HomeworkSource;

import java.util.List;

public class HomeworkValidator {
    public void validate(List<String> tasks) {
        if (tasks == null) {
            throw new ValidationException("Tasks of homework are invalid");
        }
    }

    public void validate(int id) {
        HomeworkSource homeworkSource = HomeworkSource.getInstance();
        if (id < 0 || id >= homeworkSource.getHomeworks().size()) {
            throw new NotFoundException("Homework not found");
        }
    }
}
