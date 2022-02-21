package repository;

import db.DataBaseConnector;
import models.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {
    private static CourseRepository instance;

    private CourseRepository() {
    }

    public static CourseRepository getInstance() {
        if (instance == null) {
            instance = new CourseRepository();
        }

        return instance;
    }

    public List<Course> getCourses() throws SQLException, IOException {
        List<Course> courses = new ArrayList<>();

        try (
            Connection connection = DataBaseConnector.getConnection();
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
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.execute();
        }
    }

    public void deleteCourse(int id) throws SQLException, IOException {
        String sql = "delete from course where id=?";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }

    public Optional<Course> getCourse(int id) throws SQLException, IOException {
        Course course;
        String sql = "select * from course where id=?";

        try (
            Connection connection = DataBaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            String name = resultSet.getString("name");
            List<Lection> lections = getLections(connection, id);
            List<Person> students = getPeople(connection, id);

            course = new Course(id, name, lections, students);
        }

        return Optional.of(course);
    }

    private List<Lection> getLections(Connection connection, int id) throws SQLException, IOException {
        List<Lection> lections = new ArrayList<>();
        String sql = "select * from lection where course_id=?";

        try (
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PersonRepository personRepository = PersonRepository.getInstance();
                int lecturerId = resultSet.getInt("lecturer_id");
                List<Resource> resources = getResources(connection, id);
                Optional<Person> lecturerOptional = personRepository.getPerson(lecturerId);
                Person lecturer = null;
                if (lecturerOptional.isPresent()) {
                    lecturer = lecturerOptional.get();
                }
                List<HomeWork> homeWorks = getHomeWorks(connection, id);

                Lection lection = new Lection(
                    id,
                    resultSet.getString("name"),
                    resultSet.getString("describe"),
                    resources,
                    lecturer,
                    homeWorks,
                    resultSet.getTimestamp("creation_date").toLocalDateTime().toLocalDate()
                );
                lections.add(lection);
            }
        }

        return lections;
    }

    private List<Resource> getResources(Connection connection, int id) throws SQLException {
        List<Resource> resources = new ArrayList<>();
        String sql = "select * from resource where lection_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Resource resource = new Resource(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    ResourceType.valueOf(resultSet.getString("type")),
                    resultSet.getString("data")
                );
                resources.add(resource);
            }
        }

        return resources;
    }

    private List<HomeWork> getHomeWorks(Connection connection, int id) throws SQLException {
        List<HomeWork> homeWorks = new ArrayList<>();
        String sql = "select * from homework where lection_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                HomeWork homeWork = new HomeWork(
                    resultSet.getInt("id"),
                    resultSet.getString("task"),
                    resultSet.getTimestamp("deadline").toLocalDateTime()
                );
                homeWorks.add(homeWork);
            }
        }

        return homeWorks;
    }

    private List<Person> getPeople(Connection connection, int id) throws SQLException {
        List<Person> people = new ArrayList<>();
        String sql = "select * from person where course_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                List<Contact> contacts = getContacts(connection, id);

                Person person = new Person(
                    id,
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    contacts,
                    resultSet.getString("git_hub_nickname"),
                    Role.valueOf(resultSet.getString("role"))
                );
                people.add(person);
            }
        }

        return people;
    }

    private List<Contact> getContacts(Connection connection, int id) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String sql = "select * from contacts where person_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Contact contact = new Contact(
                    resultSet.getInt("id"),
                    resultSet.getString("email"),
                    resultSet.getString("phone")
                );
                contacts.add(contact);
            }
        }

        return contacts;
    }
}
