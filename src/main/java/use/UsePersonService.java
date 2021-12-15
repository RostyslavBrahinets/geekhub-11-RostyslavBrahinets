package use;

import logger.Logger;
import services.PersonService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UsePersonService {
    private final Scanner scanner = new Scanner(System.in);

    public void addPeople(int countOfPerson) {
        PersonService personService = new PersonService();

        for (int i = 0; i < countOfPerson; i++) {
            personService.addPerson(
                getFirstNameOfPerson(),
                getLastNameOfPerson(),
                getContactsOfPerson(),
                getGitHubNicknameNameOfPerson(),
                getRoleOfPerson()
            );
        }
    }

    public void closeScanner() {
        scanner.close();
    }

    private String getFirstNameOfPerson() {
        System.out.print("\nInput first name of person: ");
        return scanner.nextLine();
    }

    private String getLastNameOfPerson() {
        System.out.print("Input last name of person: ");
        return scanner.nextLine();
    }

    private int getCountOf(String s) {
        System.out.printf("Input count of %s: ", s);
        int count;

        try {
            count = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            Logger.warning(getClass().getName(), String.format("Count of %s is invalid", s));
            count = 0;
            scanner.nextLine();
        }

        return count;
    }

    private List<String> getContactsOfPerson() {
        List<String> contacts = new ArrayList<>();
        for (int i = 0; i < getCountOf("contacts"); i++) {
            System.out.print("Input contact: ");
            contacts.add(scanner.nextLine());
        }

        return contacts;
    }

    private String getGitHubNicknameNameOfPerson() {
        System.out.print("Input GitHub nickname of person: ");
        return scanner.nextLine();
    }

    private String getRoleOfPerson() {
        System.out.print("Input role of person: ");
        return scanner.nextLine().toUpperCase();
    }
}
