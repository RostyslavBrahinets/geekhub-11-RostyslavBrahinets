package org.geekhub.web.servlets.controllers.menu;

import config.AppConfig;
import models.Lection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.LectionService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@Controller
@RequestMapping("/menu/lection")
public class LectionController {
    @GetMapping("")
    public String index(
        HttpSession session,
        Model model
    ) {
        String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);
        model.addAttribute("userName", userName);
        return "menu/lection/index";
    }

    @GetMapping("/{command}")
    public String doCommand(
        @PathVariable("command") String command,
        Model model
    ) {
        return switch (command) {
            case "show-all" -> getViewForShowAll(model);
            case "add" -> "menu/lection/add";
            case "delete" -> "menu/lection/delete";
            case "show" -> "menu/lection/show";
            default -> "menu/lection/index";
        };
    }

    @PostMapping("/add")
    public String show(
        @ModelAttribute("name") String name,
        @ModelAttribute("describe") String describe,
        @ModelAttribute("lecturerId") int lecturerId,
        @ModelAttribute("courseId") int courseId
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        LectionService lectionService =
            applicationContext.getBean(LectionService.class);

        lectionService.addLection(name, describe, lecturerId, courseId);

        return "redirect:/menu/lection";
    }

    @PostMapping("/delete")
    public String delete(
        @ModelAttribute("id") int id
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        LectionService lectionService =
            applicationContext.getBean(LectionService.class);

        lectionService.deleteLection(id);

        return "redirect:/menu/lection";
    }

    @PostMapping("/show")
    public String show(
        @ModelAttribute("id") int id,
        Model model
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        LectionService lectionService =
            applicationContext.getBean(LectionService.class);

        Optional<Lection> lection = lectionService.getLection(id);
        lection.ifPresent(value -> model.addAttribute("lection", value));

        return "menu/lection/show-by-id";
    }

    private String getViewForShowAll(Model model) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        LectionService lectionService =
            applicationContext.getBean(LectionService.class);

        List<Lection> lections = lectionService.getLections();
        model.addAttribute("lections", lections);
        return "menu/lection/show-all";
    }
}
