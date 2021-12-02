import exceptions.CommandNotFoundException;
import exceptions.LessonNotFoundException;
import exceptions.ValidationException;
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

        String message = "\n1 - show all lections\n2 - add new lection\n3 - delete lection by number\n" +
            "4 - show lection by number\n5 - exit\n6 - show all logs";
        Logger.info(LectionsDemonstration.class.getName(), message);

        while (true) {
            try {
                switch (getCommand()) {
                    case "1" -> useCommand.showAllLections(lections);
                    case "2" -> changeLections(useCommand.addNewLection(lections, getNameOfLection()));
                    case "3" -> changeLections(useCommand.deleteLectionByNumber(lections, getNumberOfLection(lections)));
                    case "4" -> useCommand.showLectionByNumber(lections, getNumberOfLection(lections));
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
            } catch (LessonNotFoundException e) {
                message = "Number of lection is invalid!";
                Logger.error(LectionsDemonstration.class.getName(), message, e);
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

    private static String getNameOfLection() {
        System.out.print("Input new lection: ");
        String nameOfLection = scanner.nextLine();

        if (nameOfLection.isBlank()) {
            throw new ValidationException("Inputted Invalid Data");
        }

        return nameOfLection;
    }

    private static String[] getArrayNumbersOfLections(Lection[] lections) {
        String[] numbersOfLections = new String[lections.length];

        for (int i = 0; i < lections.length; i++) {
            numbersOfLections[i] = String.valueOf(i);
        }

        return numbersOfLections;
    }

    private static String getNumberOfLection(Lection[] lections) {
        System.out.print("Input number of lection: ");
        String numberOfLection = scanner.nextLine();

        if (Arrays.binarySearch(getArrayNumbersOfLections(lections), numberOfLection) < 0) {
            throw new LessonNotFoundException("Lesson Not Found");
        }

        return numberOfLection;
    }
}
