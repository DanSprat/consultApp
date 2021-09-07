package Filters;

import DB.DataBase;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class MentorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpSession session = ((HttpServletRequest) servletRequest).getSession();
       String login = (String) session.getAttribute("login");
       DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
       if (user == null || !user.is_mentor){
           servletResponse.getWriter().println("Error");
           return;
       }
       filterChain.doFilter(servletRequest,servletResponse);

    }
}
