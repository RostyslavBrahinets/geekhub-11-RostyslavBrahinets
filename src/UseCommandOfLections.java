import logger.LogType;
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
        System.out.print("Input new lection: ");
        String nameOfLection = scanner.nextLine();
        Lection[] newLections = new Lection[lections.length + 1];

        for (int i = 0; i < lections.length; i++) {
            newLections[i] = lections[i];
        }

        newLections[newLections.length - 1] = new Lection(nameOfLection);
        return newLections;
    }

    public Lection[] deleteLectionByNumber(Lection[] lections) {
        String numberOfLection = getNumberOfLection(lections);
        Lection[] newLections = new Lection[lections.length - 1];

        for (int i = 0; i < Integer.parseInt(numberOfLection); i++) {
            newLections[i] = lections[i];
        }

        for (int i = Integer.parseInt(numberOfLection) + 1; i < lections.length; i++) {
            newLections[i - 1] = lections[i];
        }

        return newLections;
    }

    public void showLectionByNumber(Lection[] lections) {
        String numberOfLection = getNumberOfLection(lections);
        System.out.println(lections[Integer.parseInt(numberOfLection)].getName());
    }

    public void exit() {
        scanner.close();
        System.exit(0);
    }

    public String[] getArrayNumbersOfLections(Lection[] lections) {
        String[] numbersOfLections = new String[lections.length];

        for (int i = 0; i < lections.length; i++) {
            numbersOfLections[i] = String.valueOf(i);
        }

        return numbersOfLections;
    }

    public String getNumberOfLection(Lection[] lections) {
        String numberOfLection = null;

        do {
            if (numberOfLection != null) {
                String message = "'" + numberOfLection + "' is invalid number of lection";
                Logger.log(LogType.ERROR, getClass().getName(), message);
            }

            System.out.print("Input number of lection: ");
            numberOfLection = scanner.nextLine();
        } while (Arrays.binarySearch(getArrayNumbersOfLections(lections), numberOfLection) < 0);

        return numberOfLection;
    }
}
