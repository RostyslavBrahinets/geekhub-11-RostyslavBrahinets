package menu;

import exceptions.NotFoundException;

import java.util.Scanner;

public class MainMenu extends Menu {
    public MainMenu() {
        super();
    }

    @Override
    public void runMenu() {
        System.out.println(
            """

                ###################################

                Main Menu
                1 - Courses Menu
                2 - Lections Menu
                3 - People Menu
                4 - Resources
                5 - Home Work Menu
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
                HomeWorkMenu homeWorkMenu = new HomeWorkMenu();
                homeWorkMenu.runMenu();
            }
            case "6" -> {
                System.out.println(
                    """
                                                
                        Logger Menu
                        1 - Show All Logs
                        2 - Show Sorted Logs By Date ASC
                        3 - Show Sorted Logs By Date DESC"""
                );

                switch (getCommand()) {
                    case "1" -> logger.showLogs();
//                    case "2" -> Logger.showSortedLogsByDateASC();
//                    case "3" -> Logger.showSortedLogsByDateDESC();
                    default -> throw new NotFoundException("Command not found");
                }
            }
            case "7" -> {
                Menu.closeScanner();
                System.exit(0);
            }
            default -> throw new NotFoundException("Command not found");
        }
    }

    @Override
    protected String getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%nInput command (1-7): ");
        return scanner.nextLine();
    }
}
