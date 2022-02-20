package repository;

import models.Person;
import models.Role;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonRepository {
    private static PersonRepository instance;
    private final Connection connection;

    private PersonRepository() throws SQLException, IOException {
        connection = Connector.getConnection();
    }

    public static PersonRepository getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new PersonRepository();
        }

        return instance;
    }

    public List<Person> getPeople() throws SQLException {
        List<Person> people = new ArrayList<>();

        Statement statement = connection.createStatement();
        String sql = "select * from person";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Person person = new Person(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                /*resultSet.getInt("contacts"),*/
                List.of(),
                resultSet.getString("git_hub_nickname"),
                Role.valueOf(resultSet.getString("role"))
            );
            people.add(person);
        }

        return people;
    }

    public void addPerson(Person person) throws SQLException {
        String sql = "insert into person"
            + "(first_name, last_name, contact_id, git_hub_nickname, role)"
            + "values (?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getLastName());
//        preparedStatement.setInt(3, person.contacts());
        preparedStatement.setInt(3, 1);
        preparedStatement.setString(4, person.getGitHubNickname());
        preparedStatement.setString(5, String.valueOf(person.getRole()));
        preparedStatement.execute();
    }

    public void deletePerson(int id) throws SQLException {
        String sql = "delete from person where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Optional<Person> getPerson(int id) throws SQLException {
        return Optional.ofNullable(getPeople().get(id));
    }
}
