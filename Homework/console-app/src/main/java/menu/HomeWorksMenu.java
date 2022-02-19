package menu;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.HomeWork;
import services.HomeWorkService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HomeWorksMenu extends Menu {
    private final HomeWorkService homeWorkService;

    public HomeWorksMenu() throws SQLException, IOException {
        super();
        homeWorkService = new HomeWorkService();
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
                    homeWork.task(),
                    homeWork.deadline()
                );
            }
        } catch (SQLException e) {
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
                homeWorkService.addHomeWork(task, getDeadLine());
            }
        } catch (ValidationException | SQLException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deleteHomeWork() {
        try {
            homeWorkService.deleteHomeWork(getId());
        } catch (NotFoundException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showHomeWork() {
        try {
            Optional<HomeWork> homeWork = homeWorkService.getHomeWork(getId());
            homeWork.ifPresent(work -> System.out.printf(
                "%s: %s%n",
                work.task(),
                work.deadline()
            ));
        } catch (NotFoundException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}
