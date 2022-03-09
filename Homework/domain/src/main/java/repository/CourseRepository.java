package repository;

import db.DbConnectionProvider;
import models.Course;
import models.Lection;
import models.Person;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private final DbConnectionProvider dbConnectionProvider;
    private final PersonRepository personRepository;

    public CourseRepository(
        DbConnectionProvider dbConnectionProvider,
        PersonRepository personRepository
    ) {
        this.dbConnectionProvider = dbConnectionProvider;
        this.personRepository = personRepository;
    }

    public List<Course> getCourses() throws SQLException, IOException {
        List<Course> courses = new ArrayList<>();

        try (
            Connection connection = dbConnectionProvider.getConnection();
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

    public void addCourse(Course course) throws SQLException, IOException {
        String sql = "insert into course(name) values (?)";

        try (
            Connection connection = dbConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.execute();
        }
    }

    public void deleteCourse(int id) throws SQLException, IOException {
        String sql = "delete from course where id=?";

        try (
            Connection connection = dbConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Course> getCourse(int id) throws SQLException, IOException {
        Course course = null;
        String sql = "select * from course where id=?";

        try (
            Connection connection = dbConnectionProvider.getConnection();
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
        throws SQLException, IOException {
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
