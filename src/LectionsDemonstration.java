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
                case "1" -> useCommand.showAllLectures(lections);
                case "2" -> changeLectures(useCommand.addNewLecture(lections));
                case "3" -> changeLectures(useCommand.deleteLectureByNumber(lections));
                case "4" -> useCommand.showLectureByNumber(lections);
                default -> {
                    scanner.close();
                    useCommand.exit();
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

    private static void changeLectures(Lection[] newLections) {
        lections = newLections;
    }
}
