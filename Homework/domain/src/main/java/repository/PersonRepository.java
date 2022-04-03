package repository;

import jdbc.DatabaseTemplate;
import models.Person;
import models.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonRepository {
    private static final NamedParameterJdbcTemplate jdbcTemplate =
        DatabaseTemplate.getJdbcTemplate();

    private static RowMapper<Person> mapper() {
        return (rs, rowNum) -> new Person(
            rs.getInt("id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            MethodsForRepository.getContactsByPeople(rs.getInt("id")),
            rs.getString("git_hub_nickname"),
            Role.valueOf(rs.getString("role"))
        );
    }

    public List<Person> getPeople() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, mapper());
    }

    public void addPerson(Person person, int courseId) {
        String sql = "insert into person"
            + "(first_name, last_name, git_hub_nickname, role, course_id)"
            + "values (:first_name, :last_name, :git_hub_nickname, :role, :course_id)";

        Map<String, Object> param = Map.of(
            "first_name", person.getFirstName(),
            "last_name", person.getLastName(),
            "git_hub_nickname", person.getGitHubNickname(),
            "role", person.getRole().toString(),
            "course_id", courseId
        );

        jdbcTemplate.update(sql, param);
    }

    public void deletePerson(int id) {
        String sql = "DELETE FROM person WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        jdbcTemplate.update(sql, param);
    }

    public Optional<Person> getPerson(int id) {
        String sql = "SELECT * FROM person WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        Person person = jdbcTemplate.queryForObject(sql, param, mapper());
        return Optional.ofNullable(person);
    }
}
