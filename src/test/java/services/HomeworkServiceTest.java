package services;

import exceptions.ValidationException;
import models.Homework;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkServiceTest {
    private static HomeworkService homeworkService;

    @BeforeAll
    static void setUp() {
        homeworkService = new HomeworkService();
        List<String> tasks = new ArrayList<>();
        tasks.add("Task");
        homeworkService.addHomework(tasks);
    }

    @Test
    void getHomeworks_doNothing_withoutError() {
        List<Homework> homeworks = new ArrayList<>();
        List<String> tasks = new ArrayList<>();
        tasks.add("Task");
        homeworks.add(new Homework(tasks));
        assertEquals(homeworkService.getHomeworks(), homeworks);
    }

    @Test
    void addHomework_doNothing_withoutError() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Task");
        assertDoesNotThrow(() -> homeworkService.addHomework(tasks));
    }

    @Test
    void addHomework_doNothing_forTasksAreNull() {
        assertDoesNotThrow(() -> homeworkService.addHomework(null));
    }

    @Test
    void deleteHomework_doNothing_withoutError() {
        assertDoesNotThrow(() -> homeworkService.deleteHomework(0));
    }

    @Test
    void deleteHomework_doNothing_forIdIsNegative() {
        assertDoesNotThrow(() -> homeworkService.deleteHomework(-1));
    }

    @Test
    void deleteHomework_doNothing_forIdIsMoreThenExpected() {
        assertDoesNotThrow(() -> homeworkService.deleteHomework(homeworkService.getHomeworks().size()));
    }

    @Test
    void getHomework_doNothing_withoutError() {
        List<String> tasks = new ArrayList<>();
        tasks.add("Task");
        Homework homework = new Homework(tasks);

        assertEquals(homeworkService.getHomework(0), homework);
    }

    @Test
    void getHomework_returnNull_forIdIsNegative() {
        assertNull(homeworkService.getHomework(-1));
    }

    @Test
    void getHomework_returnNull_forIdIsMoreThenExpected() {
        assertNull(homeworkService.getHomework(homeworkService.getHomeworks().size()));
    }
}