package repository;

import models.Contact;
import models.Lection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepository {
    private static ContactRepository instance;
    private final Connection connection;

    private ContactRepository() throws SQLException, IOException {
        connection = Connector.getConnection();
    }

    public static ContactRepository getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new ContactRepository();
        }

        return instance;
    }

    public List<Contact> getContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();

        Statement statement = connection.createStatement();
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

        return contacts;
    }

    public void addContact(Contact contact) throws SQLException {
        String sql = "insert into contacts("
            + "email, phone)"
            + "values (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, contact.getEmail());
        preparedStatement.setString(2, contact.getPhone());
        preparedStatement.execute();
    }

    public void deleteContact(int id) throws SQLException {
        String sql = "delete from contacts where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Optional<Contact> getContact(int id) throws SQLException {
        return Optional.ofNullable(getContacts().get(id));
    }
}
