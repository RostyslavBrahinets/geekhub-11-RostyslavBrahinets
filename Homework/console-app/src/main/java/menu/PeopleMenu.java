package menu;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Person;
import services.PersonService;

import java.util.List;
import java.util.Optional;

public class PeopleMenu extends Menu {
    private final PersonService personService = new PersonService();

    public PeopleMenu() {
        super();
    }

    @Override
    public void runMenu() {
        System.out.println(
            """

                ###################################

                People Menu
                1 - Show People
                2 - Add Person
                3 - Delete Person
                4 - Show Person"""
        );

        switch (getCommand()) {
            case "1" -> showPeople();
            case "2" -> addPerson();
            case "3" -> deletePerson();
            case "4" -> showPerson();
            default -> throw new NotFoundException("Command not found");
        }
    }

    private void showPeople() {
        List<Person> people = personService.getPeople();
        for (Person person : people) {
            System.out.printf(
                "%s %s, %s, %s, %s%n",
                person.firstName(),
                person.lastName(),
                person.contacts(),
                person.gitHubNickname(),
                person.role()
            );
        }
    }

    public void addPerson() {
        try {
            System.out.println("\nNew People");
            int count = getCount();
            for (int i = 0; i < count; i++) {
                System.out.print("\nFirst name: ");
                String firstName = getFromScanner();
                System.out.print("Last name: ");
                String lastName = getFromScanner();
                List<String> contacts = getContacts();
                System.out.print("\nGitHub nickname: ");
                String nickname = getFromScanner();
                System.out.print("Role: ");
                String role = getFromScanner().toUpperCase();
                personService.addPerson(firstName, lastName, contacts, nickname, role);
            }
        } catch (InvalidArgumentException | ValidationException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deletePerson() {
        try {
            personService.deletePerson(getId());
        } catch (NotFoundException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showPerson() {
        try {
            Optional<Person> person = personService.getPerson(getId());
            person.ifPresent(value -> System.out.printf(
                "%s %s, %s, %s, %s%n",
                value.firstName(),
                value.lastName(),
                value.contacts(),
                value.gitHubNickname(),
                value.role()
            ));
        } catch (NotFoundException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}
