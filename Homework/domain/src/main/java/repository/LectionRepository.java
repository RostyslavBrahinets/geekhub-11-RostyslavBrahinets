package repository;

import jdbc.DatabaseTemplate;
import models.Lection;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LectionRepository {
    private static final NamedParameterJdbcTemplate jdbcTemplate =
        DatabaseTemplate.getJdbcTemplate();

    private static RowMapper<Lection> mapper() {
        return (rs, rowNum) -> new Lection(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("describe"),
            MethodsForRepository.getResourcesByLections(rs.getInt("id")),
            MethodsForRepository.getPerson(rs.getInt("lecturer_id")).get(),
            MethodsForRepository.getHomeWorksByLections(rs.getInt("id")),
            rs.getTimestamp("creation_date").toLocalDateTime().toLocalDate()
        );
    }

    public List<Lection> getLections() {
        String sql = "SELECT * FROM lection";
        return jdbcTemplate.query(sql, mapper());
    }

    public void addLection(
        Lection lection,
        int lecturerId,
        int courseId
    ) {
        String sql = "insert into lection"
            + "(name, describe, lecturer_id, course_id)"
            + "values (:name, :describe, :lecturer_id, :course_id)";

        Map<String, Object> param = Map.of(
            "name", lection.getName(),
            "describe", lection.getDescribe(),
            "lecturer_id", lecturerId,
            "course_id", courseId
        );

        jdbcTemplate.update(sql, param);
    }

    public void deleteLection(int id) {
        String sql = "DELETE FROM lection WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        jdbcTemplate.update(sql, param);
    }

    public Optional<Lection> getLection(int id) {
        String sql = "SELECT * FROM lection WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        Lection lection = jdbcTemplate.queryForObject(sql, param, mapper());
        return Optional.ofNullable(lection);
    }
}
