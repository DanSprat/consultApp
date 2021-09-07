package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String login = (String) session.getAttribute("login");
        if (login == null){
            String targetURL = ((HttpServletRequest) servletRequest).getRequestURI();
            String args = ((HttpServletRequest) servletRequest).getQueryString();
            if (args != null && args.length()>0){
                targetURL +="?"+args;
            }
            session.setAttribute("targetURL",targetURL);
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
