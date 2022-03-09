package repository;

import db.DbConnectionProvider;
import models.Contact;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepository {
    private final DbConnectionProvider dbConnectionProvider;

    public ContactRepository(DbConnectionProvider dbConnectionProvider) {
        this.dbConnectionProvider = dbConnectionProvider;
    }

    public List<Contact> getContacts() throws SQLException, IOException {
        List<Contact> contacts = new ArrayList<>();

        try (
            Connection connection = dbConnectionProvider.getConnection();
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

    public void addContact(Contact contact, int personId) throws SQLException, IOException {
        String sql = "insert into contacts"
            + "(email, phone, person_id)"
            + "values (?,?,?)";

        try (
            Connection connection = dbConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, contact.getEmail());
            preparedStatement.setString(2, contact.getPhone());
            preparedStatement.setInt(3, personId);
            preparedStatement.execute();
        }
    }

    public void deleteContact(int id) throws SQLException, IOException {
        String sql = "delete from contacts where id=?";

        try (
            Connection connection = dbConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Contact> getContact(int id) throws SQLException, IOException {
        Contact contact = null;
        String sql = "select * from contacts where id=?";

        try (
            Connection connection = dbConnectionProvider.getConnection();
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
