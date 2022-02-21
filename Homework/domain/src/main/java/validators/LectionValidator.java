package validators;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import repository.LectionRepository;

import java.io.IOException;
import java.sql.SQLException;

public class LectionValidator {
    public void validate(String name, String describe) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Name of lection is invalid");
        } else if (describe == null || describe.isBlank()) {
            throw new ValidationException("Describe of lection is invalid");
        }
    }

    public void validate(int id) throws SQLException, IOException {
        LectionRepository lectionSource = LectionRepository.getInstance();
        if (id < 0 || id >= lectionSource.getLections().size()) {
            throw new NotFoundException("Lection not found");
        }
    }
}
