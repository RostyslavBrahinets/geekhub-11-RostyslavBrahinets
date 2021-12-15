package use;

import services.PersonService;

import java.util.ArrayList;
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

    private List<String> getContactsOfPerson() {
        System.out.print("\nInput count of contacts: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        List<String> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
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
