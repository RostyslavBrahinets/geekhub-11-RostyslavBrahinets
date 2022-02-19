package services;

import models.Course;
import models.Lection;
import models.Person;
import repository.CourseRepository;
import validators.CourseValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseValidator validator;

    public CourseService() throws SQLException, IOException {
        courseRepository = CourseRepository.getInstance();
        validator = new CourseValidator();
    }

    public List<Course> getCourses() throws SQLException {
        return courseRepository.getCourses();
    }

    public void addCourse(String name, List<Lection> lections, List<Person> students) throws SQLException {
        validator.validate(name, lections, students);
        courseRepository.addCourse(new Course(name, lections, students));
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
