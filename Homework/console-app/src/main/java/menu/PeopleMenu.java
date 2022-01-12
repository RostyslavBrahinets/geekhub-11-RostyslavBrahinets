package menu;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Person;
import services.PersonService;

import java.util.List;
import java.util.Optional;

public class PeopleMenu extends Menu {
    private final PersonService personService = new PersonService();

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
                person.getFirstName(),
                person.getLastName(),
                person.getContacts(),
                person.getGitHubNickname(),
                person.getRole()
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
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deletePerson() {
        try {
            personService.deletePerson(getId());
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showPerson() {
        try {
            Optional<Person> person = personService.getPerson(getId());
            if (person.isPresent()) {
                System.out.printf(
                    "%s %s, %s, %s, %s%n",
                    person.get().getFirstName(),
                    person.get().getLastName(),
                    person.get().getContacts(),
                    person.get().getGitHubNickname(),
                    person.get().getRole()
                );
            }
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}
