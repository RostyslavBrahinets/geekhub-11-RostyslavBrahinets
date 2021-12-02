import exceptions.CommandNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import work.Lection;

import java.util.Scanner;

public class LectionsDemonstration {
    static final Scanner scanner = new Scanner(System.in);
    static Lection[] lections = {new Lection("Intro"),
        new Lection("Basics"),
        new Lection("OOP")};

    public static void main(String[] args) {
        UseCommandOfLections useCommand = new UseCommandOfLections();

        String message = "\n1 - show all lections\n2 - add new lection\n3 - delete lection by number\n" +
            "4 - show lection by number\n5 - exit\n6 - show all logs";
        Logger.info(LectionsDemonstration.class.getName(), message);

        while (true) {
            try {
                switch (getCommand()) {
                    case "1" -> useCommand.showAllLections(lections);
                    case "2" -> changeLections(useCommand.addNewLection(lections));
                    case "3" -> changeLections(useCommand.deleteLectionByNumber(lections));
                    case "4" -> useCommand.showLectionByNumber(lections);
                    case "5" -> {
                        scanner.close();
                        useCommand.exit();
                    }
                    case "6" -> Logger.showAllLogs();
                    default -> throw new CommandNotFoundException("Command Not Found");
                }
            } catch (CommandNotFoundException e) {
                message = "Invalid command!";
                Logger.error(LectionsDemonstration.class.getName(), message, e);
            } catch (ValidationException e) {
                message = "Name of lection is invalid!";
                Logger.error(LectionsDemonstration.class.getName(), message);
            }
        }
    }

    private static String getCommand() {
        System.out.printf("%nInput command (1-6): ");
        return scanner.nextLine();
    }

    private static void changeLections(Lection[] newLections) {
        lections = newLections;
    }
}