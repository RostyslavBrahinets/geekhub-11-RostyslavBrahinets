package org.geekhub.web.servlets.menu;

import org.geekhub.web.servlets.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.*;

public class MenuCommand {
    private MenuCommand() {
    }

    public static void showCommands(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Show all\"></br></br>");

            if (userName.equals("admin")) {
                writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                    + "\" value=\"Add new\"></br></br>");
                writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                    + "\" value=\"Delete by id\"></br></br>");
            }

            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Show by id\"></br></br>");
        }
    }

    public static void handleCommands(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        RequestParameter parameter = new RequestParameter();
        parameter.setRequestParameterToSessionAttribute(
            COMMAND_SESSION_PARAMETER,
            request, response
        );

        HttpSession session = request.getSession();
        String command = (String) session.getAttribute(COMMAND_SESSION_PARAMETER);

        switch (command) {
            case "Show all" -> response.sendRedirect(request.getRequestURI() + "/show");
            case "Add new" -> response.sendRedirect(request.getRequestURI() + "/add");
            case "Delete by id" -> response.sendRedirect(request.getRequestURI() + "/delete");
            case "Show by id" -> response.sendRedirect(request.getRequestURI() + "/show-by-id");
            default -> response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Command Not Found");
        }
    }

    public static String getValueOfParameter(
        String parameter,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        Optional<String> optionalId = Optional.empty();
        try {
            RequestParameter requestParameter = new RequestParameter();
            optionalId = requestParameter.extractParameter(parameter, request);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
        return optionalId.orElse("");
    }
}
