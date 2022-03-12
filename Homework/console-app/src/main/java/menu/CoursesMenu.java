package menu;

import exceptions.NotFoundException;
import models.Course;
import services.CourseService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CoursesMenu extends Menu {
    private final CourseService courseService;

    public CoursesMenu(CourseService courseService) {
        super();
        this.courseService = courseService;
    }

    @Override
    public void runMenu() throws SQLException, IOException {
        System.out.println(
            """

                ###################################

                Courses Menu
                1 - Show Courses
                2 - Add Course
                3 - Delete Course
                4 - Show Course"""
        );

        switch (getCommand()) {
            case "1" -> showCourses();
            case "2" -> addCourse();
            case "3" -> deleteCourse();
            case "4" -> showCourse();
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    private void showCourses() throws SQLException, IOException {
        List<Course> courses = courseService.getCourses();
        for (Course course : courses) {
            System.out.printf(
                "%s: %s, %s%n",
                course.getName(),
                course.getLections(),
                course.getStudents()
            );
        }
    }

    private void addCourse() {
        try {
            System.out.println("\nNew Course");
            int count = getCount();
            for (int i = 0; i < count; i++) {
                System.out.print("\nName: ");
                String name = getFromScanner();
                courseService.addCourse(name);
            }
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteCourse() {
        try {
            courseService.deleteCourse(getId());
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showCourse() {
        try {
            Optional<Course> course = courseService.getCourse(getId());
            course.ifPresent(value -> System.out.printf(
                "%s: %s, %s%n",
                value.getName(),
                value.getLections(),
                value.getStudents()
            ));
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}
