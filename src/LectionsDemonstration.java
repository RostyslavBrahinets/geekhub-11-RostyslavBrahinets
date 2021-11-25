import exceptions.CommandNotFoundException;
import logger.LogType;
import logger.Logger;
import work.Lection;

import java.util.Arrays;
import java.util.Scanner;

public class LectionsDemonstration {
    static final Scanner scanner = new Scanner(System.in);
    static Lection[] lections = {new Lection("Intro"),
        new Lection("Basics"),
        new Lection("OOP")};

    public static void main(String[] args) {
        UseCommandOfLections useCommand = new UseCommandOfLections();

        while (true) {
            switch (getCommand()) {
                case "1" -> useCommand.showAllLections(lections);
                case "2" -> changeLections(useCommand.addNewLection(lections));
                case "3" -> changeLections(useCommand.deleteLectionByNumber(lections));
                case "4" -> useCommand.showLectionByNumber(lections);
                case "5" -> {
                    scanner.close();
                    useCommand.exit();
                }
                default -> Logger.showAllLogs();
            }
        }
    }

    private static String getCommand() {
        String[] commands = {"1", "2", "3", "4", "5", "6"};
        String command = null;
        boolean run;

        do {
            try {
                run = false;
                System.out.printf("%nInput command (1-6): ");
                command = scanner.nextLine();

                if (Arrays.binarySearch(commands, command) < 0) {
                    throw new CommandNotFoundException("Command Not Found");
                }
            } catch (CommandNotFoundException e) {
                run = true;
                String message = "'" + command + "' is invalid command!";
                Logger.log(LogType.ERROR, LectionsDemonstration.class.getName(), message, e);
            }
        } while (run);

        return command;
    }

    private static void changeLections(Lection[] newLections) {
        lections = newLections;
    }
}
