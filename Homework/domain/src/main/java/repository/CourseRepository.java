package repository;

import models.Course;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private static CourseRepository instance;
    private final Connection connection;

    private CourseRepository() throws SQLException, IOException {
        connection = Connector.getConnection();
    }

    public static CourseRepository getInstance() throws SQLException, IOException {
        if (instance == null) {
            instance = new CourseRepository();
        }

        return instance;
    }

    public List<Course> getCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();

        Statement statement = connection.createStatement();
        String sql = "select * from course";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Course course = new Course(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                List.of(),
                List.of()
//                resultSet.getInt("lections"),
//                resultSet.getInt("students")
            );
            courses.add(course);
        }

        return courses;
    }

    public void addCourse(Course course) throws SQLException {
        String sql = "insert into course(name, lection_id, student_id) values (?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, course.getName());
        preparedStatement.setInt(2, 1);
        preparedStatement.setInt(3, 1);
//        preparedStatement.setInt(2, course.lections()));
//        preparedStatement.setInt(3, course.students());
        preparedStatement.execute();
    }

    public void deleteCourse(int id) throws SQLException {
        String sql = "delete from course where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Optional<Course> getCourse(int id) throws SQLException {
        return Optional.ofNullable(getCourses().get(id));
    }
}
