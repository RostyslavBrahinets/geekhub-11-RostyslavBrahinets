package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Lection;
import models.Person;
import repository.CourseRepository;

import java.util.List;

public class CourseValidator {
    public void validate(String name, List<Lection> lections, List<Person> students) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of course is invalid");
        } else if (lections == null) {
            throw new ValidationException("List of lections is invalid");
        } else if (students == null) {
            throw new ValidationException("List of students is invalid");
        }
    }

    public void validate(int id) {
        CourseRepository courseSource = CourseRepository.getInstance();
        if (id < 0 || id >= courseSource.getCourses().size()) {
            throw new NotFoundException("Course not found");
        }
    }
}
