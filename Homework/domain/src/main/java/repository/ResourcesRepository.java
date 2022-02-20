package repository;

import models.Resource;
import models.ResourceType;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResourcesRepository {
    private static ResourcesRepository instance;
    private final Connection connection;

    private ResourcesRepository() throws SQLException, IOException {
        connection = Connector.getConnection();
    }

    public static ResourcesRepository getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new ResourcesRepository();
        }

        return instance;
    }

    public List<Resource> getResources() throws SQLException {
        List<Resource> resources = new ArrayList<>();

        Statement statement = connection.createStatement();
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

        return resources;
    }

    public void addResource(Resource resource) throws SQLException {
        String sql = "insert into resource(name, type, date) values (?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, resource.getName());
        preparedStatement.setString(2, String.valueOf(resource.getType()));
        preparedStatement.setString(3, resource.getData());
        preparedStatement.execute();
    }

    public void deleteResource(int id) throws SQLException {
        String sql = "delete from resource where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Optional<Resource> getResource(int id) throws SQLException {
        return Optional.ofNullable(getResources().get(id));
    }
}
