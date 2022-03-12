package org.geekhub.web.servlets.menu.course;

import config.AppConfig;
import logger.Logger;
import models.Course;
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
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;
import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/courses/show-all")
public class CourseShowAllServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        try {
            showMenu(request, response);
        } catch (Exception e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenu(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException, SQLException {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        CourseService courseService =
            applicationContext.getBean(CourseService.class);

        List<Course> courses = courseService.getCourses();

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses Show All</title></head><body>");
            if (courses.size() == 0) {
                showMenuIfCoursesNotFound(request, response);
                return;
            }
            showMenuIfCoursesFound(courses, response);
            writer.write("</body></html>");
        }
    }

    private void showMenuIfCoursesNotFound(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Courses not found!<h1>");

            HttpSession session = request.getSession();
            String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);

            if (userName.equals("admin")) {
                writer.write("<h1>Do you want add new course?<h1>");
                writer.write("<form action=\"add\" method=\"get\">");
                writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                    + "\" value=\"Add new\"></br></br>");
                writer.write("</form>");
            }
        }
    }

    private void showMenuIfCoursesFound(
        List<Course> courses,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Courses:</h1><ul>");
            for (Course course : courses) {
                writer.write("<li>" + course.getName() + "</li>");
            }
            writer.write("<ul>");
        }
    }
}