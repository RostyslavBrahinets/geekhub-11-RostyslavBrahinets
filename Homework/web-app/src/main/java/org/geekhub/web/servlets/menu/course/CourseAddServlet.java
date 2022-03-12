package org.geekhub.web.servlets.menu.course;

import config.AppConfig;
import logger.Logger;
import org.geekhub.web.servlets.menu.MenuCommand;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CourseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.geekhub.web.servlets.SessionAttributes.NAME_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/courses/add")
public class CourseAddServlet extends HttpServlet {
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
        String name = MenuCommand.getValueOfParameter(NAME_SESSION_PARAMETER, request);
        HttpSession session = request.getSession();
        session.setAttribute(NAME_SESSION_PARAMETER, name);
        try {
            addCourse(name, response);
        } catch (Exception e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Course Add</title></head><body>");

            writer.write("<form action=\"add\" method=\"post\">");
            writer.write("<label for=\"name\">Name: </label>");
            writer.write("<input id=\"name\" type=\"text\" name=\""
                + NAME_SESSION_PARAMETER + "\">");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addCourse(
        String name,
        HttpServletResponse response
    ) throws IOException, SQLException {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService =
            applicationContext.getBean(CourseService.class);

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Course Add</title></head><body>");
            courseService.addCourse(name);
            writer.write("<h1>Course with name '" + name + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}
