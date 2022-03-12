package org.geekhub.web.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RequestParameter {
    public Optional<String> extractParameter(String name, HttpServletRequest request) {
        String parameter = request.getParameter(name);
        return Optional.ofNullable(parameter);
    }

    public void setRequestParameterToSessionAttribute(
        String name,
        HttpServletRequest request
    ) {
        Optional<String> parameter = extractParameter(name, request);
        HttpSession session = request.getSession();
        session.setAttribute(name, parameter.orElse(""));
    }
}
