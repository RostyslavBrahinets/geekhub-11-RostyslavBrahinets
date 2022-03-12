package org.geekhub.web.servlets.menu.lection;

import config.AppConfig;
import logger.Logger;
import org.geekhub.web.servlets.menu.MenuCommand;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.LectionService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.geekhub.web.servlets.SessionAttributes.*;

@WebServlet(urlPatterns = "/menu/lections/add")
public class LectionAddServlet extends HttpServlet {
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
        String name = MenuCommand
            .getValueOfParameter(NAME_SESSION_PARAMETER, request);
        String describe = MenuCommand
            .getValueOfParameter(DESCRIBE_SESSION_PARAMETER, request);
        String lecturerId = MenuCommand
            .getValueOfParameter(LECTURER_ID_SESSION_PARAMETER, request);
        String courseId = MenuCommand
            .getValueOfParameter(ID_SESSION_PARAMETER, request);
        HttpSession session = request.getSession();
        session.setAttribute(NAME_SESSION_PARAMETER, name);
        session.setAttribute(DESCRIBE_SESSION_PARAMETER, describe);
        session.setAttribute(LECTURER_ID_SESSION_PARAMETER, lecturerId);
        session.setAttribute(ID_SESSION_PARAMETER, courseId);
        try {
            addLection(
                name,
                describe,
                Integer.parseInt(lecturerId),
                Integer.parseInt(courseId),
                response
            );
        } catch (Exception e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Lection Add</title></head><body>");

            writer.write("<form action=\"add\" method=\"post\">");
            writer.write("<label for=\"name\">Name of lection: </label>");
            writer.write("<input id=\"name\" type=\"text\" name=\""
                + NAME_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"describe\">Describe: </label>");
            writer.write("<input id=\"describe\" type=\"text\" name=\""
                + DESCRIBE_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"lecturerId\">Lecturer id: </label>");
            writer.write("<input id=\"lecturerId\" type=\"text\" name=\""
                + LECTURER_ID_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"courseId\">Course id: </label>");
            writer.write("<input id=\"courseId\" type=\"text\" name=\""
                + ID_SESSION_PARAMETER + "\"></br>");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addLection(
        String name,
        String describe,
        int lecturerId,
        int courseId,
        HttpServletResponse response
    ) throws IOException, SQLException {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        LectionService lectionService =
            applicationContext.getBean(LectionService.class);

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Lection Add</title></head><body>");
            lectionService.addLection(name, describe, lecturerId, courseId);
            writer.write("<h1>Lection with name '" + name + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}