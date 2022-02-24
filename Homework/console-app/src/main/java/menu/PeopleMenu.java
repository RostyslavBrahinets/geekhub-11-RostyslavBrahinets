package menu;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Person;
import services.PersonService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PeopleMenu extends Menu {
    private final PersonService personService;

    public PeopleMenu(PersonService personService) {
        super();
        this.personService = personService;
    }

    @Override
    public void runMenu() throws SQLException, IOException {
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
            default -> throw new NotFoundException(COMMAND_NOT_FOUND);
        }
    }

    private void showPeople() throws SQLException, IOException {
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
                System.out.print("\nGitHub nickname: ");
                String nickname = getFromScanner();
                System.out.print("Role: ");
                String role = getFromScanner().toUpperCase();
                System.out.print("Role: ");
                String courseId = getFromScanner();
                personService.addPerson(firstName, lastName, nickname, role, Integer.parseInt(courseId));
            }
        } catch (InvalidArgumentException | ValidationException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void deletePerson() {
        try {
            personService.deletePerson(getId());
        } catch (NotFoundException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    private void showPerson() {
        try {
            Optional<Person> person = personService.getPerson(getId());
            person.ifPresent(value -> System.out.printf(
                "%s %s, %s, %s, %s%n",
                value.getFirstName(),
                value.getLastName(),
                value.getContacts(),
                value.getGitHubNickname(),
                value.getRole()
            ));
        } catch (NotFoundException | SQLException | IOException e) {
            logger.error(getClass().getName(), e.getMessage(), e);
        }
    }
}
