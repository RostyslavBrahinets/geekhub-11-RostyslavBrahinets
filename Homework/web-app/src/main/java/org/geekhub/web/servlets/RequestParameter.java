package org.geekhub.web.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

public class RequestParameter {
    public Optional<String> extractParameter(String name, HttpServletRequest request) {
        String parameter = request.getParameter(name);
        return Optional.ofNullable(parameter);
    }

    public void setRequestParameterToSessionAttribute(
        String name,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        Optional<String> parameter;
        try {
            parameter = extractParameter(name, request);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute(name, parameter.orElse(""));
    }
}
