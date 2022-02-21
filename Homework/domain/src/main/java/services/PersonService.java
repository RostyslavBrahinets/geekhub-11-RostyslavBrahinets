package services;

import models.Person;
import models.Role;
import repository.PersonRepository;
import validators.PersonValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PersonService {
    private final PersonRepository personRepository;
    private final PersonValidator validator;

    public PersonService() throws SQLException {
        personRepository = PersonRepository.getInstance();
        validator = new PersonValidator();
    }

    public List<Person> getPeople() throws SQLException, IOException {
        return personRepository.getPeople();
    }

    public void addPerson(
        String firstName,
        String lastName,
        String gitHubNickname,
        String role,
        int courseId
    ) throws SQLException, IOException {
        validator.validate(
            firstName,
            lastName,
            gitHubNickname,
            role
        );
        personRepository.addPerson(
            new Person(
                firstName,
                lastName,
                gitHubNickname,
                Role.valueOf(role)
            ),
            courseId
        );
    }

    public void deletePerson(int id) throws SQLException, IOException {
        validator.validate(id);
        personRepository.deletePerson(id);
    }

    public Optional<Person> getPerson(int id) throws SQLException, IOException {
        validator.validate(id);
        return personRepository.getPerson(id);
    }
}
