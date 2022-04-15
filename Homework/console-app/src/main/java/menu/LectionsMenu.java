package menu;

import exceptions.NotFoundException;
import models.Lection;
import services.LectionService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LectionsMenu extends Menu {
    private final LectionService lectionService;

    public LectionsMenu(LectionService lectionService) {
        super();
        this.lectionService = lectionService;
    }

    @Override
    public void runMenu() throws SQLException, IOException {
        System.out.println(
            """

                ###################################

                Lections Menu
                1 - Show Lections
                2 - Add Lection
                3 - Delete Lection
                4 - Show Lection
                5 - Show Resources Grouped By Lection
                6 - Show Home Works Grouped By Lection"""
        );

        switch (getCommand()) {
            case "1" -> {
                System.out.println("""

                    Show Menu
                    1 - Without sorting
                    2 - Sorted ASC
                    3 - Sorted DESC""");
                switch (getCommand()) {
                    case "1" -> showLections(lectionService.getLections());
                    case "2" -> showLections(lectionService.getLectionsSortedByDateAsc());
                    case "3" -> showLections(lectionService.getLectionsSortedByDateDesc());
                    default -> throw new NotFoundException(COMMAND_NOT_FOUND);
                }
            }
            case "2" -> addLection();
            case "3" -> deleteLection();
            case "4" -> showLection();
            case "5" -> showResourcesGroupedByLection();
            case "6" -> showHomeWorksGroupedByLection();
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    @Override
    protected String getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInput command (1-6): ");
        return scanner.nextLine();
    }

    private void showLections(List<Lection> lections) {
        for (Lection lection : lections) {
            System.out.printf(
                "%s: %s; %s; %s %s; %s%n",
                lection.getName(),
                lection.getDescribe(),
                lection.getResources(),
                lection.getLecturer().getFirstName(),
                lection.getLecturer().getLastName(),
                lection.getHomeWorks()
            );
        }
    }

    public void addLection() {
        try {
            System.out.println("\nNew Lections");
            int count = getCount();
            for (int i = 0; i < count; i++) {
                System.out.print("\nName: ");
                String name = getFromScanner();
                System.out.print("Describe: ");
                String describe = getFromScanner();
                System.out.print("Lecturer id: ");
                String lecturerId = getFromScanner();
                System.out.print("Course id: ");
                String courseId = getFromScanner();

                lectionService.addLection(
                    new Lection(name, describe, LocalDate.now()),
                    Integer.parseInt(lecturerId),
                    Integer.parseInt(courseId)
                );
            }
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteLection() {
        try {
            lectionService.deleteLection(getId());
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showLection() {
        try {
            Optional<Lection> lection = lectionService.getLection(getId());
            lection.ifPresent(value -> System.out.printf(
                "%s: %s; %s; %s %s; %s%n",
                value.getName(),
                value.getDescribe(),
                value.getResources(),
                value.getLecturer().getFirstName(),
                value.getLecturer().getLastName(),
                value.getHomeWorks()
            ));
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showResourcesGroupedByLection() throws SQLException, IOException {
        List<Lection> lections = lectionService.getLections();
        for (Lection lection : lections) {
            System.out.printf(
                "%n%s: %s%n",
                lection.getName(),
                lectionService.getResourcesGroupedByLecture().get(lection.getName())
            );
        }
    }

    private void showHomeWorksGroupedByLection() throws SQLException, IOException {
        List<Lection> lections = lectionService.getLections();
        for (Lection lection : lections) {
            System.out.printf(
                "%n%s: %s%n",
                lection.getName(),
                lectionService.getHomeWorksGroupedByLecture().get(lection.getName())
            );
        }
    }
}
