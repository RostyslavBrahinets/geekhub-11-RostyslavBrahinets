//package services;
//
//import models.*;
//import org.junit.jupiter.api.*;
//
//import java.util.ArrayList;
//import java.util.List;
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
//    static void setUp() {
//        courseService = new CourseService();
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
//        homeWorks.add(new HomeWork("Task"));
//
//        lections.add(new Lection("Lection", "Describe", resources, lecturer, homeWorks, creationDate));
//
//        Person student = new Person(
//            "First name",
//            "Last name",
//            contacts,
//            "Nickname",
//            Role.STUDENT);
//        students.add(student);
//
//        courseService.addCourse(name, lections, students);
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
//        assertDoesNotThrow(() -> courseService.addCourse(name, lections, students));
//    }
//
//    @Test
//    void addCourse_LoggingException_ForNameIsNull() {
//        assertDoesNotThrow(() -> courseService.addCourse(null, lections, students));
//    }
//
//    @Test
//    void addCourse_LoggingException_ForNameIsEmpty() {
//        assertDoesNotThrow(() -> courseService.addCourse("", lections, students));
//    }
//
//    @Test
//    void addCourse_LoggingException_ForLectionsAreNull() {
//        assertDoesNotThrow(() -> courseService.addCourse(name, null, students));
//    }
//
//    @Test
//    void addCourse_LoggingException_ForStudentsAreNull() {
//        assertDoesNotThrow(() -> courseService.addCourse(name, lections, null));
//    }
//
//    @Test
//    void deleteCourse_DoNothing_WithoutError() {
//        assertDoesNotThrow(() -> courseService.deleteCourse(0));
//    }
//
//    @Test
//    void deleteCourse_LoggingException_ForIdIsLessThenExpected() {
//        assertDoesNotThrow(() -> courseService.deleteCourse(-1));
//    }
//
//    @Test
//    void deleteCourse_LoggingException_ForIdIsMoreThenExpected() {
//        assertDoesNotThrow(() -> courseService.deleteCourse(courseService.getCourses().size()));
//    }
//
//    @Test
//    void getCourse_DoNothing_WithoutError() {
//        Course course = new Course(name, lections, students);
//
//        assertEquals(course, courseService.getCourse(0));
//    }
//
//    @Test
//    void getCourse_ReturnNull_ForIdIsLessThenExpected() {
//        assertNull(courseService.getCourse(-1));
//    }
//
//    @Test
//    void getCourse_ReturnNull_ForIdIsMoreThenExpected() {
//        assertNull(courseService.getCourse(courseService.getCourses().size()));
//    }
//}