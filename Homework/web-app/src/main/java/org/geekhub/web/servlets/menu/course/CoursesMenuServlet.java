package org.geekhub.web.servlets.menu.course;

import org.geekhub.web.servlets.RequestParameter;
import org.geekhub.web.servlets.menu.MenuCommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;
import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/courses")
public class CoursesMenuServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showMenu(request, response);
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        MenuCommand.handleCommands(request, response);
    }

    private void showMenu(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses</title></head><body>");
            writer.write("<h1>Courses</h1>");
            writer.write("<form action=\"courses\" method=\"post\">");
            MenuCommand.showCommands(request, response);
            writer.write("</form>");
            writer.write("</body></html>");
        }
    }
}
