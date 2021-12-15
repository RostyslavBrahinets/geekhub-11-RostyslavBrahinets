package sources;

import models.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseSource {
    private static CourseSource instance;
    private final List<Course> courses;

    public CourseSource() {
        this.courses = new ArrayList<>();
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

    public Course getCourse(int id) {
        return courses.get(id);
    }

    public static CourseSource getInstance() {
        if (instance == null) {
            instance = new CourseSource();
        }

        return instance;
    }
}
