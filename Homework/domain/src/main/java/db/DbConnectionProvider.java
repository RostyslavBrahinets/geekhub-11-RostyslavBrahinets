package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionProvider {
    public Connection getConnection() throws IOException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        properties.load(DbConnectionProvider.class.getClassLoader()
            .getResourceAsStream("application.properties"));
        return DriverManager
            .getConnection(properties.getProperty("url"), properties);
    }
}
