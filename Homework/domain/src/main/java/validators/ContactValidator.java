package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Contact;
import repository.ContactRepository;

public class ContactValidator {
    private final ContactRepository contactRepository;

    public ContactValidator(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void validate(Contact contact) {
        String email = contact.getEmail();
        String phone = contact.getPhone();

        if (email == null || email.isBlank()) {
            throw new ValidationException("E-mail of contact is invalid");
        } else if (phone == null || phone.isBlank()) {
            throw new ValidationException("Phone of contact is invalid");
        }
    }

    public void validate(int id) {
        if (id < 1 || id > contactRepository.getContacts().size()) {
            throw new NotFoundException("Contact not found");
        }
    }
}
