package repository;

import db.DataBaseConnector;
import models.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectionRepository {
    private static LectionRepository instance;

    private LectionRepository() {
    }

    public static LectionRepository getInstance() {
        if (instance == null) {
            instance = new LectionRepository();
        }

        return instance;
    }

    public List<Lection> getLections() throws SQLException, IOException {
        List<Lection> lections = new ArrayList<>();

        try (
            Connection connection = DataBaseConnector.getConnection();
            Statement statement = connection.createStatement()
        ) {
            String sql = "select * from lection";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                PersonRepository personRepository = PersonRepository.getInstance();
                int id = resultSet.getInt("id");
                int lecturerId = resultSet.getInt("lecturer_id");
                List<Resource> resources = getResources(connection, id);
                Optional<Person> lecturerOptional = personRepository.getPerson(lecturerId);
                Person lecturer = null;
                if (lecturerOptional.isPresent()) {
                    lecturer = lecturerOptional.get();
                }
                List<HomeWork> homeWorks = getHomeWorks(connection, id);

                Lection lection = new Lection(
                    id,
                    resultSet.getString("name"),
                    resultSet.getString("describe"),
                    resources,
                    lecturer,
                    homeWorks,
                    resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate()
                );
                lections.add(lection);
            }
        }

        return lections;
    }

    public void addLection(Lection lection, int lecturerId, int courseId) throws SQLException, IOException {
        String sql = "insert into lection"
            + "(name, describe, lecturer_id, course_id)"
            + "values (?,?,?,?)";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, lection.getName());
            preparedStatement.setString(2, lection.getDescribe());
            preparedStatement.setInt(3, lecturerId);
            preparedStatement.setInt(4, courseId);
            preparedStatement.execute();
        }
    }

    public void deleteLection(int id) throws SQLException, IOException {
        String sql = "delete from lection where id=?";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Lection> getLection(int id) throws SQLException, IOException {
        Lection lection = null;
        String sql = "select * from lection where id=?";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                PersonRepository personRepository = PersonRepository.getInstance();
                int lecturerId = resultSet.getInt("lecturer_id");
                List<Resource> resources = getResources(connection, id);
                Optional<Person> lecturerOptional = personRepository.getPerson(lecturerId);
                Person lecturer = null;
                if (lecturerOptional.isPresent()) {
                    lecturer = lecturerOptional.get();
                }
                List<HomeWork> homeWorks = getHomeWorks(connection, id);

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
        }

        return Optional.ofNullable(lection);
    }

    private List<Resource> getResources(Connection connection, int id) throws SQLException {
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

    private List<HomeWork> getHomeWorks(Connection connection, int id) throws SQLException {
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
}
