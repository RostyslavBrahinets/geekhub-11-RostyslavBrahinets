package org.geekhub.web.servlets.menu.resource;

import exceptions.ValidationException;
import logger.Logger;
import org.geekhub.web.servlets.menu.MenuCommand;
import services.ResourceService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.geekhub.web.servlets.SessionAttributes.*;

@WebServlet(urlPatterns = "/menu/resources/add")
public class ResourcesAddServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showMenu(response);
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        String name = MenuCommand.getValueOfParameter(NAME_SESSION_PARAMETER, request, response);
        String type = MenuCommand.getValueOfParameter(TYPE_SESSION_PARAMETER, request, response);
        String data = MenuCommand.getValueOfParameter(DATA_SESSION_PARAMETER, request, response);
        HttpSession session = request.getSession();
        session.setAttribute(NAME_SESSION_PARAMETER, name);
        session.setAttribute(TYPE_SESSION_PARAMETER, type);
        session.setAttribute(DATA_SESSION_PARAMETER, data);
        addResource(name, type, data, response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Add</title></head><body>");

            writer.write("<form action=\"add\" method=\"post\">");
            writer.write("<label for=\"name\">Name: </label>");
            writer.write("<input id=\"name\" type=\"text\" name=\""
                + NAME_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"type\">Type (URL, VIDEO, BOOK): </label>");
            writer.write("<input id=\"type\" type=\"text\" name=\""
                + TYPE_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"data\">Data: </label>");
            writer.write("<input id=\"data\" type=\"text\" name=\""
                + DATA_SESSION_PARAMETER + "\"></br>");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addResource(
        String name,
        String type,
        String data,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Resources Add</title></head><body>");
            try {
                ResourceService resourceService = new ResourceService();
                resourceService.addResource(name, type.toUpperCase(), data);
            } catch (ValidationException | IllegalArgumentException | SQLException e) {
                Logger logger = new Logger();
                logger.error(getClass().getSimpleName(), e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
            writer.write("<h1>Resource with name '" + name + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}
