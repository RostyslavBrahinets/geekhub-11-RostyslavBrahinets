package org.geekhub.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.geekhub.web.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@Controller
public class AuthorisationViewController {
    @GetMapping("/authorisation")
    public String authorisation() {
        return "authorisation";
    }

    @PostMapping("/authorisation")
    public String logIn(
            @ModelAttribute("userName") String userName,
            HttpSession session
    ) {
        List<String> users = List.of("admin", "user");
        if (users.contains(userName)) {
            session.setAttribute(USER_NAME_SESSION_PARAMETER, userName);
            return "redirect:/";
        } else {
            return "redirect:/authorisation";
        }
    }
}
