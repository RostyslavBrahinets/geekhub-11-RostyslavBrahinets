package org.geekhub.web.controllers.menu;

import config.AppConfig;
import models.Resource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import services.ResourceService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

import static org.geekhub.web.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@Controller
@RequestMapping("/menu/resource")
public class ResourceController {
    @GetMapping("")
    public String index(
        HttpSession session,
        Model model
    ) {
        String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);
        model.addAttribute("userName", userName);
        return "menu/resource/index";
    }

    @GetMapping("/{command}")
    public String doCommand(
        @PathVariable("command") String command,
        Model model
    ) {
        return switch (command) {
            case "show-all" -> getViewForShowAll(model);
            case "add" -> "menu/resource/add";
            case "delete" -> "menu/resource/delete";
            case "show" -> "menu/resource/show";
            default -> "menu/resource/index";
        };
    }

    @PostMapping("/add")
    public String show(
        @ModelAttribute("name") String name,
        @ModelAttribute("type") String type,
        @ModelAttribute("data") String data,
        @ModelAttribute("lectionId") int lectionId
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ResourceService resourceService =
            applicationContext.getBean(ResourceService.class);

        resourceService.addResource(name, type, data, lectionId);

        return "redirect:/menu/resource";
    }

    @PostMapping("/delete")
    public String delete(
        @ModelAttribute("id") int id
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ResourceService resourceService =
            applicationContext.getBean(ResourceService.class);

        resourceService.deleteResource(id);

        return "redirect:/menu/resource";
    }

    @PostMapping("/show")
    public String show(
        @ModelAttribute("id") int id,
        Model model
    ) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ResourceService resourceService =
            applicationContext.getBean(ResourceService.class);

        Optional<Resource> resource = resourceService.getResource(id);
        resource.ifPresent(value -> model.addAttribute("resource", value));

        return "menu/resource/show-by-id";
    }

    private String getViewForShowAll(Model model) {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        ResourceService resourceService =
            applicationContext.getBean(ResourceService.class);

        List<Resource> resources = resourceService.getResources();
        model.addAttribute("resources", resources);
        return "menu/resource/show-all";
    }
}
