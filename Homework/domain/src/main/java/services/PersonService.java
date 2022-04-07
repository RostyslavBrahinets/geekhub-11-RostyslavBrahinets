package services;

import models.Person;
import models.Role;
import repository.PersonRepository;
import validators.PersonValidator;

import java.util.List;
import java.util.Optional;

public class PersonService {
    private final PersonRepository personRepository;
    private final PersonValidator validator;

    public PersonService(
        PersonRepository personRepository,
        PersonValidator validator
    ) {
        this.personRepository = personRepository;
        this.validator = validator;
    }

    public List<Person> getPeople() {
        return personRepository.getPeople();
    }

    public void addPerson(
        String firstName,
        String lastName,
        String gitHubNickname,
        String role,
        int courseId
    ) {
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

    public void deletePerson(int id) {
        validator.validate(id);
        personRepository.deletePerson(id);
    }

    public Optional<Person> getPerson(int id) {
        validator.validate(id);
        return personRepository.getPerson(id);
    }
}
