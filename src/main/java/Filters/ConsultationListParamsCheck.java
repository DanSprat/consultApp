package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/schedule/list")
public class ConsultationListParamsCheck implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String sortDate = servletRequest.getParameter("sortDate");
        if (sortDate == null || !(sortDate.equalsIgnoreCase("asc") || sortDate.equalsIgnoreCase("desc"))){
            servletResponse.getWriter().println("Incorrect parameter sortDate. Use ASC or DESC ");
            return;
        }
        String page = servletRequest.getParameter("page");
        if (page == null){
            servletResponse.getWriter().println("Parameter \"page\" is missing.");
            return;
        }
        if (Integer.parseInt(page) < 1){
            servletResponse.getWriter().println("Negative number of page. It must be positive.");
            return;
        }
        String count = servletRequest.getParameter("count");
        if (count == null){
            servletResponse.getWriter().println("Parameter \"count\" is missing.");
            return;
        }
        if (Integer.parseInt(count)<1){
            servletResponse.getWriter().println("Negative count of page's notes. It must be positive");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
