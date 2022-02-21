package repository;

import db.DataBaseConnector;
import models.Contact;
import models.Person;
import models.Role;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepository {
    private static PersonRepository instance;

    private PersonRepository() {
    }

    public static PersonRepository getInstance() {
        if (instance == null) {
            instance = new PersonRepository();
        }

        return instance;
    }

    public List<Person> getPeople() throws SQLException, IOException {
        List<Person> people = new ArrayList<>();

        try (
            Connection connection = DataBaseConnector.getConnection();
            Statement statement = connection.createStatement()
        ) {
            String sql = "select * from person";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                List<Contact> contacts = getContacts(connection, id);

                Person person = new Person(
                    id,
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    contacts,
                    resultSet.getString("git_hub_nickname"),
                    Role.valueOf(resultSet.getString("role"))
                );
                people.add(person);
            }
        }

        return people;
    }

    public void addPerson(Person person, int courseId) throws SQLException, IOException {
        String sql = "insert into person"
            + "(first_name, last_name, git_hub_nickname, role, course_id)"
            + "values (?,?,?,?,?)";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setString(3, person.getGitHubNickname());
            preparedStatement.setString(4, String.valueOf(person.getRole()));
            preparedStatement.setInt(5, courseId);
            preparedStatement.execute();
        }
    }

    public void deletePerson(int id) throws SQLException, IOException {
        String sql = "delete from person where id=?";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Person> getPerson(int id) throws SQLException, IOException {
        Person person;
        String sql = "select * from person where id=?";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            person = new Person(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                getContacts(connection, id),
                resultSet.getString("git_hub_nickname"),
                Role.valueOf(resultSet.getString("role"))
            );
        }

        return Optional.of(person);
    }

    private List<Contact> getContacts(Connection connection, int id) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "select * from contacts where person_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while (resultSet.next()) {
                Contact contact = new Contact(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
                );
                contacts.add(contact);
            }
        }

        return contacts;
    }
}
