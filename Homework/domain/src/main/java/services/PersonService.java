package services;

import models.Person;
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

    public Optional<Person> addPerson(Person person, int courseId) {
        validator.validate(person);
        personRepository.addPerson(person, courseId);
        return Optional.of(person);
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
