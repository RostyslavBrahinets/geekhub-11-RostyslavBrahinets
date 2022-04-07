package services;

import models.Course;
import repository.CourseRepository;
import validators.CourseValidator;

import java.util.List;
import java.util.Optional;

public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseValidator validator;

    public CourseService(
        CourseRepository courseRepository,
        CourseValidator validator
    ) {
        this.courseRepository = courseRepository;
        this.validator = validator;
    }

    public List<Course> getCourses() {
        return courseRepository.getCourses();
    }

    public void addCourse(String name) {
        validator.validate(name);
        courseRepository.addCourse(new Course(name));
    }

    public void deleteCourse(int id) {
        validator.validate(id);
        courseRepository.deleteCourse(id);
    }

    public Optional<Course> getCourse(int id) {
        validator.validate(id);
        return courseRepository.getCourse(id);
    }
}
