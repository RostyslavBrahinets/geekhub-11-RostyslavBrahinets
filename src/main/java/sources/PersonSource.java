package sources;

import models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonSource {
    private static PersonSource instance;
    private final List<Person> people;

    public PersonSource() {
        this.people = new ArrayList<>();
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

    public Person getPerson(int id) {
        return people.get(id);
    }

    public static PersonSource getInstance() {
        if (instance == null) {
            instance = new PersonSource();
        }

        return instance;
    }
}
