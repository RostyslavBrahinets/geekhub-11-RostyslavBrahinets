package db;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseConnector {
    public static Connection getConnection() throws IOException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        properties.load(DataBaseConnector.class.getClassLoader()
            .getResourceAsStream("application.properties"));
        return DriverManager
            .getConnection(properties.getProperty("url"), properties);
    }

    private void runSql(String sql) throws SQLException, IOException {
        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.execute();
        }
    }
}
