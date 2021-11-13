import java.util.Arrays;
import java.util.Scanner;

public class Lectures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] commands = {"1", "2", "3", "4", "5"};
        String command;

        String[] lectures = {"Intro", "Basics", "Object-oriented Programming", "Error Propagation and Handling"};

        while (true) {
            do {
                System.out.print("Input command (1-5): ");
                command = scanner.nextLine();
            } while (Arrays.binarySearch(commands, command) < 0);

            if (command.equals("1")) {
                for (String lecture : lectures) {
                    System.out.println(lecture);
                }
                System.out.println();
            } else if (command.equals("2")) {
                System.out.print("Input new lecture: ");
                String lecture = scanner.nextLine();
                String[] newLectures = new String[lectures.length + 1];

                for (int i = 0; i < lectures.length; i++) {
                    newLectures[i] = lectures[i];
                }

                newLectures[newLectures.length - 1] = lecture;
                lectures = newLectures;
                System.out.println();
            } else if (command.equals("3")) {
                String[] numbersOfLectures = new String[lectures.length];

                for (int i = 0; i < lectures.length; i++) {
                    numbersOfLectures[i] = String.valueOf(i);
                }

                String numberOfLecture;

                do {
                    System.out.print("Input number of lecture: ");
                    numberOfLecture = scanner.nextLine();
                } while (Arrays.binarySearch(numbersOfLectures, numberOfLecture) < 0);

                String[] newLectures = new String[lectures.length - 1];

                for (int i = 0; i < Integer.parseInt(numberOfLecture); i++) {
                    newLectures[i] = lectures[i];
                }

                for (int i = Integer.parseInt(numberOfLecture) + 1; i < lectures.length; i++) {
                    newLectures[i - 1] = lectures[i];
                }

                lectures = newLectures;
                System.out.println();
            } else if (command.equals("4")) {
                String[] numbersOfLectures = new String[lectures.length];

                for (int i = 0; i < lectures.length; i++) {
                    numbersOfLectures[i] = String.valueOf(i);
                }

                String numberOfLecture;

                do {
                    System.out.print("Input number of lecture: ");
                    numberOfLecture = scanner.nextLine();
                } while (Arrays.binarySearch(numbersOfLectures, numberOfLecture) < 0);

                System.out.println(lectures[Integer.parseInt(numberOfLecture)]);
                System.out.println();
            } else if (command.equals("5")) {
                scanner.close();
                System.exit(0);
            }
        }
    }
}
