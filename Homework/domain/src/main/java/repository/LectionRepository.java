package repository;

import db.DbConnectionProvider;
import models.HomeWork;
import models.Lection;
import models.Person;
import models.Resource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectionRepository {
    private final DbConnectionProvider dbConnectionProvider;
    private final PersonRepository personRepository;

    public LectionRepository(
        DbConnectionProvider dbConnectionProvider,
        PersonRepository personRepository
    ) {
        this.dbConnectionProvider = dbConnectionProvider;
        this.personRepository = personRepository;
    }

    public List<Lection> getLections() throws SQLException, IOException {
        List<Lection> lections = new ArrayList<>();

        try (
            Connection connection = dbConnectionProvider.getConnection();
            Statement statement = connection.createStatement()
        ) {
            String sql = "select * from lection";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int lecturerId = resultSet.getInt("lecturer_id");
                MethodsForRepository repository = new MethodsForRepository();
                List<Resource> resources = repository.getResourcesByLections(connection, id);
                Optional<Person> lecturerOptional = personRepository.getPerson(lecturerId);
                Person lecturer = null;
                if (lecturerOptional.isPresent()) {
                    lecturer = lecturerOptional.get();
                }
                List<HomeWork> homeWorks = repository.getHomeWorksByLections(connection, id);

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

    public void addLection(
        Lection lection,
        int lecturerId,
        int courseId
    ) throws SQLException, IOException {
        String sql = "insert into lection"
            + "(name, describe, lecturer_id, course_id)"
            + "values (?,?,?,?)";

        try (
            Connection connection = dbConnectionProvider.getConnection();
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
            Connection connection = dbConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Lection> getLection(int id) throws SQLException, IOException {
        Optional<Lection> lection;
        String sql = "select * from lection where id=?";

        try (
            Connection connection = dbConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            MethodsForRepository repository = new MethodsForRepository();
            lection = repository.getLection(connection, resultSet, id, personRepository);
        }

        return lection;
    }
}
