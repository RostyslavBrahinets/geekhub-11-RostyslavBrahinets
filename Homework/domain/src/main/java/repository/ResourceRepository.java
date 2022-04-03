package repository;

import models.Resource;
import models.ResourceType;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourceRepository {
    private final DataSource dataSource;

    public ResourceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Resource> getResources() throws SQLException {
        List<Resource> resources = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()
        ) {
            String sql = "select * from resource";
            ResultSet resultSet = statement.executeQuery(sql);

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

    public void addResource(Resource resource, int lectionId) throws SQLException {
        String sql = "insert into resource(name, type, data, lection_id) values (?,?,?,?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, resource.getName());
            preparedStatement.setString(2, String.valueOf(resource.getType()));
            preparedStatement.setString(3, resource.getData());
            preparedStatement.setInt(4, lectionId);
            preparedStatement.execute();
        }
    }

    public void deleteResource(int id) throws SQLException {
        String sql = "delete from resource where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Resource> getResource(int id) throws SQLException {
        Resource resource = null;
        String sql = "select * from resource where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resource = new Resource(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    ResourceType.valueOf(resultSet.getString("type")),
                    resultSet.getString("data")
                );
            }
        }

        return Optional.ofNullable(resource);
    }
}
