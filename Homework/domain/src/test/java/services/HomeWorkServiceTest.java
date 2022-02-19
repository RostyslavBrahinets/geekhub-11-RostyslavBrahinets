//package services;
//
//import exceptions.NotFoundException;
//import exceptions.ValidationException;
//import models.HomeWork;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import repository.HomeWorkRepository;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class HomeWorkServiceTest {
//    private static HomeWorkService homeWorkService;
//    private static String task;
//    private static LocalDateTime deadline;
//
//    @BeforeAll
//    static void setDataInRepository() throws SQLException, IOException {
//        HomeWorkRepository homeWorkRepository = HomeWorkRepository.getInstance();
//        task = "Task";
//        deadline = LocalDateTime.now();
//        homeWorkRepository.addHomeWork(
//            new HomeWork(task, deadline)
//        );
//    }
//
//    @BeforeEach
//    void setUp() throws SQLException, IOException {
//        homeWorkService = new HomeWorkService();
//    }
//
//    @Test
//    void getHomeWorks_DoNothing_WithoutError() throws SQLException {
//        List<HomeWork> homeWorks = new ArrayList<>();
//        homeWorks.add(new HomeWork(task, deadline));
//
//        assertEquals(homeWorks, homeWorkService.getHomeWorks());
//    }
//
//    @Test
//    void addHomeWork_DoNothing_WithoutError() {
//        assertDoesNotThrow(
//            () -> homeWorkService.addHomeWork(task, deadline)
//        );
//    }
//
//    @Test
//    void addHomeWork_ThrowValidationException_ForTaskIsNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> homeWorkService.addHomeWork(null, deadline)
//        );
//    }
//
//    @Test
//    void addHomeWork_ThrowValidationException_ForDeadlineIsNull() {
//        assertThrows(
//            ValidationException.class,
//            () -> homeWorkService.addHomeWork("Task", null)
//        );
//    }
//
//    @Test
//    void addHomeWork_ThrowValidationException_ForTaskIsEmpty() {
//        assertThrows(
//            ValidationException.class,
//            () -> homeWorkService.addHomeWork("", deadline)
//        );
//    }
//
//    @Test
//    void deleteHomeWork_DoNothing_WithoutError() {
//        assertDoesNotThrow(
//            () -> homeWorkService.deleteHomeWork(0)
//        );
//    }
//
//    @Test
//    void deleteHomeWork_ThrowNotFoundException_ForIdIsLessThenExpected() {
//        assertThrows(
//            NotFoundException.class,
//            () -> homeWorkService.deleteHomeWork(-1)
//        );
//    }
//
//    @Test
//    void deleteHomeWork_ThrowNotFoundException_ForIdIsMoreThenExpected() throws SQLException {
//        List<HomeWork> homeWorks = homeWorkService.getHomeWorks();
//        int size = homeWorks.size();
//
//        assertThrows(
//            NotFoundException.class,
//            () -> homeWorkService.deleteHomeWork(size)
//        );
//    }
//
//    @Test
//    void getHomeWork_DoNothing_WithoutError() throws SQLException {
//        HomeWork expectedHomeWork = new HomeWork(task, deadline);
//        Optional<HomeWork> homeWork = homeWorkService.getHomeWork(0);
//
//        HomeWork actualHomeWork = null;
//        if (homeWork.isPresent()) {
//            actualHomeWork = homeWork.get();
//        }
//
//        assertEquals(expectedHomeWork, actualHomeWork);
//    }
//
//    @Test
//    void getHomeWork_ThrowNotFoundException_ForIdIsLessThenExpected() {
//        assertThrows(
//            NotFoundException.class,
//            () -> homeWorkService.getHomeWork(-1)
//        );
//    }
//
//    @Test
//    void getHomeWork_ThrowNotFoundException_ForIdIsMoreThenExpected() throws SQLException {
//        List<HomeWork> homeWorks = homeWorkService.getHomeWorks();
//        int size = homeWorks.size();
//
//        assertThrows(
//            NotFoundException.class,
//            () -> homeWorkService.getHomeWork(size)
//        );
//    }
//}