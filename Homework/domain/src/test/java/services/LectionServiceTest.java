//package services;
//
//import models.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
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
//
//    @BeforeAll
//    static void setUp() {
//        lectionService = new LectionService();
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
//        homeWorks.add(new HomeWork("Task"));
//
//        lectionService.addLection(name, describe, resources, lecturer, homeWorks);
//        lectionService.addLection(name, describe, resources, lecturer, homeWorks);
//    }
//
//    @Test
//    void getLections_DoNothing_WithoutError() {
//        List<Lection> lections = new ArrayList<>();
//        lections.add(new Lection(name, describe, resources, lecturer, homeWorks, creationDate));
//
//        assertEquals(lections, lectionService.getLections());
//    }
//
//    @Test
//    void addLection_DoNothing_WithoutError() {
//        assertDoesNotThrow(() -> lectionService.addLection(name, describe, resources, lecturer, homeWorks));
//    }
//
//    @Test
//    void addLection_LoggingException_ForNameIsNull() {
//        assertDoesNotThrow(() -> lectionService.addLection(null, describe, resources, lecturer, homeWorks));
//    }
//
//    @Test
//    void addLection_LoggingException_ForDescriptionIsNull() {
//        assertDoesNotThrow(() -> lectionService.addLection(name, null, resources, lecturer, homeWorks));
//    }
//
//    @Test
//    void addLection_LoggingException_ForResourcesAreNull() {
//        assertDoesNotThrow(() -> lectionService.addLection(name, describe, null, lecturer, homeWorks));
//    }
//
//    @Test
//    void addLection_LoggingException_ForLecturerIsNull() {
//        assertDoesNotThrow(() -> lectionService.addLection(name, describe, resources, null, homeWorks));
//    }
//
//    @Test
//    void addLection_LoggingException_ForHomeWorksAreNull() {
//        assertDoesNotThrow(() -> lectionService.addLection(name, describe, resources, lecturer, null));
//    }
//
//    @Test
//    void addLection_LoggingException_ForNameIsEmpty() {
//        assertDoesNotThrow(() -> lectionService.addLection("", describe, resources, lecturer, homeWorks));
//    }
//
//    @Test
//    void addLection_LoggingException_ForDescribeIsEmpty() {
//        assertDoesNotThrow(() -> lectionService.addLection(name, "", resources, lecturer, homeWorks));
//    }
//
//    @Test
//    void deleteLection_DoNothing_WithoutError() {
//        assertDoesNotThrow(() -> lectionService.deleteLection(0));
//    }
//
//    @Test
//    void deleteLection_LoggingException_ForIdIsLessThenExpected() {
//        assertDoesNotThrow(() -> lectionService.deleteLection(-1));
//    }
//
//    @Test
//    void deleteLection_LoggingException_ForIdIsMoreThenExpected() {
//        assertDoesNotThrow(() -> lectionService.deleteLection(lectionService.getLections().size()));
//    }
//
//    @Test
//    void getLection_DoNothing_WithoutError() {
//        Lection lection = new Lection(name, describe, resources, lecturer, homeWorks, creationDate);
//
//        assertEquals(lection, lectionService.getLection(0));
//    }
//
//    @Test
//    void getLection_ReturnNull_ForIdIsLessThenExpected() {
//        assertNull(lectionService.getLection(-1));
//    }
//
//    @Test
//    void getLection_ReturnNull_ForIdIsMoreThenExpected() {
//        assertNull(lectionService.getLection(lectionService.getLections().size()));
//    }
//}