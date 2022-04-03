package repository;

import models.Contact;
import models.Person;
import models.Role;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepository {
    private final DataSource dataSource;

    public PersonRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Person> getPeople() throws SQLException {
        List<Person> people = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()
        ) {
            String sql = "select * from person";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                MethodsForRepository repository = new MethodsForRepository();
                Optional<Person> person = repository.getPerson(connection, resultSet, id);
                person.ifPresent(people::add);
            }
        }

        return people;
    }

    public void addPerson(Person person, int courseId) throws SQLException {
        String sql = "insert into person"
            + "(first_name, last_name, git_hub_nickname, role, course_id)"
            + "values (?,?,?,?,?)";

        try (
            Connection connection = dataSource.getConnection();
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

    public void deletePerson(int id) throws SQLException {
        String sql = "delete from person where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Person> getPerson(int id) throws SQLException {
        Person person = null;
        String sql = "select * from person where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            MethodsForRepository repository = new MethodsForRepository();
            List<Contact> contacts = repository.getContactsByPeople(connection, id);
            if (resultSet.next()) {
                person = new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    contacts,
                    resultSet.getString("git_hub_nickname"),
                    Role.valueOf(resultSet.getString("role"))
                );
            }
        }

        return Optional.ofNullable(person);
    }
}
