package repository;

import models.Contact;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepository {
    private final DataSource dataSource;

    public ContactRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Contact> getContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()
        ) {
            String sql = "select * from contacts";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Contact contact = new Contact(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
                );
                contacts.add(contact);
            }
        }

        return contacts;
    }

    public void addContact(Contact contact, int personId) throws SQLException {
        String sql = "insert into contacts"
            + "(email, phone, person_id)"
            + "values (?,?,?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, contact.getEmail());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setInt(3, personId);
            preparedStatement.execute();
        }
    }

    public void deleteContact(int id) throws SQLException {
        String sql = "delete from contacts where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Contact> getContact(int id) throws SQLException {
        Contact contact = null;
        String sql = "select * from contacts where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                contact = new Contact(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
                );
            }
        }

        return Optional.ofNullable(contact);
    }
}
