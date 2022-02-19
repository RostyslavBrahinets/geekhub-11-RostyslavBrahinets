package menu;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Course;
import models.Lection;
import models.Person;
import services.CourseService;
import services.LectionService;
import services.PersonService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CoursesMenu extends Menu {
    private final CourseService courseService;

    public CoursesMenu() throws SQLException, IOException {
        super();
        courseService = new CourseService();
    }

    @Override
    public void runMenu() throws SQLException {
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

    private void showCourses() throws SQLException {
        List<Course> courses = courseService.getCourses();
        for (Course course : courses) {
            System.out.printf(
                "%s: %s, %s%n",
                course.name(),
                course.lections(),
                course.students()
            );
        }
    }

    private void addCourse() {
        try {
            System.out.println("\nNew Courses");
            int count = getCount();
            for (int i = 0; i < count; i++) {
                System.out.print("\nName: ");
                String name = getFromScanner();
                List<Lection> lectionsOfCourse = getLectionsOfCourse();
                List<Person> studentsOfCourse = getStudentsOfCourse();
                courseService.addCourse(
                    name,
                    lectionsOfCourse,
                    studentsOfCourse
                );
            }
        } catch (ValidationException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteCourse() {
        try {
            courseService.deleteCourse(getId());
        } catch (NotFoundException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showCourse() {
        try {
            Optional<Course> course = courseService.getCourse(getId());
            course.ifPresent(value -> System.out.printf(
                "%s: %s, %s%n",
                value.name(),
                value.lections(),
                value.students()
            ));
        } catch (NotFoundException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private List<Lection> getLectionsOfCourse() throws SQLException, IOException {
        LectionsMenu lectionsMenu = new LectionsMenu();
        lectionsMenu.addLection();
        return new LectionService().getLections();
    }

    private List<Person> getStudentsOfCourse() throws SQLException, IOException {
        PeopleMenu peopleMenu = new PeopleMenu();
        peopleMenu.addPerson();
        return new PersonService().getPeople();
    }
}
