package menu;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.HomeWork;
import models.Lection;
import models.Person;
import models.Resource;
import services.HomeWorkService;
import services.LectionService;
import services.PersonService;
import services.ResourceService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LectionsMenu extends Menu {
    private final LectionService lectionService;
    private final PersonService personService;

    public LectionsMenu() throws SQLException, IOException {
        super();
        lectionService = new LectionService();
        personService = new PersonService();
    }

    @Override
    public void runMenu() throws SQLException {
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
                System.out.println("\nShow Menu\n1 - Without sorting\n2 - Sorted ASC\n3 - Sorted DESC");
                switch (getCommand()) {
                    case "1" -> showLections(lectionService.getLections());
                    case "2" -> showLections(lectionService.getLectionsSortedByDateASC());
                    case "3" -> showLections(lectionService.getLectionsSortedByDateDESC());
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
                lection.name(),
                lection.describe(),
                lection.resources(),
                lection.lecturer().firstName(),
                lection.lecturer().lastName(),
                lection.homeWorks()
            );
        }
    }

    public void addLection() {
        try {
            ResourcesMenu resourcesMenu = new ResourcesMenu();
            HomeWorksMenu homeWorkMenu = new HomeWorksMenu();

            System.out.println("\nNew Lections");
            int count = getCount();
            for (int i = 0; i < count; i++) {
                System.out.print("\nName: ");
                String name = getFromScanner();
                System.out.print("Describe: ");
                String describe = getFromScanner();
                resourcesMenu.addResource();
                Optional<Person> lecturer = getLecturer();
                homeWorkMenu.addHomeWork();

                ResourceService resourceService = new ResourceService();
                HomeWorkService homeWorkService = new HomeWorkService();

                List<Resource> resources = resourceService.getResources();
                List<HomeWork> homeWorks = homeWorkService.getHomeWorks();

                lecturer.ifPresent(person -> {
                    try {
                        lectionService.addLection(name, describe, resources, person, homeWorks);
                    } catch (SQLException e) {
                        logger.error(getClass().getName(), e.getMessage(), e);
                    }
                });
            }
        } catch (ValidationException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteLection() {
        try {
            lectionService.deleteLection(getId());
        } catch (NotFoundException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showLection() {
        try {
            Optional<Lection> lection = lectionService.getLection(getId());
            lection.ifPresent(value -> System.out.printf(
                "%s: %s; %s; %s %s; %s%n",
                value.name(),
                value.describe(),
                value.resources(),
                value.lecturer().firstName(),
                value.lecturer().lastName(),
                value.homeWorks()
            ));
        } catch (NotFoundException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showResourcesGroupedByLection() throws SQLException {
        List<Lection> lections = lectionService.getLections();
        for (Lection lection : lections) {
            System.out.printf(
                "%n%s: %s%n",
                lection.name(),
                lectionService.getResourcesGroupedByLecture().get(lection.name())
            );
        }
    }

    private void showHomeWorksGroupedByLection() throws SQLException {
        List<Lection> lections = lectionService.getLections();
        for (Lection lection : lections) {
            System.out.printf(
                "%n%s: %s%n",
                lection.name(),
                lectionService.getHomeWorksGroupedByLecture().get(lection.name())
            );
        }
    }

    private Optional<Person> getLecturer() throws SQLException, IOException {
        PeopleMenu peopleMenu = new PeopleMenu();
        peopleMenu.addPerson();
        return personService.getPerson(0);
    }
}
