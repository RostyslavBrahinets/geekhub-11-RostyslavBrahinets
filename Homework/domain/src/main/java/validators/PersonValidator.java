package validators;

import exceptions.InvalidArgumentException;
import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.Person;
import models.Role;
import repository.PersonRepository;

public class PersonValidator {
    private final PersonRepository personRepository;

    public PersonValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void validate(Person person) {
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String gitHubNickname = person.getGitHubNickname();
        String role = person.getRole().toString();

        if (firstName == null || firstName.isBlank()) {
            throw new ValidationException("First name is invalid");
        } else if (lastName == null || lastName.isBlank()) {
            throw new ValidationException("Last name is invalid");
        } else if (gitHubNickname == null || gitHubNickname.isBlank()) {
            throw new ValidationException("GitHub nickname is invalid");
        } else if (isInvalidRole(role)) {
            throw new InvalidArgumentException("Role of person is invalid");
        }
    }

    public void validate(int id) {
        if (id < 1 || id > personRepository.getPeople().size()) {
            throw new NotFoundException("Person not found");
        }
    }

    private boolean isInvalidRole(String role) {
        boolean invalidRole = true;

        if (role == null) {
            return true;
        }

        for (Role roleOfPerson : Role.values()) {
            if (role.equalsIgnoreCase(String.valueOf(roleOfPerson))) {
                invalidRole = false;
                break;
            }
        }

        return invalidRole;
    }
}
