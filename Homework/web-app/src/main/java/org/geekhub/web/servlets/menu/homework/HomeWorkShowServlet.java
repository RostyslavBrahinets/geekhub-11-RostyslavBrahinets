package org.geekhub.web.servlets.menu.homework;

import config.AppConfig;
import exceptions.NotFoundException;
import logger.Logger;
import models.HomeWork;
import org.geekhub.web.servlets.menu.MenuCommand;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.HomeWorkService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.ID_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/homeworks/show")
public class HomeWorkShowServlet extends HttpServlet {
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
        String id = MenuCommand.getValueOfParameter(ID_SESSION_PARAMETER, request);
        HttpSession session = request.getSession();
        session.setAttribute(ID_SESSION_PARAMETER, id);
        try {
            showHomeWork(id, response);
        } catch (Exception e) {
            Logger logger = new Logger();
            logger.error(getClass().getSimpleName(), e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Home Works Show</title></head><body>");

            writer.write("<form action=\"show\" method=\"post\">");
            writer.write("<label for=\"id\">Id: </label>");
            writer.write("<input id=\"id\" type=\"text\" name=\"" + ID_SESSION_PARAMETER + "\">");
            writer.write("<input type=\"submit\" value=\"Show\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void showHomeWork(
        String id,
        HttpServletResponse response
    ) throws IOException, SQLException {
        AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(AppConfig.class);
        HomeWorkService homeWorkService =
            applicationContext.getBean(HomeWorkService.class);

        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Home Work Show</title></head><body>");

            if (id.isBlank()) {
                throw new NotFoundException("Course not found");
            }
            Optional<HomeWork> homeWork = homeWorkService.getHomeWork(Integer.parseInt(id));

            homeWork.ifPresent(
                value -> writer.write(
                    "<h3>"
                        + value.getTask() + " ["
                        + value.getDeadline()
                        + "]</h3>"
                ));

            writer.write("</body></html>");
        }
    }
}
