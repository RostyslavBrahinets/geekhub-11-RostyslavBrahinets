package repository;

import jdbc.DatabaseTemplate;
import models.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContactRepository {
    private static final NamedParameterJdbcTemplate jdbcTemplate =
        DatabaseTemplate.getJdbcTemplate();

    private static RowMapper<Contact> mapper() {
        return (rs, rowNum) -> new Contact(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("phone")
        );
    }

    public List<Contact> getContacts() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, mapper());
    }

    public void addContact(Contact contact, int personId) {
        String sql = "INSERT INTO contacts (email, phone, person_id)"
            + " VALUES (:email, :phone, :person_id)";

        Map<String, Object> param = Map.of(
            "email", contact.getEmail(),
            "phone", contact.getPhone(),
            "person_id", personId
        );

        jdbcTemplate.update(sql, param);
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        jdbcTemplate.update(sql, param);
    }

    public Optional<Contact> getContact(int id) {
        String sql = "SELECT * FROM contacts WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        Contact contact = jdbcTemplate.queryForObject(sql, param, mapper());
        return Optional.ofNullable(contact);
    }
}
