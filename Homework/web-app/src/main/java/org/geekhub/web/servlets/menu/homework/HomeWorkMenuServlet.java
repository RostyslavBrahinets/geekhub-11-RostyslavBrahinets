package org.geekhub.web.servlets.menu.homework;

import logger.Logger;
import org.geekhub.web.servlets.menu.MenuCommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/menu/homeworks")
public class HomeWorkMenuServlet extends HttpServlet {
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
        try {
            MenuCommand.handleCommands(request, response);
        } catch (Exception e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenu(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Home Works</title></head><body>");
            writer.write("<h1>Home Works</h1>");
            writer.write("<form action=\"homeworks\" method=\"post\">");
            MenuCommand.showCommands(request, response);
            writer.write("</form>");
            writer.write("</body></html>");
        }
    }
}
