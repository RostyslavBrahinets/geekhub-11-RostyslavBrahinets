package services;

import models.Contact;
import repository.ContactRepository;
import validators.ContactValidator;

import java.util.List;
import java.util.Optional;

public class ContactService {
    private final ContactRepository contactRepository;
    private final ContactValidator validator;

    public ContactService(
        ContactRepository contactRepository,
        ContactValidator validator
    ) {
        this.contactRepository = contactRepository;
        this.validator = validator;
    }

    public List<Contact> getContacts() {
        return contactRepository.getContacts();
    }

    public void addContact(String email, String phone, int personId) {
        validator.validate(email, phone);
        contactRepository.addContact(new Contact(email, phone), personId);
    }

    public void deleteContact(int id) {
        validator.validate(id);
        contactRepository.deleteContact(id);
    }

    public Optional<Contact> getContact(int id) {
        validator.validate(id);
        return contactRepository.getContact(id);
    }
}
