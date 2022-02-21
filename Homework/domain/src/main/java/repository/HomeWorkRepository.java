package repository;

import db.DataBaseConnector;
import models.HomeWork;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomeWorkRepository {
    private static HomeWorkRepository instance;

    private HomeWorkRepository() {
    }

    public static HomeWorkRepository getInstance() {
        if (instance == null) {
            instance = new HomeWorkRepository();
        }

        return instance;
    }

    public List<HomeWork> getHomeWorks() throws SQLException, IOException {
        List<HomeWork> homeWorks = new ArrayList<>();

        try (
            Connection connection = DataBaseConnector.getConnection();
            Statement statement = connection.createStatement()
        ) {
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
        }

        return homeWorks;
    }

    public void addHomeWork(HomeWork homeWork, int lectionId) throws SQLException, IOException {
        String sql = "insert into homework(task, deadline, lection_id) values (?,?,?)";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, homeWork.getTask());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(homeWork.getDeadline()));
            preparedStatement.setInt(3, lectionId);
            preparedStatement.execute();
        }
    }

    public void deleteHomeWork(int id) throws SQLException, IOException {
        String sql = "delete from homework where id=?";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<HomeWork> getHomeWork(int id) throws SQLException, IOException {
        return Optional.ofNullable(getHomeWorks().get(id));
    }
}
