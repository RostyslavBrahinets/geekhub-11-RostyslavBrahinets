package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import repository.CourseRepository;

import java.io.IOException;
import java.sql.SQLException;

public class CourseValidator {
    public void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of course is invalid");
        }
    }

    public void validate(int id) throws SQLException, IOException {
        CourseRepository courseSource = CourseRepository.getInstance();
        if (id < 1 || id > courseSource.getCourses().size()) {
            throw new NotFoundException("Course not found");
        }
    }
}
