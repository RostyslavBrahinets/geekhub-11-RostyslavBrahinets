package repository;

import models.Course;
import models.Lection;
import models.Person;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final DataSource dataSource;
    private final PersonRepository personRepository;

    public CourseRepository(DataSource dataSource, PersonRepository personRepository) {
        this.dataSource = dataSource;
        this.personRepository = personRepository;
    }

    public List<Course> getCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();

        try (
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()
        ) {
            String sql = "select * from course";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                List<Lection> lections = getLections(connection, id);
                List<Person> students = getPeople(connection, id);

                Course course = new Course(id, name, lections, students);
                courses.add(course);
            }
        }

        return courses;
    }

    public void addCourse(Course course) throws SQLException {
        String sql = "insert into course(name) values (?)";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.execute();
        }
    }

    public void deleteCourse(int id) throws SQLException {
        String sql = "delete from course where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Course> getCourse(int id) throws SQLException {
        Course course = null;
        String sql = "select * from course where id=?";

        try (
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                List<Lection> lections = getLections(connection, id);
                List<Person> students = getPeople(connection, id);
                course = new Course(id, name, lections, students);
            }
        }

        return Optional.ofNullable(course);
    }

    private List<Lection> getLections(Connection connection, int id)
        throws SQLException {
        List<Lection> lections = new ArrayList<>();
        String sql = "select * from lection where course_id=?";

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            MethodsForRepository repository = new MethodsForRepository();

            while (resultSet.next()) {
                Optional<Lection> lection = repository
                    .getLection(connection, resultSet, id, personRepository);
                lection.ifPresent(lections::add);
            }
        }

        return lections;
    }

    private List<Person> getPeople(Connection connection, int id) throws SQLException {
        List<Person> people = new ArrayList<>();
        String sql = "select * from person where course_id=? and role='STUDENT'";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MethodsForRepository repository = new MethodsForRepository();
                Optional<Person> person = repository.getPerson(connection, resultSet, id);
                person.ifPresent(people::add);
            }
        }

        return people;
    }
}
