package services;

import models.Contact;
import repository.ContactRepository;
import validators.ContactValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactValidator validator;

    public ContactService() throws SQLException, IOException {
        contactRepository = ContactRepository.getInstance();
        validator = new ContactValidator();
    }

    public List<Contact> getContacts() throws SQLException {
        return contactRepository.getContacts();
    }

    public void addContact(String email, String phone) throws SQLException {
        validator.validate(email, phone);
        contactRepository.addContact(new Contact(email, phone));
    }

    public void deleteContact(int id) throws SQLException, IOException {
        validator.validate(id);
        contactRepository.deleteContact(id);
    }

    public Optional<Contact> getContact(int id) throws SQLException, IOException {
        validator.validate(id);
        return contactRepository.getContact(id);
    }
}
