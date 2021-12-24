package validators;//package validators;
//
//import exceptions.NotFoundException;
//import exceptions.ValidationException;
//import models.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import sources.CourseSource;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CourseValidatorTest {
//    private CourseValidator validator;
//    private static String name;
//    private static List<Lection> lections;
//    private static List<Person> students;
//
//
//    @BeforeAll
//    static void setDataInSource() {
//        List<Resource> resources = new ArrayList<>();
//        resources.add(new Resource("Resource", ResourceType.URL, "Data"));
//        List<String> contacts = new ArrayList<>();
//        contacts.add("Contact");
//        Person lecturer = new Person("First name", "Last name",
//            contacts, "Nickname", Role.TEACHER);
//        List<HomeWork> homeWorks = new ArrayList<>();
//        homeWorks.add(new HomeWork("Task"));
//        lections = new ArrayList<>();
//        lections.add(new Lection("Lection", "Describe",
//            resources, lecturer, homeWorks, creationDate));
//        students = new ArrayList<>();
//        students.add(new Person("First name", "Last name",
//            contacts, "Nickname", Role.STUDENT));
//        CourseSource courseSource = CourseSource.getInstance();
//        name = "Course";
//        courseSource.addCourse(new Course(name, lections, students));
//    }
//
//    @BeforeEach
//    void setUp() {
//        validator = new CourseValidator();
//    }
//
//    @Test
//    void validate_DoNothing_WithoutError() {
//        assertDoesNotThrow(() -> validator.validate(name, lections, students));
//    }
//
//    @Test
//    void validate_ThrowsValidationException_ForNameIsNull() {
//        assertThrows(ValidationException.class, () -> validator.validate(null, lections, students));
//    }
//
//    @Test
//    void validate_ThrowsValidationException_ForLectionsIsNull() {
//        assertThrows(ValidationException.class, () -> validator.validate(name, null, students));
//    }
//
//    @Test
//    void validate_ThrowsValidationException_ForStudentsIsNull() {
//        assertThrows(ValidationException.class, () -> validator.validate(name, lections, null));
//    }
//
//    @Test
//    void validate_ThrowsValidationException_ForNameIsEmpty() {
//        assertThrows(ValidationException.class, () -> validator.validate("", lections, students));
//    }
//
//    @Test
//    void validateId_DoNothing_WithoutError() {
//        assertDoesNotThrow(() -> validator.validate(0));
//    }
//
//    @Test
//    void validateId_ThrowsNotFoundException_ForIdIsLessThenExpected() {
//        assertThrows(NotFoundException.class, () -> validator.validate(-1));
//    }
//
//    @Test
//    void validateId_ThrowsNotFoundException_ForIdIsMoreThenExpected() {
//        assertThrows(NotFoundException.class, () -> validator.validate(1));
//    }
//}