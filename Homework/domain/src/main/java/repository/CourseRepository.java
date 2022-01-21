package repository;

import models.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private static CourseRepository instance;
    private final List<Course> courses = new ArrayList<>();

    private CourseRepository() {
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void deleteCourse(int id) {
        courses.remove(id);
    }

    public Optional<Course> getCourse(int id) {
        return Optional.ofNullable(courses.get(id));
    }

    public static CourseRepository getInstance() {
        if (instance == null) {
            instance = new CourseRepository();
        }

        return instance;
    }
}
