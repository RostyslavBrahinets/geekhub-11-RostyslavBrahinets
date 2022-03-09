package repository;

import models.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MethodsForRepository {
    public List<HomeWork> getHomeWorksByLections(
        Connection connection,
        int id
    ) throws SQLException {
        List<HomeWork> homeWorks = new ArrayList<>();
        String sql = "select * from homework where lection_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HomeWork homeWork = new HomeWork(
                    resultSet.getInt("id"),
                    resultSet.getString("task"),
                    resultSet.getTimestamp("deadline").toLocalDateTime()
                );
                homeWorks.add(homeWork);
            }
        }

        return homeWorks;
    }

    public List<Resource> getResourcesByLections(
        Connection connection,
        int id
    ) throws SQLException {
        List<Resource> resources = new ArrayList<>();
        String sql = "select * from resource where lection_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Resource resource = new Resource(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    ResourceType.valueOf(resultSet.getString("type")),
                    resultSet.getString("data")
                );
                resources.add(resource);
            }
        }

        return resources;
    }

    public List<Contact> getContactsByPeople(Connection connection, int id) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "select * from contacts where person_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

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

    public Optional<Person> getPerson(
        Connection connection,
        ResultSet resultSet,
        int id
    ) throws SQLException {
        Person person = null;
        if (resultSet.next()) {
            person = new Person(
                id,
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                getContactsByPeople(connection, id),
                resultSet.getString("git_hub_nickname"),
                Role.valueOf(resultSet.getString("role"))
            );
        }

        return Optional.ofNullable(person);
    }

    public Optional<Lection> getLection(
        Connection connection,
        ResultSet resultSet,
        int id,
        PersonRepository personRepository
    ) throws SQLException, IOException {
        Lection lection = null;

        if (resultSet.next()) {
            int lecturerId = resultSet.getInt("lecturer_id");
            List<Resource> resources = getResourcesByLections(connection, id);
            Optional<Person> lecturerOptional = personRepository.getPerson(lecturerId);
            Person lecturer = null;
            if (lecturerOptional.isPresent()) {
                lecturer = lecturerOptional.get();
            }
            List<HomeWork> homeWorks = getHomeWorksByLections(connection, id);

            lection = new Lection(
                id,
                resultSet.getString("name"),
                resultSet.getString("describe"),
                resources,
                lecturer,
                homeWorks,
                resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate()
            );
        }

        return Optional.ofNullable(lection);
    }
}
