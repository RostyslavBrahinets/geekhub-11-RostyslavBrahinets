package services;

import exceptions.NotFoundException;
import exceptions.ValidationException;
import models.HomeWork;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.HomeWorkRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HomeWorkServiceTest {
    private static HomeWorkService homeWorkService;
    private static String task;
    private static LocalDateTime deadline;

    @BeforeAll
    static void setDataInRepository() {
        HomeWorkRepository homeWorkRepository = HomeWorkRepository.getInstance();
        task = "Task";
        deadline = LocalDateTime.now();
        homeWorkRepository.addHomeWork(
            new HomeWork(task, deadline)
        );
    }

    @BeforeEach
    void setUp() {
        homeWorkService = new HomeWorkService();
    }

    @Test
    void getHomeWorks_DoNothing_WithoutError() {
        Optional<List<HomeWork>> homeWorks = Optional.of(new ArrayList<>());
        homeWorks.get().add(new HomeWork(task, deadline));

        assertEquals(homeWorks, homeWorkService.getHomeWorks());
    }

    @Test
    void addHomeWork_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> homeWorkService.addHomeWork(task, deadline)
        );
    }

    @Test
    void addHomeWork_ThrowValidationException_ForTaskIsNull() {
        assertThrows(
            ValidationException.class,
            () -> homeWorkService.addHomeWork(null, deadline)
        );
    }

    @Test
    void addHomeWork_ThrowValidationException_ForDeadlineIsNull() {
        assertThrows(
            ValidationException.class,
            () -> homeWorkService.addHomeWork("Task", null)
        );
    }

    @Test
    void addHomeWork_ThrowValidationException_ForTaskIsEmpty() {
        assertThrows(
            ValidationException.class,
            () -> homeWorkService.addHomeWork("", deadline)
        );
    }

    @Test
    void deleteHomeWork_DoNothing_WithoutError() {
        assertDoesNotThrow(
            () -> homeWorkService.deleteHomeWork(0)
        );
    }

    @Test
    void deleteHomeWork_ThrowNotFoundException_ForIdIsLessThenExpected() {
        assertThrows(
            NotFoundException.class,
            () -> homeWorkService.deleteHomeWork(-1)
        );
    }

    @Test
    void deleteHomeWork_ThrowNotFoundException_ForIdIsMoreThenExpected() {
        Optional<List<HomeWork>> homeWorks = homeWorkService.getHomeWorks();
        int homeWorksSize = Integer.MAX_VALUE;
        if (homeWorks.isPresent()) {
            homeWorksSize = homeWorks.get().size();
        }
        int size = homeWorksSize;

        assertThrows(
            NotFoundException.class,
            () -> homeWorkService.deleteHomeWork(size)
        );
    }

    @Test
    void getHomeWork_DoNothing_WithoutError() {
        HomeWork expectedHomeWork = new HomeWork(task, deadline);
        Optional<HomeWork> homeWork = homeWorkService.getHomeWork(0);

        HomeWork actualHomeWork = null;
        if (homeWork.isPresent()) {
            actualHomeWork = homeWork.get();
        }

        assertEquals(expectedHomeWork, actualHomeWork);
    }

    @Test
    void getHomeWork_ThrowNotFoundException_ForIdIsLessThenExpected() {
        assertThrows(
            NotFoundException.class,
            () -> homeWorkService.getHomeWork(-1)
        );
    }

    @Test
    void getHomeWork_ThrowNotFoundException_ForIdIsMoreThenExpected() {
        Optional<List<HomeWork>> homeWorks = homeWorkService.getHomeWorks();
        int homeWorksSize = Integer.MAX_VALUE;
        if (homeWorks.isPresent()) {
            homeWorksSize = homeWorks.get().size();
        }
        int size = homeWorksSize;

        assertThrows(
            NotFoundException.class,
            () -> homeWorkService.getHomeWork(size)
        );
    }
}