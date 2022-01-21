package repository;

import models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepository {
    private static PersonRepository instance;
    private final List<Person> people = new ArrayList<>();

    private PersonRepository() {
    }

    public List<Person> getPeople() {
        return people;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void deletePerson(int id) {
        people.remove(id);
    }

    public Optional<Person> getPerson(int id) {
        return Optional.ofNullable(people.get(id));
    }

    public static PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }

        return instance;
    }
}
