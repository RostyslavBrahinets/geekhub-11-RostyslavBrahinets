import exceptions.LessonNotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import work.Lection;

import java.util.Arrays;
import java.util.Scanner;

public class UseCommandOfLections {
    private final Scanner scanner = new Scanner(System.in);

    public void showAllLections(Lection[] lections) {
        for (Lection lection : lections) {
            System.out.println(lection.getName());
        }
    }

    public Lection[] addNewLection(Lection[] lections) {
        String nameOfLection = getNameOfLection();

        Lection[] newLections = new Lection[lections.length + 1];

        for (int i = 0; i < lections.length; i++) {
            newLections[i] = lections[i];
        }

        newLections[newLections.length - 1] = new Lection(nameOfLection);
        return newLections;
    }

    public Lection[] deleteLectionByNumber(Lection[] lections) {
        String numberOfLection;
        Lection[] newLections = new Lection[lections.length - 1];

        try {
            numberOfLection = getNumberOfLection(lections);

            for (int i = 0; i < Integer.parseInt(numberOfLection); i++) {
                newLections[i] = lections[i];
            }

            for (int i = Integer.parseInt(numberOfLection) + 1; i < lections.length; i++) {
                newLections[i - 1] = lections[i];
            }
        } catch (LessonNotFoundException e) {
            String message = "Number of lection is invalid!";
            Logger.error(getClass().getName(), message, e);
        }

        return newLections;
    }

    public void showLectionByNumber(Lection[] lections) {
        try {
            String numberOfLection = getNumberOfLection(lections);
            System.out.println(lections[Integer.parseInt(numberOfLection)].getName());
        } catch (LessonNotFoundException e) {
            String message = "Number of lection is invalid!";
            Logger.error(getClass().getName(), message, e);
        }
    }

    public void exit() {
        scanner.close();
        System.exit(0);
    }

    private String getNameOfLection() {
        System.out.print("Input new lection: ");
        String nameOfLection = scanner.nextLine();

        if (nameOfLection.isBlank()) {
            throw new ValidationException("Inputted Invalid Data");
        }

        return nameOfLection;
    }

    private String[] getArrayNumbersOfLections(Lection[] lections) {
        String[] numbersOfLections = new String[lections.length];

        for (int i = 0; i < lections.length; i++) {
            numbersOfLections[i] = String.valueOf(i);
        }

        return numbersOfLections;
    }

    private String getNumberOfLection(Lection[] lections) {
        System.out.print("Input number of lection: ");
        String numberOfLection = scanner.nextLine();

        if (Arrays.binarySearch(getArrayNumbersOfLections(lections), numberOfLection) < 0) {
            throw new LessonNotFoundException("Lesson Not Found");
        }

        return numberOfLection;
    }
}
