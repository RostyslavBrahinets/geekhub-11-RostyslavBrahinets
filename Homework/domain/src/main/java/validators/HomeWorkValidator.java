package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import repository.HomeWorkRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class HomeWorkValidator {
    private final HomeWorkRepository homeWorkRepository;

    public HomeWorkValidator(HomeWorkRepository homeworkSource) {
        this.homeWorkRepository = homeworkSource;
    }

    public void validate(String task, LocalDateTime deadline) {
        if (task == null || task.isBlank()) {
            throw new ValidationException("Task of homework is invalid");
        } else if (deadline == null) {
            throw new ValidationException("Deadline of homework is invalid");
        }
    }

    public void validate(int id) throws SQLException, IOException {
        if (id < 1 || id > homeWorkRepository.getHomeWorks().size()) {
            throw new NotFoundException("HomeWork not found");
        }
    }
}
