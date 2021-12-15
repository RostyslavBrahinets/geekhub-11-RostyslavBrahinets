package use;

import logger.Logger;
import models.Lection;
import models.Person;
import services.CourseService;
import services.LectionService;
import services.PersonService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UseCourseService {
    private final Scanner scanner = new Scanner(System.in);
    private final UseLectionService useLectionService = new UseLectionService();
    private final UsePersonService usePersonService = new UsePersonService();

    public void addCourses(int countOfCourses) {
        CourseService courseService = new CourseService();

        for (int i = 0; i < countOfCourses; i++) {
            courseService.addCourse(
                getNameOfCourse(),
                getLectionsOfCourse(),
                getStudentsOfCourse()
            );
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
            Logger.warning(getClass().getName(), String.format("Count of %s is invalid", s));
            count = 0;
            scanner.nextLine();
        }

        return count;
    }

    private List<Lection> getLectionsOfCourse() {
        for (int i = 0; i < getCountOf("lections"); i++) {
            useLectionService.addNewLection();
        }

        return new LectionService().getLections();
    }

    private List<Person> getStudentsOfCourse() {
        usePersonService.addPeople(getCountOf("students"));
        return new PersonService().getPeople();
    }
}
