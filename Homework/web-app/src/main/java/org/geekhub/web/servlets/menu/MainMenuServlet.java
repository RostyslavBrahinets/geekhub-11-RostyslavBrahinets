package org.geekhub.web.servlets.menu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/main")
public class MainMenuServlet extends HttpServlet {
    @Override
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<html><head><title>Main</title></head><body>");
            writer.write("<h1>Main</h1>");

            writer.write("<form action=\"menu/courses\" method=\"get\">");
            writer.write("<input type=\"submit\" value=\"Courses Menu\"></br></br>");
            writer.write("</form>");

            writer.write("<form action=\"menu/lections\" method=\"get\">");
            writer.write("<input type=\"submit\" value=\"Lections Menu\"></br></br>");
            writer.write("</form>");

            writer.write("<form action=\"menu/people\" method=\"get\">");
            writer.write("<input type=\"submit\" value=\"People Menu\"></br></br>");
            writer.write("</form>");

            writer.write("<form action=\"menu/resources\" method=\"get\">");
            writer.write("<input type=\"submit\" value=\"Resources Menu\"></br></br>");
            writer.write("</form>");

            writer.write("<form action=\"menu/homeworks\" method=\"get\">");
            writer.write("<input type=\"submit\" value=\"Home Works Menu\"></br></br>");
            writer.write("</form>");

            writer.write("<form action=\"menu/logger\" method=\"get\">");
            writer.write("<input type=\"submit\" value=\"Logger Menu\"></br></br>");
            writer.write("</form>");

            writer.write("<form action=\"logs\" method=\"get\">");
            writer.write("<input type=\"submit\" value=\"Show Logs\">");
            writer.write("</form>");

            writer.write("</body></html>");
        }
    }
}
