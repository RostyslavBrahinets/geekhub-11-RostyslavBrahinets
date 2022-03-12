package org.geekhub.web.servlets.menu.homework;

import config.AppConfig;
import logger.Logger;
import models.HomeWork;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.HomeWorkService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.geekhub.web.servlets.SessionAttributes.COMMAND_SESSION_PARAMETER;
import static org.geekhub.web.servlets.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/homeworks/show-all")
public class HomeWorkShowAllServlet extends HttpServlet {
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
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        HomeWorkService homeWorkService =
            applicationContext.getBean(HomeWorkService.class);

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Home Works Show All</title></head><body>");
            List<HomeWork> homeWorks = homeWorkService.getHomeWorks();
            if (homeWorks.size() == 0) {
                showMenuIfHomeWorksNotFound(request, response);
                return;
            }
            showMenuIfHomeWorksFound(homeWorks, response);
            writer.write("</body></html>");
        } catch (Exception e) {
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
