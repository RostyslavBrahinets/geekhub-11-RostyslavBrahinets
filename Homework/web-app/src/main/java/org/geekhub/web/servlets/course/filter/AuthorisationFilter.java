package org.geekhub.web.servlets.course.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.geekhub.web.servlets.course.SessionAttributes.USER_NAME_SESSION_PARAMETER;

@WebFilter(urlPatterns = "/*")
public class AuthorisationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

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
            && session.getAttribute(USER_NAME_SESSION_PARAMETER) != null);
        boolean allowedPath = path.equals("/auth");

        if (loggedIn || allowedPath) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/auth");
        }
    }

    @Override
    public void destroy() {
    }
}
