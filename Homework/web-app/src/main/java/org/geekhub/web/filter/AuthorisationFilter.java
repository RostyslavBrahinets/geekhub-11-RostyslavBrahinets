package org.geekhub.web.filter;

import org.geekhub.web.SessionAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthorisationFilter implements Filter {
    @Override
    public void doFilter(
        ServletRequest servletRequest,
        ServletResponse servletResponse,
        FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String path = request.getRequestURI().substring(request.getContextPath().length())
            .replaceAll("[/]+$", "");

        boolean loggedIn = (session != null
            && session.getAttribute(SessionAttributes.USER_NAME_SESSION_PARAMETER) != null);
        boolean allowedPath = path.equals("/auth");

        if (loggedIn || allowedPath) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/auth");
        }
    }
}
