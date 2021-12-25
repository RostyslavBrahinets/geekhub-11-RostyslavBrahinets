package services;

import models.Course;
import models.Lection;
import models.Person;
import repository.CourseRepository;
import validators.CourseValidator;

import java.util.List;
import java.util.Optional;

public class CourseService {
    private final CourseRepository courseSource = CourseRepository.getInstance();
    private final CourseValidator validator = new CourseValidator();

    public Optional<List<Course>> getCourses() {
        return Optional.ofNullable(courseSource.getCourses());
    }

    public void addCourse(String name, List<Lection> lections, List<Person> students) {
        validator.validate(name, lections, students);
        courseSource.addCourse(new Course(name, lections, students));
    }

    public void deleteCourse(int id) {
        validator.validate(id);
        courseSource.deleteCourse(id);
    }

    public Optional<Course> getCourse(int id) {
        validator.validate(id);
        return Optional.ofNullable(courseSource.getCourse(id));
    }
}
