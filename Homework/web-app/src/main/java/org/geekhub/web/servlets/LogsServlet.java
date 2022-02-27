package org.geekhub.web.servlets;

import logger.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/logs")
public class LogsServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        Logger logger = new Logger();
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Logs</title></head><body>");
            writer.write("<h1>Logs</h1>");

            List<String> logs = logger.getLogs();
            for (String log : logs) {
                writer.write("<p>" + log + "</p>");
            }

            writer.write("</body></html>");
        }
    }
}
