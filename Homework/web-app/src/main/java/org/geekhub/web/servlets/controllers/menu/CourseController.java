package org.geekhub.web.servlets.controllers.menu;

import config.AppConfig;
import models.Course;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.CourseService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@Controller
@RequestMapping("/menu/course")
public class CourseController {
    @GetMapping("")
    public String index(
        HttpSession session,
        Model model
    ) {
        String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);
        userName = "admin";

        model.addAttribute("userName", userName);

        return "menu/course/index";
    }

    @GetMapping("/{command}")
    public String doCommand(
        @PathVariable("command") String command,
        Model model
    ) {
        return switch (command) {
            case "show-all" -> getViewForShowAll(model);
            case "add" -> "menu/course/add";
            case "delete" -> "menu/course/delete";
            case "show" -> "menu/course/show";
            default -> "menu/course/index";
        };
    }

    @PostMapping("/add")
    public String show(@ModelAttribute("name") String name) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService =
            applicationContext.getBean(CourseService.class);

        courseService.addCourse(name);

        return "redirect:/menu/course";
    }

    @PostMapping("/delete")
    public String delete(
        @ModelAttribute("id") int id
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService =
            applicationContext.getBean(CourseService.class);

        courseService.deleteCourse(id);

        return "redirect:/menu/course";
    }

    @PostMapping("/show")
    public String show(
        @ModelAttribute("id") int id,
        Model model
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService =
            applicationContext.getBean(CourseService.class);

        Optional<Course> course = courseService.getCourse(id);
        course.ifPresent(value -> model.addAttribute("course", value));

        return "menu/course/show-by-id";
    }

    private String getViewForShowAll(Model model) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService =
            applicationContext.getBean(CourseService.class);

        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "menu/course/show-all";
    }
}
