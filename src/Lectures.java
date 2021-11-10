import java.util.Arrays;
import java.util.Scanner;

public class Lectures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] commands = {1, 2, 3, 4, 5};
        int command;

        String[] lectures = {"Intro", "Basics", "Object-oriented Programming", "Error Propagation and Handling"};

        while (true) {
            do {
                System.out.print("Input command (1-5): ");
                command = scanner.nextInt();
            } while (Arrays.binarySearch(commands, command) < 0);

            if (command == 1) {
                for (String lecture : lectures) {
                    System.out.println(lecture);
                }
                System.out.println();
            } else if (command == 2) {
                System.out.print("Input new lecture: ");
                String lecture = scanner.next();
                String[] newLectures = new String[lectures.length + 1];

                for (int i = 0; i < lectures.length; i++) {
                    newLectures[i] = lectures[i];
                }

                newLectures[newLectures.length - 1] = lecture;
                lectures = newLectures;
                System.out.println();
            } else if (command == 3) {
                int numberOfLecture;

                do {
                    System.out.print("Input number of lecture: ");
                    numberOfLecture = scanner.nextInt();
                } while (numberOfLecture < 0 || numberOfLecture >= lectures.length);

                String[] newLectures = new String[lectures.length - 1];

                for (int i = 0; i < numberOfLecture; i++) {
                    newLectures[i] = lectures[i];
                }

                for (int i = numberOfLecture + 1; i < lectures.length; i++) {
                    newLectures[i - 1] = lectures[i];
                }

                lectures = newLectures;
                System.out.println();
            } else if (command == 4) {
                int numberOfLecture;

                do {
                    System.out.print("Input number of lecture: ");
                    numberOfLecture = scanner.nextInt();
                } while (numberOfLecture < 0 || numberOfLecture >= lectures.length);

                System.out.println(lectures[numberOfLecture]);
                System.out.println();
            } else if (command == 5) {
                scanner.close();
                System.exit(0);
            }
        }
    }
}
