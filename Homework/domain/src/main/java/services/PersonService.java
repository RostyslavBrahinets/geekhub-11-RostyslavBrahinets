package services;

import models.Person;
import models.Role;
import repository.PersonRepository;
import validators.PersonValidator;

import java.util.List;
import java.util.Optional;

public class PersonService {
    private final PersonRepository personSource = PersonRepository.getInstance();
    private final PersonValidator validator = new PersonValidator();

    public Optional<List<Person>> getPeople() {
        return Optional.ofNullable(personSource.getPeople());
    }

    public void addPerson(String firstName, String lastName, List<String> contacts,
                          String gitHubNickname, String role) {
        validator.validate(firstName, lastName, contacts, gitHubNickname, role);
        personSource.addPerson(new Person(firstName, lastName, contacts, gitHubNickname, Role.valueOf(role)));
    }

    public void deletePerson(int id) {
        validator.validate(id);
        personSource.deletePerson(id);
    }

    public Optional<Person> getPerson(int id) {
        validator.validate(id);
        return Optional.ofNullable(personSource.getPerson(id));
    }
}
