import java.util.Arrays;
import java.util.Scanner;

public class Lectures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] commands = {1, 2, 3, 4, 5};
        int command;

        do {
            System.out.print("Input command (1-5): ");
            command = scanner.nextInt();
        } while (Arrays.binarySearch(commands, command) < 0);

        scanner.close();
    }
}
