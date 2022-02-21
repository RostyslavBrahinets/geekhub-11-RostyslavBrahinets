package repository;

import db.DataBaseConnector;
import models.Resource;
import models.ResourceType;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourcesRepository {
    private static ResourcesRepository instance;

    private ResourcesRepository() {
    }

    public static ResourcesRepository getInstance() {
        if (instance == null) {
            instance = new ResourcesRepository();
        }

        return instance;
    }

    public List<Resource> getResources() throws SQLException, IOException {
        List<Resource> resources = new ArrayList<>();

        try (
            Connection connection = DataBaseConnector.getConnection();
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
            Connection connection = DataBaseConnector.getConnection();
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
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Resource> getResource(int id) throws SQLException, IOException {
        Resource resource;
        String sql = "select * from resource where id=?";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resource = new Resource(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                ResourceType.valueOf(resultSet.getString("type")),
                resultSet.getString("data")
            );
        }

        return Optional.of(resource);
    }
}
