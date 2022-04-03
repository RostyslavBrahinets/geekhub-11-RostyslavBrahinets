package repository;

import jdbc.DatabaseTemplate;
import models.HomeWork;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HomeWorkRepository {
    private static final NamedParameterJdbcTemplate jdbcTemplate =
        DatabaseTemplate.getJdbcTemplate();

    private static RowMapper<HomeWork> mapper() {
        return (rs, rowNum) -> new HomeWork(
            rs.getInt("id"),
            rs.getString("task"),
            rs.getTimestamp("deadline").toLocalDateTime()
        );
    }

    public List<HomeWork> getHomeWorks() {
        String sql = "SELECT * FROM homework";
        return jdbcTemplate.query(sql, mapper());
    }

    public void addHomeWork(HomeWork homeWork, int lectionId) {
        String sql = "INSERT INTO homework(task, deadline, lection_id)"
            + " VALUES (:task, :deadline, :lection_id)";

        Map<String, Object> param = Map.of(
            "task", homeWork.getTask(),
            "deadline", homeWork.getDeadline(),
            "lection_id", lectionId
        );

        jdbcTemplate.update(sql, param);
    }

    public void deleteHomeWork(int id) {
        String sql = "DELETE FROM homework WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        jdbcTemplate.update(sql, param);
    }

    public Optional<HomeWork> getHomeWork(int id) {
        String sql = "SELECT * FROM homework WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        HomeWork homeWork = jdbcTemplate.queryForObject(sql, param, mapper());
        return Optional.ofNullable(homeWork);
    }
}
