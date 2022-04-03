package repository;

import jdbc.DatabaseTemplate;
import models.Resource;
import models.ResourceType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ResourceRepository {
    private static final NamedParameterJdbcTemplate jdbcTemplate =
        DatabaseTemplate.getJdbcTemplate();

    private static RowMapper<Resource> mapper() {
        return (rs, rowNum) -> new Resource(
            rs.getInt("id"),
            rs.getString("name"),
            ResourceType.valueOf(rs.getString("type")),
            rs.getString("data")
        );
    }

    public List<Resource> getResources() {
        String sql = "SELECT * FROM resource";
        return jdbcTemplate.query(sql, mapper());
    }

    public void addResource(Resource resource, int lectionId) {
        String sql = "INSERT INTO resource(name, type, data, lection_id)"
            + " values (:name, :type, :data, :lection_id)";

        Map<String, Object> param = Map.of(
            "name", resource.getName(),
            "type", resource.getType().toString(),
            "data", resource.getData(),
            "lection_id", lectionId
        );

        jdbcTemplate.update(sql, param);
    }

    public void deleteResource(int id) {
        String sql = "DELETE FROM resource WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        jdbcTemplate.update(sql, param);
    }

    public Optional<Resource> getResource(int id) {
        String sql = "SELECT * FROM resource WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        Resource resource = jdbcTemplate.queryForObject(sql, param, mapper());
        return Optional.ofNullable(resource);
    }
}
