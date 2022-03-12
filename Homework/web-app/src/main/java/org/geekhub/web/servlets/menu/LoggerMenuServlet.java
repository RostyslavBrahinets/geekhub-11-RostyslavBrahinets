package org.geekhub.web.servlets.menu;

import exceptions.NotFoundException;
import logger.LoggerStorageFactory;
import org.geekhub.web.servlets.SessionAttributes;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import static org.geekhub.web.servlets.SessionAttributes.LOGGER_STORAGE_SESSION_PARAMETER;

@WebServlet(urlPatterns = "/menu/logger")
public class LoggerMenuServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Logger</title></head><body>");
            writer.write("<h1>Choose where to store logs</h1>");

            writer.write("<form action=\"logger\" method=\"post\">");
            writer.write("<input type=\"submit\" name=\"" + LOGGER_STORAGE_SESSION_PARAMETER
                + "\" value=\"In Memory\"></br></br>");
            writer.write("<input type=\"submit\" name=\"" + LOGGER_STORAGE_SESSION_PARAMETER
                + "\" value=\"In File\"></br></br>");
            writer.write("<input type=\"submit\" name=\"" + LOGGER_STORAGE_SESSION_PARAMETER
                + "\" value=\"In Memory And File\"></br></br>");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }

    @Override
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        Optional<String> optionalLoggerStorage = extractLoggerStorageParameter(request);
        String loggerStorage = optionalLoggerStorage.orElse("");
        HttpSession session = request.getSession();
        session.setAttribute(LOGGER_STORAGE_SESSION_PARAMETER, loggerStorage);
        setLoggerStorage(loggerStorage);
        response.sendRedirect("/main");
    }

    private Optional<String> extractLoggerStorageParameter(HttpServletRequest request) {
        String loggerStorage = request
            .getParameter(SessionAttributes.LOGGER_STORAGE_SESSION_PARAMETER);
        return Optional.ofNullable(loggerStorage);
    }

    private void setLoggerStorage(String loggerStorage) {
        switch (loggerStorage) {
            case "In Memory" -> LoggerStorageFactory.setStorage("memory");
            case "In File" -> LoggerStorageFactory.setStorage("file");
            case "In Memory And File" -> LoggerStorageFactory.setStorage("memory_file");
            default -> throw new NotFoundException("Logger Storage Not Found");
        }
    }
}
