package org.geekhub.web.servlets.menu.homework;

import exceptions.ValidationException;
import logger.Logger;
import org.geekhub.web.servlets.menu.MenuCommand;
import services.HomeWorkService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.geekhub.web.servlets.SessionAttributes.*;

@WebServlet(urlPatterns = "/menu/homeworks/add")
public class HomeWorksAddServlet extends HttpServlet {
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
        String task = MenuCommand.getValueOfParameter(TASK_SESSION_PARAMETER, request, response);
        String date = MenuCommand
            .getValueOfParameter(DATE_SESSION_PARAMETER, request, response);
        String time = MenuCommand
            .getValueOfParameter(TIME_SESSION_PARAMETER, request, response);
        String lectionId = MenuCommand
            .getValueOfParameter(ID_SESSION_PARAMETER, request, response);
        HttpSession session = request.getSession();
        session.setAttribute(TASK_SESSION_PARAMETER, task);
        session.setAttribute(DATE_SESSION_PARAMETER, date);
        session.setAttribute(TIME_SESSION_PARAMETER, time);
        session.setAttribute(ID_SESSION_PARAMETER, lectionId);
        addCourse(task, date, time, Integer.parseInt(lectionId), response);
    }

    private void showMenu(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Home Works Add</title></head><body>");

            writer.write("<form action=\"add\" method=\"post\">");
            writer.write("<label for=\"task\">Task: </label>");
            writer.write("<input id=\"task\" type=\"text\" name=\""
                + TASK_SESSION_PARAMETER + "\"></br>");
            writer.write("<label for=\"date\">Date of deadline: </label>");
            writer.write("<input id=\"date\" type=\"text\" name=\""
                + DATE_SESSION_PARAMETER + "\" value=\"yyyy-mm-dd\"></br>");
            writer.write("<label for=\"time\">Time of deadline: </label>");
            writer.write("<input id=\"time\" type=\"text\" name=\""
                + TIME_SESSION_PARAMETER + "\" value=\"hh:mm\"></br>");
            writer.write("<label for=\"lectionId\">Lection id: </label>");
            writer.write("<input id=\"lectionId\" type=\"text\" name=\""
                + ID_SESSION_PARAMETER + "\"></br>");
            writer.write("<input type=\"submit\" value=\"Add\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    private void addCourse(
        String task,
        String date,
        String time,
        int lectionId,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Home Works Add</title></head><body>");
            try {
                HomeWorkService homeWorkService = new HomeWorkService();
                homeWorkService.addHomeWork(
                    task,
                    LocalDateTime.parse(date + "T" + time),
                    lectionId)
                ;
            } catch (ValidationException
                | DateTimeParseException
                | IllegalArgumentException
                | SQLException e
            ) {
                Logger logger = new Logger();
                logger.error(getClass().getSimpleName(), e.getMessage(), e);
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
            writer.write("<h1>Home work with task '" + task + "' added</h1>");
            writer.write("</body></html>");
        }
    }
}
