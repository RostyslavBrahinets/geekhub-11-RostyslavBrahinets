import work.Lection;

import java.util.Scanner;

public class UseCommandOfLections {
    private final Scanner scanner = new Scanner(System.in);

    public void showAllLections(Lection[] lections) {
        if (lections == null) {
            return;
        }

        for (Lection lection : lections) {
            System.out.println(lection.getName());
        }
    }

    public Lection[] addNewLection(Lection[] lections, String nameOfLection) {
        Lection[] newLections = new Lection[lections.length + 1];

        for (int i = 0; i < lections.length; i++) {
            newLections[i] = lections[i];
        }

        newLections[newLections.length - 1] = new Lection(nameOfLection);
        return newLections;
    }

    public Lection[] deleteLectionByNumber(Lection[] lections, String numberOfLection) {
        Lection[] newLections = new Lection[lections.length - 1];

        for (int i = 0; i < Integer.parseInt(numberOfLection); i++) {
            newLections[i] = lections[i];
        }

        for (int i = Integer.parseInt(numberOfLection) + 1; i < lections.length; i++) {
            newLections[i - 1] = lections[i];
        }

        return newLections;
    }

    public void showLectionByNumber(Lection[] lections, String numberOfLection) {
        System.out.println(lections[Integer.parseInt(numberOfLection)].getName());
    }

    public void exit() {
        scanner.close();
        System.exit(0);
    }
}
