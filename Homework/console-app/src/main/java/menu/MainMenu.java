package menu;

import exceptions.NotFoundException;
import logger.LogType;

import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu() {
        super();
    }

    @Override
    public void runMenu() {
        showMainMenu();
    }

    @Override
    protected String getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%nInput command (1-7): ");
        return scanner.nextLine();
    }

    private void showMainMenu() {
        System.out.println(
            """

                ###################################

                Main Menu
                1 - Courses Menu
                2 - Lections Menu
                3 - People Menu
                4 - Resources Menu
                5 - Home Works Menu
                6 - Show Logs
                7 - Exit"""
        );

        switch (getCommand()) {
            case "1" -> {
                CoursesMenu coursesMenu = new CoursesMenu();
                coursesMenu.runMenu();
            }
            case "2" -> {
                LectionsMenu lectionsMenu = new LectionsMenu();
                lectionsMenu.runMenu();
            }
            case "3" -> {
                PeopleMenu peopleMenu = new PeopleMenu();
                peopleMenu.runMenu();
            }
            case "4" -> {
                ResourcesMenu resourcesMenu = new ResourcesMenu();
                resourcesMenu.runMenu();
            }
            case "5" -> {
                HomeWorksMenu homeWorkMenu = new HomeWorksMenu();
                homeWorkMenu.runMenu();
            }
            case "6" -> showLoggerMenu();
            case "7" -> {
                Menu.closeScanner();
                System.exit(0);
            }
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    private void showLoggerMenu() {
        System.out.println(
            """
                                    
                Logger Menu
                1 - Show Logs With Sorting
                2 - Show Logs By Status""");

        switch (getCommand()) {
            case "1" -> showLoggerSortMenu();
            case "2" -> showLoggerStatusMenu();
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    private void showLoggerSortMenu() {
        System.out.println(
            """
                                        
                Logger Sort Menu
                1 - Show Logs Without Sorting
                2 - Show Sorted Logs By Date ASC
                3 - Show Sorted Logs By Date DESC"""
        );

        switch (getCommand()) {
            case "1" -> logger.getLogs().forEach(System.out::println);
            case "2" -> logger.getSortedLogsByDateAsc().forEach(System.out::println);
            case "3" -> logger.getSortedLogsByDateDesc().forEach(System.out::println);
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    private void showLoggerStatusMenu() {
        System.out.println(
            """
                                        
                Logger Status Menu
                1 - Info
                2 - Warning
                3 - Error"""
        );

        switch (getCommand()) {
            case "1" -> logger.getLogsByStatus(LogType.INFO);
            case "2" -> logger.getLogsByStatus(LogType.WARNING);
            case "3" -> logger.getLogsByStatus(LogType.ERROR);
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }
}
