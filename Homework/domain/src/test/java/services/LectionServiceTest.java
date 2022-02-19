//package services;
//
//import exceptions.NotFoundException;
//import exceptions.ValidationException;
//import models.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import repository.LectionRepository;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LectionServiceTest {
//    private static LectionService lectionService;
//    private static String name;
//    private static String describe;
//    private static List<Resource> resources;
//    private static Person lecturer;
//    private static List<HomeWork> homeWorks;
//    private static LocalDate creationDate;
//
//    @BeforeAll
//    static void setDataInRepository() {
//        LectionRepository lectionRepository = LectionRepository.getInstance();
//        name = "Lection";
//        describe = "Describe";
//        resources = new ArrayList<>();
//        resources.add(new Resource("Resource", ResourceType.URL, "Data"));
//
//        List<String> contacts = new ArrayList<>();
//        contacts.add("Contact");
//
//        lecturer = new Person(
//            "First name",
//            "Last name",
//            contacts,
//            "Nickname",
//            Role.TEACHER);
//
//        homeWorks = new ArrayList<>();
//        homeWorks.add(new HomeWork("Task", LocalDateTime.now()));
//        creationDate = LocalDate.now();
//
//        lectionRepository.addLection(
//            new Lection(name, describe, resources, lecturer, homeWorks, creationDate)
//        );
//        lectionRepository.addLection(
//            new Lection(name, describe, resources, lecturer, homeWorks, creationDate)
//        );
//    }
//
//    @BeforeEach
//    void setUp() {
//        lectionService = new LectionService();
//    }
//
//    @Test
//    void getLections_DoNothing_WithoutError() {
//        List<Lection> lections = new ArrayList<>();
//        lections.add(
//            new Lection(name, describe, resources, lecturer, homeWorks, creationDate)
//        );
//
//        assertEquals(lections, lectionService.getLections());
//    }
//
//    @Test
//    void addLection_DoNothing_WithoutError() {
//        assertDoesNotThrow(
//            () -> lectionService.addLection(name, describe, resources, lecturer, homeWorks)
//        );
//    }
//
//    @Test
//    void addLection_ThrowValidationException_ForNameIsNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> lectionService.addLection(null, describe, resources, lecturer, homeWorks)
//        );
//    }
//
//    @Test
//    void addLection_ThrowValidationException_ForDescriptionIsNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> lectionService.addLection(name, null, resources, lecturer, homeWorks)
//        );
//    }
//
//    @Test
//    void addLection_ThrowValidationException_ForResourcesAreNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> lectionService.addLection(name, describe, null, lecturer, homeWorks)
//        );
//    }
//
//    @Test
//    void addLection_ThrowValidationException_ForLecturerIsNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> lectionService.addLection(name, describe, resources, null, homeWorks)
//        );
//    }
//
//    @Test
//    void addLection_ThrowValidationException_ForHomeWorksAreNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> lectionService.addLection(name, describe, resources, lecturer, null)
//        );
//    }
//
//    @Test
//    void addLection_ThrowValidationException_ForNameIsEmpty() {
//        assertThrows(
//            ValidationException.class,
//            () -> lectionService.addLection("", describe, resources, lecturer, homeWorks)
//        );
//    }
//
//    @Test
//    void addLection_ThrowValidationException_ForDescribeIsEmpty() {
//        assertThrows(
//            ValidationException.class,
//            () -> lectionService.addLection(name, "", resources, lecturer, homeWorks)
//        );
//    }
//
//    @Test
//    void deleteLection_DoNothing_WithoutError() {
//        assertDoesNotThrow(
//            () -> lectionService.deleteLection(0)
//        );
//    }
//
//    @Test
//    void deleteLection_ThrowNotFoundException_ForIdIsLessThenExpected() {
//        assertThrows(
//            NotFoundException.class,
//            () -> lectionService.deleteLection(-1)
//        );
//    }
//
//    @Test
//    void deleteLection_ThrowNotFoundException_ForIdIsMoreThenExpected() {
//        List<Lection> lections = lectionService.getLections();
//        int size = lections.size();
//
//        assertThrows(
//            NotFoundException.class,
//            () -> lectionService.deleteLection(size)
//        );
//    }
//
//    @Test
//    void getLection_DoNothing_WithoutError() {
//        Lection expectedLection = new Lection(name, describe, resources, lecturer, homeWorks, creationDate);
//        Optional<Lection> lection = lectionService.getLection(0);
//
//        Lection actualLection = null;
//        if (lection.isPresent()) {
//            actualLection = lection.get();
//        }
//
//        assertEquals(expectedLection, actualLection);
//    }
//
//    @Test
//    void getLection_ThrowNotFoundException_ForIdIsLessThenExpected() {
//        assertThrows(
//            NotFoundException.class,
//            () -> lectionService.getLection(-1)
//        );
//    }
//
//    @Test
//    void getLection_ThrowNotFoundException_ForIdIsMoreThenExpected() {
//        List<Lection> lections = lectionService.getLections();
//        int size = lections.size();
//
//        assertThrows(
//            NotFoundException.class,
//            () -> lectionService.getLection(size)
//        );
//    }
//}