package org.geekhub.web.servlets.menu.homework;

import logger.Logger;
import models.HomeWork;
import services.HomeWorkService;

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

@WebServlet(urlPatterns = "/menu/homeworks/show")
public class HomeWorksShowServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        showMenu(request, response);
    }

    private void showMenu(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Home Works Show</title></head><body>");
            HomeWorkService homeWorkService = new HomeWorkService();
            List<HomeWork> homeWorks = homeWorkService.getHomeWorks();
            if (homeWorks.size() == 0) {
                showMenuIfHomeWorksNotFound(request, response);
                return;
            }
            showMenuIfHomeWorksFound(homeWorks, response);
            writer.write("</body></html>");
        } catch (SQLException e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenuIfHomeWorksNotFound(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Homework not found!<h1>");

            HttpSession session = request.getSession();
            String userName = (String) session.getAttribute(USER_NAME_SESSION_PARAMETER);

            if (userName.equals("admin")) {
                writer.write("<h1>Do you want add new homework?<h1>");
                writer.write("<form action=\"add\" method=\"get\">");
                writer.write("<input type=\"submit\" name=\"" + COMMAND_SESSION_PARAMETER
                    + "\" value=\"Add new\"></br></br>");
                writer.write("</form>");
            }
        }
    }

    private void showMenuIfHomeWorksFound(
        List<HomeWork> homeWorks,
        HttpServletResponse response
    ) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1>Home Works:</h1><ul>");
            for (HomeWork homeWork : homeWorks) {
                writer.write("<li>"
                    + homeWork.getTask() + " ["
                    + homeWork.getDeadline()
                    + "]</li>");
            }
            writer.write("<ul>");
        }
    }
}
