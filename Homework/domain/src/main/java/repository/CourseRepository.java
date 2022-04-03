package repository;

import jdbc.DatabaseTemplate;
import models.Course;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CourseRepository {
    private static final NamedParameterJdbcTemplate jdbcTemplate =
        DatabaseTemplate.getJdbcTemplate();

    private static RowMapper<Course> mapper() {
        return (rs, rowNum) -> new Course(
            rs.getInt("id"),
            rs.getString("name"),
            MethodsForRepository.getLectionsByCourse(rs.getInt("id")),
            MethodsForRepository.getPeopleByCourse(rs.getInt("id"))
        );
    }

    public List<Course> getCourses() {
        String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, mapper());
    }

    public void addCourse(Course course) {
        String sql = "INSERT INTO course(name) VALUES (:name)";
        Map<String, Object> param = Map.of("name", course.getName());
        jdbcTemplate.update(sql, param);
    }

    public void deleteCourse(int id) {
        String sql = "DELETE FROM course WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        jdbcTemplate.update(sql, param);
    }

    public Optional<Course> getCourse(int id) {
        String sql = "SELECT * FROM course WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        Course course = jdbcTemplate.queryForObject(sql, param, mapper());
        return Optional.ofNullable(course);
    }
}
