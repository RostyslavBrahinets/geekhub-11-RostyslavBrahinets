package menu;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Course;
import models.Lection;
import models.Person;
import services.CourseService;
import services.LectionService;
import services.PersonService;

import java.util.List;
import java.util.Optional;

public class CoursesMenu extends Menu {
    private final CourseService courseService = new CourseService();

    @Override
    public void runMenu() {
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
            default -> throw new NotFoundException("Command not found");
        }
    }

    private void showCourses() {
        Optional<List<Course>> courses = courseService.getCourses();
        if (courses.isPresent()) {
            for (Course course : courses.get()) {
                System.out.printf(
                    "%s: %s, %s%n",
                    course.getName(),
                    course.getLections(),
                    course.getStudents()
                );
            }
        }
    }

    private void addCourse() {
        try {
            System.out.println("\nNew Courses");
            int count = getCount();
            for (int i = 0; i < count; i++) {
                System.out.print("\nName: ");
                String name = getFromScanner();
                Optional<List<Lection>> lectionsOfCourse = getLectionsOfCourse();
                Optional<List<Person>> studentsOfCourse = getStudentsOfCourse();

                if (lectionsOfCourse.isPresent() && studentsOfCourse.isPresent()) {
                    courseService.addCourse(
                        name,
                        lectionsOfCourse.get(),
                        studentsOfCourse.get()
                    );
                }
            }
        } catch (
            ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteCourse() {
        try {
            courseService.deleteCourse(getId());
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showCourse() {
        try {
            Optional<Course> course = courseService.getCourse(getId());
            if (course.isPresent()) {
                System.out.printf(
                    "%s: %s, %s%n",
                    course.get().getName(),
                    course.get().getLections(),
                    course.get().getStudents()
                );
            }
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private Optional<List<Lection>> getLectionsOfCourse() {
        LectionsMenu lectionsMenu = new LectionsMenu();
        lectionsMenu.addLection();
        return new LectionService().getLections();
    }

    private Optional<List<Person>> getStudentsOfCourse() {
        PeopleMenu peopleMenu = new PeopleMenu();
        peopleMenu.addPerson();
        return new PersonService().getPeople();
    }
}