package services;

import models.Course;
import models.Lection;
import models.Person;
import repository.CourseRepository;
import validators.CourseValidator;

import java.util.List;
import java.util.Optional;

public class CourseService {
    private final CourseRepository courseRepository = CourseRepository.getInstance();
    private final CourseValidator validator = new CourseValidator();

    public List<Course> getCourses() {
        return courseRepository.getCourses();
    }

    public void addCourse(String name, List<Lection> lections, List<Person> students) {
        validator.validate(name, lections, students);
        courseRepository.addCourse(new Course(name, lections, students));
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
