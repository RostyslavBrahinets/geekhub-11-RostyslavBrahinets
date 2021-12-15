package services;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import logger.Logger;
import models.Person;
import models.Role;
import sources.PersonSource;
import validators.PersonValidator;

import java.util.List;

public class PersonService {
    private final PersonSource personSource = PersonSource.getInstance();
    private final PersonValidator validator = new PersonValidator();

    public List<Person> getPeople() {
        return personSource.getPeople();
    }

    public void addPerson(String firstName, String lastName, List<String> contacts,
                          String gitHubNickname, String role) {
        try {
            validator.validate(firstName, lastName, contacts, gitHubNickname, role);
            personSource.addPerson(new Person(firstName, lastName, contacts, gitHubNickname,
                Role.valueOf(role)));
        } catch (ValidationException | InvalidArgumentException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public void deletePerson(int id) {
        try {
            validator.validate(id);
            personSource.deletePerson(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }
    }

    public Person getPerson(int id) {
        Person person = null;

        try {
            validator.validate(id);
            person = personSource.getPerson(id);
        } catch (NotFoundException e) {
            Logger.error(getClass().getName(), e.getMessage(), e);
        }

        return person;
    }
}
