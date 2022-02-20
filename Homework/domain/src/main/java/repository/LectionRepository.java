package repository;

import models.Lection;
import models.Person;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LectionRepository {
    private static LectionRepository instance;
    private final Connection connection;

    private LectionRepository() throws SQLException, IOException {
        connection = Connector.getConnection();
    }

    public static LectionRepository getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new LectionRepository();
        }

        return instance;
    }

    public List<Lection> getLections() throws SQLException {
        List<Lection> lections = new ArrayList<>();

        Statement statement = connection.createStatement();
        String sql = "select * from lection";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Lection lection = new Lection(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("describe"),
                List.of(),
                new Person(),
                List.of(),
                /*resultSet.getInt("resource_id"),
                resultSet.getInt("person_id"),
                resultSet.getInt("homework_id"),*/
                LocalDate.now()
            );
            lections.add(lection);
        }

        return lections;
    }

    public void addLection(Lection lection) throws SQLException {
        String sql = "insert into lection("
            + "name, describe, resource_id, person_id, homework_id)"
            + "values (?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, lection.getName());
        preparedStatement.setString(2, lection.getDescribe());
        preparedStatement.setInt(3, 1);
        preparedStatement.setInt(5, 1);
//        preparedStatement.setInt(3, lection.resources());
        preparedStatement.setInt(4, lection.getLecturer().getId());
//        preparedStatement.setInt(5, lection.homeWorks());
        preparedStatement.execute();
    }

    public void deleteLection(int id) throws SQLException {
        String sql = "delete from lection where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Optional<Lection> getLection(int id) throws SQLException {
        return Optional.ofNullable(getLections().get(id));
    }
}
