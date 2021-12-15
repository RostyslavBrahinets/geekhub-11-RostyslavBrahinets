package services;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Course;
import models.Lection;
import models.Person;
import sources.CourseSource;
import validators.CourseValidator;

import java.util.List;

public class CourseService {
    private final CourseSource courseSource = CourseSource.getInstance();
    private final CourseValidator validator = new CourseValidator();

    public List<Course> getCourses() {
        return courseSource.getCourses();
    }

    public void addCourse(String name, List<Lection> lections, List<Person> students) {
        try {
            validator.validate(name, lections, students);
            courseSource.addCourse(new Course(name, lections, students));
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void deleteCourse(int id) {
        try {
            validator.validate(id);
            courseSource.deleteCourse(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public Course getCourse(int id) {
        Course course = null;

        try {
            validator.validate(id);
            course = courseSource.getCourse(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return course;
    }
}
