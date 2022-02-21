package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import repository.ContactRepository;

import java.io.IOException;
import java.sql.SQLException;

public class ContactValidator {
    public void validate(String email, String phone) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("E-mail of contact is invalid");
        } else if (phone == null || phone.isBlank()) {
            throw new ValidationException("Phone of contact is invalid");
        }
    }

    public void validate(int id) throws SQLException, IOException {
        ContactRepository contactRepository = ContactRepository.getInstance();
        if (id < 1 || id > contactRepository.getContacts().size()) {
            throw new NotFoundException("Contact not found");
        }
    }
}
