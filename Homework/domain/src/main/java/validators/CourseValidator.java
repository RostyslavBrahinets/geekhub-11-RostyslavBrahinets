package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import repository.CourseRepository;

public class CourseValidator {
    private final CourseRepository courseRepository;

    public CourseValidator(CourseRepository courseSource) {
        this.courseRepository = courseSource;
    }

    public void validate(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of course is invalid");
        }
    }

    public void validate(int id) {
        if (id < 1 || id > courseRepository.getCourses().size()) {
            throw new NotFoundException("Course not found");
        }
    }
}
