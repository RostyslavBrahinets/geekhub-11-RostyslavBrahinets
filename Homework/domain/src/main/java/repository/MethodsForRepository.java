package repository;

import jdbc.DatabaseTemplate;
import models.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MethodsForRepository {
    private static final NamedParameterJdbcTemplate jdbcTemplate =
        DatabaseTemplate.getJdbcTemplate();

    private static RowMapper<HomeWork> mapperHomeWork() {
        return (rs, rowNum) -> new HomeWork(
            rs.getInt("id"),
            rs.getString("task"),
            rs.getTimestamp("deadline").toLocalDateTime()
        );
    }

    private static RowMapper<Resource> mapperResource() {
        return (rs, rowNum) -> new Resource(
            rs.getInt("id"),
            rs.getString("name"),
            ResourceType.valueOf(rs.getString("type")),
            rs.getString("data")
        );
    }

    private static RowMapper<Contact> mapperContact() {
        return (rs, rowNum) -> new Contact(
            rs.getInt("id"),
            rs.getString("email"),
            rs.getString("phone")
        );
    }

    private static RowMapper<Person> mapperPerson() {
        return (rs, rowNum) -> new Person(
            rs.getInt("id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            getContactsByPeople(rs.getInt("id")),
            rs.getString("git_hub_nickname"),
            Role.valueOf(rs.getString("role"))
        );
    }

    private static RowMapper<Lection> mapperLection() {
        return (rs, rowNum) -> new Lection(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("describe"),
            getResourcesByLections(rs.getInt("id")),
            getPerson(rs.getInt("lecturer_id")).get(),
            getHomeWorksByLections(rs.getInt("id")),
            rs.getTimestamp("creation_date").toLocalDateTime().toLocalDate()
        );
    }

    public static List<HomeWork> getHomeWorksByLections(int id) {
        String sql = "SELECT * FROM homework WHERE lection_id=:id";
        Map<String, Integer> param = Map.of("id", id);
        return jdbcTemplate.query(sql, param, mapperHomeWork());
    }

    public static List<Resource> getResourcesByLections(int id) {
        String sql = "SELECT * FROM resource WHERE lection_id=:id";
        Map<String, Integer> param = Map.of("id", id);
        return jdbcTemplate.query(sql, param, mapperResource());
    }

    public static List<Contact> getContactsByPeople(int id) {
        String sql = "SELECT * FROM contacts where person_id=:id";
        Map<String, Integer> param = Map.of("id", id);
        return jdbcTemplate.query(sql, param, mapperContact());
    }

    public static List<Lection> getLectionsByCourse(int id) {
        String sql = "SELECT * FROM lection where course_id=:id";
        Map<String, Integer> param = Map.of("id", id);
        return jdbcTemplate.query(sql, param, mapperLection());
    }

    public static List<Person> getPeopleByCourse(int id) {
        String sql = "SELECT * FROM person where course_id=:id and role='STUDENT'";
        Map<String, Integer> param = Map.of("id", id);
        return jdbcTemplate.query(sql, param, mapperPerson());
    }

    public static Optional<Person> getPerson(int id) {
        String sql = "SELECT * FROM person WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        Person person = jdbcTemplate.queryForObject(sql, param, mapperPerson());
        return Optional.ofNullable(person);
    }
}
