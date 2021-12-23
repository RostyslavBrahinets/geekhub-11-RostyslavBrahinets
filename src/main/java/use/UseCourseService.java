package use;

import logger.Logger;
import models.Lection;
import models.Person;
import services.CourseService;
import services.LectionService;
import services.PersonService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UseCourseService {
    private final Scanner scanner = new Scanner(System.in);
    private final UseLectionService useLectionService = new UseLectionService();
    private final UsePersonService usePersonService = new UsePersonService();

    public void addCourses() {
        CourseService courseService = new CourseService();
        int count = getCountOf("courses");

        for (int i = 0; i < count; i++) {
            Optional<List<Lection>> lectionsOfCourse = getLectionsOfCourse();
            Optional<List<Person>> studentsOfCourse = getStudentsOfCourse();

            if (lectionsOfCourse.isPresent() && studentsOfCourse.isPresent()) {
                courseService.addCourse(
                    getNameOfCourse(),
                    lectionsOfCourse.get(),
                    studentsOfCourse.get()
                );
            }
        }
    }

    public void closeScanner() {
        useLectionService.closeScanner();
        usePersonService.closeScanner();
        scanner.close();
    }

    private String getNameOfCourse() {
        System.out.print("\nInput name of course: ");
        return scanner.nextLine();
    }

    private int getCountOf(String s) {
        System.out.printf("Input count of %s: ", s);
        int count;

        try {
            count = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            Logger.error(getClass().getName(), String.format("Count of %s is invalid", s));
            count = 0;
            scanner.nextLine();
        }

        return count;
    }

    private Optional<List<Lection>> getLectionsOfCourse() {
        int count = getCountOf("lections");
        for (int i = 0; i < count; i++) {
            useLectionService.addNewLection();
        }

        return new LectionService().getLections();
    }

    private Optional<List<Person>> getStudentsOfCourse() {
        usePersonService.addPeople();
        return new PersonService().getPeople();
    }
}
