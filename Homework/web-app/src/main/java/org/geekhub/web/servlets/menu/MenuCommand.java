package org.geekhub.web.servlets.menu;

import exceptions.NotFoundException;
import org.geekhub.web.servlets.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;
import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

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
                    + "\" value=\"Add\"></br></br>");
                writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                    + "\" value=\"Delete\"></br></br>");
            }

            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Show\"></br></br>");
        }
    }

    public static void handleCommands(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        RequestParameter parameter = new RequestParameter();
        parameter.setRequestParameterToSessionAttribute(
            COMMAND_SESSION_PARAMETER,
            request
        );

        HttpSession session = request.getSession();
        String command = (String) session.getAttribute(COMMAND_SESSION_PARAMETER);

        switch (command) {
            case "Show all" -> response.sendRedirect(request.getRequestURI() + "/show-all");
            case "Add" -> response.sendRedirect(request.getRequestURI() + "/add");
            case "Delete" -> response.sendRedirect(request.getRequestURI() + "/delete");
            case "Show" -> response.sendRedirect(request.getRequestURI() + "/show");
            default -> throw new NotFoundException("Command not found");
        }
    }

    public static String getValueOfParameter(
        String parameter,
        HttpServletRequest request
    ) throws IOException {
        RequestParameter requestParameter = new RequestParameter();
        Optional<String> optionalId = requestParameter.extractParameter(parameter, request);
        return optionalId.orElse("");
    }
}
