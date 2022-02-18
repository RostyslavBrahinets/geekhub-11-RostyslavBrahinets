package repository;

import models.HomeWork;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomeWorkRepository {
    private static HomeWorkRepository instance;
    private final Connection connection;

    private HomeWorkRepository() throws SQLException, IOException {
        connection = Connector.getConnection();
    }

    public static HomeWorkRepository getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new HomeWorkRepository();
        }

        return instance;
    }

    public List<HomeWork> getHomeWorks() throws SQLException {
        List<HomeWork> homeWorks = new ArrayList<>();

        Statement statement = connection.createStatement();
        String sql = "select * from homework";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            HomeWork homeWork = new HomeWork(
                resultSet.getInt("id"),
                resultSet.getString("task"),
                resultSet.getTimestamp("deadline").toLocalDateTime()
            );
            homeWorks.add(homeWork);
        }

        return homeWorks;
    }

    public void addHomeWork(HomeWork homeWork) throws SQLException {
        String sql = "insert into homework(task, deadline) values (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, homeWork.task());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(homeWork.deadline()));
        preparedStatement.execute();
    }

    public void deleteHomeWork(int id) throws SQLException {
        String sql = "delete from homework where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Optional<HomeWork> getHomeWork(int id) throws SQLException {
        return Optional.ofNullable(getHomeWorks().get(id));
    }
}
