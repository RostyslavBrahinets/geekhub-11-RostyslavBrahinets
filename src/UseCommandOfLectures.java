import java.util.Arrays;
import java.util.Scanner;

public class UseCommandOfLectures {
    private final Scanner scanner = new Scanner(System.in);

    public void showAllLectures(Lecture[] lectures) {
        for (Lecture lecture : lectures) {
            System.out.println(lecture.getTitle());
        }
    }

    public Lecture[] addNewLecture(Lecture[] lectures) {
        System.out.print("Input new lecture: ");
        String titleOfLecture = scanner.nextLine();
        Lecture[] newLectures = new Lecture[lectures.length + 1];

        for (int i = 0; i < lectures.length; i++) {
            newLectures[i] = lectures[i];
        }

        newLectures[newLectures.length - 1] = new Lecture(titleOfLecture, "",
            null, null, null);
        return newLectures;
    }

    public Lecture[] deleteLectureByNumber(Lecture[] lectures) {
        String numberOfLecture = getNumberOfLecture(lectures);
        Lecture[] newLectures = new Lecture[lectures.length - 1];

        for (int i = 0; i < Integer.parseInt(numberOfLecture); i++) {
            newLectures[i] = lectures[i];
        }

        for (int i = Integer.parseInt(numberOfLecture) + 1; i < lectures.length; i++) {
            newLectures[i - 1] = lectures[i];
        }

        return newLectures;
    }

    public void showLectureByNumber(Lecture[] lectures) {
        String numberOfLecture = getNumberOfLecture(lectures);
        System.out.println(lectures[Integer.parseInt(numberOfLecture)].getTitle());
    }

    public void exit() {
        scanner.close();
        System.exit(0);
    }

    public String[] getArrayNumbersOfLectures(Lecture[] lectures) {
        String[] numbersOfLectures = new String[lectures.length];

        for (int i = 0; i < lectures.length; i++) {
            numbersOfLectures[i] = String.valueOf(i);
        }

        return numbersOfLectures;
    }

    public String getNumberOfLecture(Lecture[] lectures) {
        String numberOfLecture;

        do {
            System.out.print("Input number of lecture: ");
            numberOfLecture = scanner.nextLine();
        } while (Arrays.binarySearch(getArrayNumbersOfLectures(lectures), numberOfLecture) < 0);

        return numberOfLecture;
    }
}
