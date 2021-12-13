import models.Lection;
import services.LectionService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UseCommandOfLections {
    private final Scanner scanner = new Scanner(System.in);
    LectionService lectionService = new LectionService();

    public void showAllLections() {
        for (Lection lection : lectionService.getLections()) {
            System.out.println(lection.getName());
        }
    }

    public void addNewLection() {
        Lection lection = new Lection(getNameOfLection());
        lectionService.addLection(lection);
    }

    public void deleteLectionByNumber() {
        lectionService.deleteLection(getNumberOfLection());
    }

    public void showLectionByNumber() {
        Lection lection = lectionService.getLection(getNumberOfLection());
        if (lection != null) {
            System.out.println(lection.getName());
        }
    }

    public void exit() {
        scanner.close();
        System.exit(0);
    }

    private String getNameOfLection() {
        System.out.print("Input new lection: ");
        return scanner.nextLine();
    }

    private int getNumberOfLection() {
        System.out.print("Input number of lection: ");
        int number;

        try {
            number = scanner.nextInt();
        } catch (InputMismatchException e) {
            number = -1;
            scanner.nextLine();
        }

        return number;
    }
}
