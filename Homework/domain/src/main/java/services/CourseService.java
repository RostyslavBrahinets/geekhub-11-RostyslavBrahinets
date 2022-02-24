package services;

import models.Course;
import repository.CourseRepository;
import validators.CourseValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseValidator validator;

    public CourseService(
        CourseRepository courseRepository,
        CourseValidator validator
    ) throws SQLException {
        this.courseRepository = courseRepository;
        this.validator = validator;
    }

    public List<Course> getCourses() throws SQLException, IOException {
        return courseRepository.getCourses();
    }

    public void addCourse(String name) throws SQLException, IOException {
        validator.validate(name);
        courseRepository.addCourse(new Course(name));
    }

    public void deleteCourse(int id) throws SQLException, IOException {
        validator.validate(id);
        courseRepository.deleteCourse(id);
    }

    public Optional<Course> getCourse(int id) throws SQLException, IOException {
        validator.validate(id);
        return courseRepository.getCourse(id);
    }
}
