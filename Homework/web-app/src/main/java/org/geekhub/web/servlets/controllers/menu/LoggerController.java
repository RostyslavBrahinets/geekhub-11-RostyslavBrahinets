package org.geekhub.web.servlets.controllers.menu;

import exceptions.NotFoundException;
import logger.Logger;
import logger.LoggerStorageFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu/logger")
public class LoggerController {
    @GetMapping("")
    public String index() {
        return "menu/logger/index";
    }

    @GetMapping("/settings")
    public String settings() {
        return "menu/logger/settings";
    }

    @PostMapping("/settings")
    public String delete(
        @ModelAttribute("loggerStorage") String loggerStorage
    ) {
        switch (loggerStorage) {
            case "In Memory" -> LoggerStorageFactory.setStorage("memory");
            case "In File" -> LoggerStorageFactory.setStorage("file");
            case "In Memory And File" -> LoggerStorageFactory.setStorage("memory_file");
            default -> throw new NotFoundException("Logger Storage Not Found");
        }

        return "redirect:/";
    }

    @GetMapping("/show")
    public String show(Model model) {
        Logger logger = new Logger();
        List<String> logs = logger.getLogs();
        model.addAttribute("logs", logs);
        return "menu/logger/show";
    }
}
