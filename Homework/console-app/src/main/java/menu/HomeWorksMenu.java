package menu;

import exceptions.NotFoundException;
import models.HomeWork;
import services.HomeWorkService;

import java.util.List;
import java.util.Optional;

public class HomeWorksMenu extends Menu {
    private final HomeWorkService homeWorkService;

    public HomeWorksMenu(HomeWorkService homeWorkService) {
        super();
        this.homeWorkService = homeWorkService;
    }

    @Override
    public void runMenu() {
        System.out.println(
            """

                ###################################

                Home Works Menu
                1 - Show Home Works
                2 - Add Home Work
                3 - Delete Home Work
                4 - Show Home Work"""
        );

        switch (getCommand()) {
            case "1" -> showHomeWorks();
            case "2" -> addHomeWork();
            case "3" -> deleteHomeWork();
            case "4" -> showHomeWork();
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    private void showHomeWorks() {
        List<HomeWork> homeWorks;
        try {
            homeWorks = homeWorkService.getHomeWorks();
            for (HomeWork homeWork : homeWorks) {
                System.out.printf(
                    "%s: %s%n",
                    homeWork.getTask(),
                    homeWork.getDeadline()
                );
            }
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void addHomeWork() {
        try {
            System.out.println("\nNew Home Works");
            int count = getCount();
            for (int i = 0; i < count; i++) {
                System.out.print("\nTask: ");
                String task = getFromScanner();
                System.out.print("Lection id: ");
                String lectionId = getFromScanner();
                homeWorkService.addHomeWork(
                    new HomeWork(task, getDeadLine()),
                    Integer.parseInt(lectionId)
                );
            }
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteHomeWork() {
        try {
            homeWorkService.deleteHomeWork(getId());
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showHomeWork() {
        try {
            Optional<HomeWork> homeWork = homeWorkService.getHomeWork(getId());
            homeWork.ifPresent(work -> System.out.printf(
                "%s: %s%n",
                work.getTask(),
                work.getDeadline()
            ));
        } catch (Exception e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}
