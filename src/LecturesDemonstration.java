import java.util.Arrays;
import java.util.Scanner;

public class LecturesDemonstration {
    static final Scanner scanner = new Scanner(System.in);
    static Lecture[] lectures = {new Lecture("Intro"), new Lecture("Basics"), new Lecture("OOP")};

    public static void main(String[] args) {
        while (true) {
            switch (getCommand()) {
                case "1" -> {
                    showAllLectures();
                }
                case "2" -> {
                    addNewLecture();
                }
                case "3" -> {
                    deleteLectureByNumber();
                }
                case "4" -> {
                    showLectureByNumber();
                }
                case "5" -> {
                    exit();
                }
            }
        }
    }

    private static String getCommand() {
        String[] commands = {"1", "2", "3", "4", "5"};
        String command;

        do {
            System.out.printf("%nInput command (1-5): ");
            command = scanner.nextLine();
        } while (Arrays.binarySearch(commands, command) < 0);

        return command;
    }

    private static void showAllLectures() {
        for (Lecture lecture : lectures) {
            System.out.println(lecture.getTitle());
        }
    }

    private static void addNewLecture() {
        System.out.print("Input new lecture: ");
        String titleOfLecture = scanner.nextLine();
        Lecture[] newLectures = new Lecture[lectures.length + 1];

        for (int i = 0; i < lectures.length; i++) {
            newLectures[i] = lectures[i];
        }

        newLectures[newLectures.length - 1] = new Lecture(titleOfLecture);
        lectures = newLectures;
    }

    private static void deleteLectureByNumber() {
        String numberOfLecture = getNumberOfLecture();

        Lecture[] newLectures = new Lecture[lectures.length - 1];

        for (int i = 0; i < Integer.parseInt(numberOfLecture); i++) {
            newLectures[i] = lectures[i];
        }

        for (int i = Integer.parseInt(numberOfLecture) + 1; i < lectures.length; i++) {
            newLectures[i - 1] = lectures[i];
        }

        lectures = newLectures;
    }

    private static void showLectureByNumber() {
        String numberOfLecture = getNumberOfLecture();
        System.out.println(lectures[Integer.parseInt(numberOfLecture)].getTitle());
    }

    private static void exit() {
        scanner.close();
        System.exit(0);
    }

    private static String[] getArrayNumbersOfLectures() {
        String[] numbersOfLectures = new String[lectures.length];

        for (int i = 0; i < lectures.length; i++) {
            numbersOfLectures[i] = String.valueOf(i);
        }

        return numbersOfLectures;
    }

    private static String getNumberOfLecture() {
        String numberOfLecture;

        do {
            System.out.print("Input number of lecture: ");
            numberOfLecture = scanner.nextLine();
        } while (Arrays.binarySearch(getArrayNumbersOfLectures(), numberOfLecture) < 0);

        return numberOfLecture;
    }
}
