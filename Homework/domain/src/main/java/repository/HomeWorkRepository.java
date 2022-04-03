package repository;

import models.HomeWork;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HomeWorkRepository {
    private final DataSource dataSource;

    public HomeWorkRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<HomeWork> getHomeWorks() throws SQLException {
        List<HomeWork> homeWorks = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
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

    public void addHomeWork(HomeWork homeWork, int lectionId) throws SQLException {
        String sql = "insert into homework(task, deadline, lection_id) values (?,?,?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, homeWork.getTask());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(homeWork.getDeadline()));
            preparedStatement.setInt(3, lectionId);
            preparedStatement.execute();
        }
    }

    public void deleteHomeWork(int id) throws SQLException {
        String sql = "delete from homework where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<HomeWork> getHomeWork(int id) throws SQLException {
        HomeWork homeWork = null;
        String sql = "select * from homework where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                homeWork = new HomeWork(
                    resultSet.getInt("id"),
                    resultSet.getString("task"),
                    resultSet.getTimestamp("deadline").toLocalDateTime()
                );
            }
        }

        return Optional.ofNullable(homeWork);
    }
}
