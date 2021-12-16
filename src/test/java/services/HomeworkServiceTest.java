package services;

import models.Homework;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkServiceTest {
    private static HomeworkService homeworkService;
    private static List<String> tasks;

    @BeforeAll
    static void setUp() {
        homeworkService = new HomeworkService();
        tasks = new ArrayList<>();
        tasks.add("Task");
        homeworkService.addHomework(tasks);
    }

    @Test
    void getHomeworks_DoNothing_WithoutError() {
        List<Homework> homeworks = new ArrayList<>();
        homeworks.add(new Homework(tasks));
        assertEquals(homeworkService.getHomeworks(), homeworks);
    }

    @Test
    void addHomework_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> homeworkService.addHomework(tasks));
    }

    @Test
    void addHomework_LoggingException_ForTasksAreNull() {
        assertDoesNotThrow(() -> homeworkService.addHomework(null));
    }

    @Test
    void deleteHomework_DoNothing_WithoutError() {
        assertDoesNotThrow(() -> homeworkService.deleteHomework(0));
    }

    @Test
    void deleteHomework_LoggingException_ForIdIsNegative() {
        assertDoesNotThrow(() -> homeworkService.deleteHomework(-1));
    }

    @Test
    void deleteHomework_LoggingException_ForIdIsMoreThenExpected() {
        assertDoesNotThrow(() -> homeworkService.deleteHomework(homeworkService.getHomeworks().size()));
    }

    @Test
    void getHomework_LoggingException_WithoutError() {
        Homework homework = new Homework(tasks);
        assertEquals(homeworkService.getHomework(0), homework);
    }

    @Test
    void getHomework_ReturnNull_ForIdIsLessThenExpected() {
        assertNull(homeworkService.getHomework(-1));
    }

    @Test
    void getHomework_ReturnNull_ForIdIsMoreThenExpected() {
        assertNull(homeworkService.getHomework(homeworkService.getHomeworks().size()));
    }
}