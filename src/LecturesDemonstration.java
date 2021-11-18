import java.util.Arrays;
import java.util.Scanner;

public class LecturesDemonstration {
    static final Scanner scanner = new Scanner(System.in);
    static Lecture[] lectures = {new Lecture("Intro", "", null, null, null),
        new Lecture("Basics", "", null, null, null),
        new Lecture("OOP", "", null, null, null)};

    public static void main(String[] args) {
        UseCommandOfLectures useCommand = new UseCommandOfLectures();

        while (true) {
            switch (getCommand()) {
                case "1" -> useCommand.showAllLectures(lectures);
                case "2" -> changeLectures(useCommand.addNewLecture(lectures));
                case "3" -> changeLectures(useCommand.deleteLectureByNumber(lectures));
                case "4" -> useCommand.showLectureByNumber(lectures);
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

    private static void changeLectures(Lecture[] newLectures) {
        lectures = newLectures;
    }
}
