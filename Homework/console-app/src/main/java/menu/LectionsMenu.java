package menu;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.HomeWork;
import models.Lection;
import models.Person;
import models.Resource;
import services.HomeWorkService;
import services.LectionService;
import services.PersonService;
import services.ResourceService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LectionsMenu extends Menu {
    private final LectionService lectionService = new LectionService();

    @Override
    public void runMenu() {
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
                System.out.println("\nShow Menu\n1 - Sorted ASC\n2 - Sorted DESC");
                switch (getCommand()) {
                    case "1" -> lectionService.sortLectionsByDateASC();
                    case "2" -> lectionService.sortLectionsByDateDESC();
                    default -> throw new NotFoundException("Command not found");
                }
                showLections();
            }
            case "2" -> addLection();
            case "3" -> deleteLection();
            case "4" -> showLection();
            case "5" -> showResourcesGroupedByLection();
            case "6" -> showHomeWorksGroupedByLection();
            default -> throw new NotFoundException("Command not found");
        }
    }

    @Override
    protected String getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInput command (1-6): ");
        return scanner.nextLine();
    }

    private void showLections() {
        List<Lection> lections = lectionService.getLections();
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
            ResourcesMenu resourcesMenu = new ResourcesMenu();
            HomeWorkMenu homeWorkMenu = new HomeWorkMenu();

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

                lecturer.ifPresent(person -> lectionService.addLection(name, describe, resources, person, homeWorks));
            }
        } catch (ValidationException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteLection() {
        try {
            lectionService.deleteLection(getId());
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
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
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showResourcesGroupedByLection() {
        List<Lection> lections = lectionService.getLections();
        for (Lection lection : lections) {
            System.out.printf(
                "%n%s: %s%n",
                lection.getName(),
                lectionService.getResourcesGroupedByLecture().get(lection)
            );
        }
    }

    private void showHomeWorksGroupedByLection() {
        List<Lection> lections = lectionService.getLections();
        for (Lection lection : lections) {
            System.out.printf(
                "%n%s: %s%n",
                lection.getName(),
                lectionService.getHomeWorksGroupedByLecture().get(lection)
            );
        }
    }

    private Optional<Person> getLecturer() {
        PeopleMenu peopleMenu = new PeopleMenu();
        peopleMenu.addPerson();
        return new PersonService().getPerson(0);
    }
}
