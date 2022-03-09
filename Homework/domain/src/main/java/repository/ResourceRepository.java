package repository;

import db.DbConnectionProvider;
import models.Resource;
import models.ResourceType;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourceRepository {
    private final DbConnectionProvider dbConnectionProvider;

    public ResourceRepository(DbConnectionProvider dbConnectionProvider) {
        this.dbConnectionProvider = dbConnectionProvider;
    }

    public List<Resource> getResources() throws SQLException, IOException {
        List<Resource> resources = new ArrayList<>();

        try (
            Connection connection = dbConnectionProvider.getConnection();
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

    public void addResource(Resource resource, int lectionId) throws SQLException, IOException {
        String sql = "insert into resource(name, type, data, lection_id) values (?,?,?,?)";

        try (
            Connection connection = dbConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, resource.getName());
            preparedStatement.setString(2, String.valueOf(resource.getType()));
            preparedStatement.setString(3, resource.getData());
            preparedStatement.setInt(4, lectionId);
            preparedStatement.execute();
        }
    }

    public void deleteResource(int id) throws SQLException, IOException {
        String sql = "delete from resource where id=?";

        try (
            Connection connection = dbConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Resource> getResource(int id) throws SQLException, IOException {
        Resource resource = null;
        String sql = "select * from resource where id=?";

        try (
            Connection connection = dbConnectionProvider.getConnection();
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