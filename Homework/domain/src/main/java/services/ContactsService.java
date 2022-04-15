package services;

import models.Contact;
import repository.ContactRepository;
import validators.ContactValidator;

import java.util.List;
import java.util.Optional;

public class ContactsService {
    private final ContactRepository contactRepository;
    private final ContactValidator validator;

    public ContactsService(
        ContactRepository contactRepository,
        ContactValidator validator
    ) {
        this.contactRepository = contactRepository;
        this.validator = validator;
    }

    public List<Contact> getContacts() {
        return contactRepository.getContacts();
    }

    public Optional<Contact> addContact(Contact contact, int personId) {
        validator.validate(contact);
        contactRepository.addContact(contact, personId);
        return Optional.of(contact);
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
