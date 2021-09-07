package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet
public class AccessEdit implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String login =(String) session.getAttribute("login");
        String reqLogin = (String) servletRequest.getParameter("login");
        if (!login.equals(reqLogin)){
            servletResponse.getWriter().println("Error");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
