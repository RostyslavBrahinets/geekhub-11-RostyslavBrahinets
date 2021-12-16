package services;

import models.HomeWork;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeWorkServiceTest {
    private static HomeWorkService homeWorkService;
    private static String task;

    @BeforeAll
    static void setUp() {
        homeWorkService = new HomeWorkService();
        task = "Task";
        homeWorkService.addHomeWork(task);
    }

    @Test
    void getHomeWorks_DoNothing_WithoutError() {
        List<HomeWork> homeworks = new ArrayList<>();
        homeworks.add(new HomeWork(task));
        assertEquals(homeWorkService.getHomeWorks(), homeworks);
    }

    @Test
    void addHomeWork_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> homeWorkService.addHomeWork(task));
    }

    @Test
    void addHomeWork_LoggingException_ForTaskIsNull() {
        assertDoesNotThrow(() -> homeWorkService.addHomeWork(null));
    }

    @Test
    void addHomeWork_LoggingException_ForTaskIsEmpty() {
        assertDoesNotThrow(() -> homeWorkService.addHomeWork(""));
    }

    @Test
    void deleteHomeWork_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> homeWorkService.deleteHomeWork(0));
    }

    @Test
    void deleteHomeWork_LoggingException_ForIdIsNegative() {
        assertDoesNotThrow(() -> homeWorkService.deleteHomeWork(-1));
    }

    @Test
    void deleteHomeWork_LoggingException_ForIdIsMoreThenExpected() {
        assertDoesNotThrow(() -> homeWorkService.deleteHomeWork(homeWorkService.getHomeWorks().size()));
    }

    @Test
    void getHomeWork_DoNothing_WithoutError() {
        HomeWork homework = new HomeWork(task);
        assertEquals(homeWorkService.getHomeWork(0), homework);
    }

    @Test
    void getHomeWork_ReturnNull_ForIdIsLessThenExpected() {
        assertNull(homeWorkService.getHomeWork(-1));
    }

    @Test
    void getHomeWork_ReturnNull_ForIdIsMoreThenExpected() {
        assertNull(homeWorkService.getHomeWork(homeWorkService.getHomeWorks().size()));
    }
}