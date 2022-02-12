package org.geekhub.web.servlets.menu.resource;

import org.geekhub.web.servlets.menu.MenuCommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/menu/resources")
public class ResourcesMenuServlet extends HttpServlet {
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
            writer.write("<html><head><title>Resources</title></head><body>");
            writer.write("<h1>Resources</h1>");
            writer.write("<form action=\"resources\" method=\"post\">");
            MenuCommand.showCommands(request, response);
            writer.write("</form>");
            writer.write("</body></html>");
        }
    }
}
