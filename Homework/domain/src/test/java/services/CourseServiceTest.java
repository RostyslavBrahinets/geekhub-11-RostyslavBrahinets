//package services;
//
//import exceptions.NotFoundException;
//import exceptions.ValidationException;
//import models.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import repository.CourseRepository;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CourseServiceTest {
//    private static CourseService courseService;
//    private static String name;
//    private static List<Lection> lections;
//    private static List<Person> students;
//
//    @BeforeAll
//    static void setDataInRepository() {
//        CourseRepository courseRepository = CourseRepository.getInstance();
//        name = "Course";
//        lections = new ArrayList<>();
//        students = new ArrayList<>();
//
//        List<Resource> resources = new ArrayList<>();
//        resources.add(new Resource("Resource", ResourceType.URL, "Data"));
//
//        List<String> contacts = new ArrayList<>();
//        contacts.add("Contact");
//
//        Person lecturer = new Person(
//            "First name",
//            "Last name",
//            contacts,
//            "Nickname",
//            Role.TEACHER);
//
//        List<HomeWork> homeWorks = new ArrayList<>();
//        homeWorks.add(new HomeWork("Task", LocalDateTime.now()));
//
//        lections.add(new Lection("Lection", "Describe", resources, lecturer, homeWorks, LocalDate.now()));
//
//        Person student = new Person(
//            "First name",
//            "Last name",
//            contacts,
//            "Nickname",
//            Role.STUDENT);
//        students.add(student);
//
//        courseRepository.addCourse(
//            new Course(name, lections, students)
//        );
//    }
//
//    @BeforeEach
//    void setUp() {
//        courseService = new CourseService();
//    }
//
//    @Test
//    void getCourses_DoNothing_WithoutError() {
//        List<Course> courses = new ArrayList<>();
//        courses.add(new Course(name, lections, students));
//        courses.add(new Course(name, lections, students));
//
//        assertEquals(courses, courseService.getCourses());
//    }
//
//    @Test
//    void addCourse_DoNothing_WithoutError() {
//        assertDoesNotThrow(
//            () -> courseService.addCourse(name, lections, students)
//        );
//    }
//
//    @Test
//    void addCourse_ThrowValidationException_ForNameIsNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> courseService.addCourse(null, lections, students)
//        );
//    }
//
//    @Test
//    void addCourse_ThrowValidationException_ForNameIsEmpty() {
//        assertThrows(
//            ValidationException.class,
//            () -> courseService.addCourse("", lections, students)
//        );
//    }
//
//    @Test
//    void addCourse_ThrowValidationException_ForLectionsAreNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> courseService.addCourse(name, null, students)
//        );
//    }
//
//    @Test
//    void addCourse_ThrowValidationException_ForStudentsAreNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> courseService.addCourse(name, lections, null)
//        );
//    }
//
//    @Test
//    void deleteCourse_DoNothing_WithoutError() {
//        assertDoesNotThrow(
//            () -> courseService.deleteCourse(0)
//        );
//    }
//
//    @Test
//    void deleteCourse_ThrowNotFoundException_ForIdIsLessThenExpected() {
//        assertThrows(
//            NotFoundException.class,
//            () -> courseService.deleteCourse(-1)
//        );
//    }
//
//    @Test
//    void deleteCourse_ThrowNotFoundException_ForIdIsMoreThenExpected() {
//        List<Course> courses = courseService.getCourses();
//        int size = courses.size();
//
//        assertThrows(
//            NotFoundException.class,
//            () -> courseService.deleteCourse(size)
//        );
//    }
//
//    @Test
//    void getCourse_DoNothing_WithoutError() {
//        Course expectedCourse = new Course(name, lections, students);
//        Optional<Course> course = courseService.getCourse(0);
//
//        Course actualCourse = null;
//        if (course.isPresent()) {
//            actualCourse = course.get();
//        }
//
//        assertEquals(expectedCourse, actualCourse);
//    }
//
//    @Test
//    void getCourse_ThrowNotFoundException_ForIdIsLessThenExpected() {
//        assertThrows(
//            NotFoundException.class,
//            () -> courseService.getCourse(-1)
//        );
//    }
//
//    @Test
//    void getCourse_ThrowNotFoundException_ForIdIsMoreThenExpected() {
//        List<Course> courses = courseService.getCourses();
//        int size = courses.size();
//
//        assertThrows(
//            NotFoundException.class,
//            () -> courseService.getCourse(size)
//        );
//    }
//}