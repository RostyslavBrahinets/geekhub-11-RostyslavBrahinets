package org.geekhub.web.controllers.menu;

import config.AppConfig;
import models.HomeWork;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.HomeWorkService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.geekhub.web.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@Controller
@RequestMapping("/menu/homework")
public class HomeworkController {
    @GetMapping("")
    public String index(
        HttpSession session,
        Model model
    ) {
        String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);
        model.addAttribute("userName", userName);
        return "menu/homework/index";
    }

    @GetMapping("/{command}")
    public String doCommand(
        @PathVariable("command") String command,
        Model model
    ) {
        return switch (command) {
            case "show-all" -> getViewForShowAll(model);
            case "add" -> "menu/homework/add";
            case "delete" -> "menu/homework/delete";
            case "show" -> "menu/homework/show";
            default -> "menu/homework/index";
        };
    }

    @PostMapping("/add")
    public String show(
        @ModelAttribute("task") String task,
        @ModelAttribute("date") String date,
        @ModelAttribute("time") String time,
        @ModelAttribute("lectionId") int lectionId
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        HomeWorkService homeWorkService =
            applicationContext.getBean(HomeWorkService.class);

        homeWorkService.addHomeWork(task, LocalDateTime.parse(date + "T" + time), lectionId);

        return "redirect:/menu/homework";
    }

    @PostMapping("/delete")
    public String delete(
        @ModelAttribute("id") int id
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        HomeWorkService homeWorkService =
            applicationContext.getBean(HomeWorkService.class);

        homeWorkService.deleteHomeWork(id);

        return "redirect:/menu/homework";
    }

    @PostMapping("/show")
    public String show(
        @ModelAttribute("id") int id,
        Model model
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        HomeWorkService homeWorkService =
            applicationContext.getBean(HomeWorkService.class);

        Optional<HomeWork> homeWork = homeWorkService.getHomeWork(id);
        homeWork.ifPresent(value -> model.addAttribute("homework", value));

        return "menu/homework/show-by-id";
    }

    private String getViewForShowAll(Model model) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        HomeWorkService homeWorkService =
            applicationContext.getBean(HomeWorkService.class);

        List<HomeWork> homeWork = homeWorkService.getHomeWorks();
        model.addAttribute("homeworks", homeWork);
        return "menu/homework/show-all";
    }
}
