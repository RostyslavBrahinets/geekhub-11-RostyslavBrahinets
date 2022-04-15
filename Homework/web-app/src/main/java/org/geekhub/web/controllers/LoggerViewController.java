package org.geekhub.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logger")
public class LoggerViewController {
    @GetMapping
    public String index() {
        return "logger/index";
    }

    @GetMapping("/settings")
    public String settings() {
        return "logger/settings";
    }
}
