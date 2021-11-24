import java.util.Arrays;
import java.util.Scanner;

public class UseCommandOfLections {
    private final Scanner scanner = new Scanner(System.in);

    public void showAllLectures(Lection[] lections) {
        for (Lection lection : lections) {
            System.out.println(lection.getName());
        }
    }

    public Lection[] addNewLecture(Lection[] lections) {
        System.out.print("Input new lecture: ");
        String titleOfLecture = scanner.nextLine();
        Lection[] newLections = new Lection[lections.length + 1];

        for (int i = 0; i < lections.length; i++) {
            newLections[i] = lections[i];
        }

        newLections[newLections.length - 1] = new Lection(titleOfLecture);
        return newLections;
    }

    public Lection[] deleteLectureByNumber(Lection[] lections) {
        String numberOfLecture = getNumberOfLecture(lections);
        Lection[] newLections = new Lection[lections.length - 1];

        for (int i = 0; i < Integer.parseInt(numberOfLecture); i++) {
            newLections[i] = lections[i];
        }

        for (int i = Integer.parseInt(numberOfLecture) + 1; i < lections.length; i++) {
            newLections[i - 1] = lections[i];
        }

        return newLections;
    }

    public void showLectureByNumber(Lection[] lections) {
        String numberOfLecture = getNumberOfLecture(lections);
        System.out.println(lections[Integer.parseInt(numberOfLecture)].getName());
    }

    public void exit() {
        scanner.close();
        System.exit(0);
    }

    public String[] getArrayNumbersOfLectures(Lection[] lections) {
        String[] numbersOfLectures = new String[lections.length];

        for (int i = 0; i < lections.length; i++) {
            numbersOfLectures[i] = String.valueOf(i);
        }

        return numbersOfLectures;
    }

    public String getNumberOfLecture(Lection[] lections) {
        String numberOfLecture;

        do {
            System.out.print("Input number of lecture: ");
            numberOfLecture = scanner.nextLine();
        } while (Arrays.binarySearch(getArrayNumbersOfLectures(lections), numberOfLecture) < 0);

        return numberOfLecture;
    }
}
