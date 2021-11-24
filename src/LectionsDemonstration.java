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
                default -> {
                    scanner.close();
                    useCommand.exit();
                }
            }
        }
    }

    private static String getCommand() {
        String[] commands = {"1", "2", "3", "4", "5"};
        String command = null;

        do {
            if (command != null) {
                String message = "'" + command + "'" + " is invalid command";
                Logger.log(LogType.ERROR, LectionsDemonstration.class.getName(), message);
            }

            System.out.printf("%nInput command (1-5): ");
            command = scanner.nextLine();
        } while (Arrays.binarySearch(commands, command) < 0);

        return command;
    }

    private static void changeLections(Lection[] newLections) {
        lections = newLections;
    }
}
