package org.geekhub.web.servlets.menu.course;

import org.geekhub.web.servlets.RequestParameter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/courses")
public class CoursesMenuServlet extends HttpServlet {
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

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Courses</title></head><body>");
            writer.write("<h1>Courses</h1>");

            writer.write("<form action=\"courses\" method=\"post\">");
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Show all\"></br></br>");
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Add new\"></br></br>");
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Delete by id\"></br></br>");
            writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                + "\" value=\"Show by id\"></br></br>");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }
}
