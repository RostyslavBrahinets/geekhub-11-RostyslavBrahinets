package menu;

import exceptions.NotFoundException;
import logger.LoggerIncluder;

import java.util.Scanner;

public class LoggerMenu extends Menu {
    public LoggerMenu() {
        super();
    }

    @Override
    public void runMenu() {
        System.out.println(
            """

                ###################################

                Logger Menu
                Choose where to store logs:
                1 - In Memory
                2 - In File
                3 - In Memory And File"""
        );

        switch (getCommand()) {
            case "1" -> LoggerIncluder.setLogger("memory");
            case "2" -> LoggerIncluder.setLogger("file");
            case "3" -> LoggerIncluder.setLogger("memory_file");
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    @Override
    protected String getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInput command (1-3): ");
        return scanner.nextLine();
    }
}
