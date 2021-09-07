package Servlets;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/view")
public class UserViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login == null){
            login = (String) req.getSession().getAttribute("login");
            req.getSession().setAttribute("my", true);
        } else {
            String attrLogin = (String) req.getSession().getAttribute("login");
            if (login.equals(attrLogin))
                req.getSession().setAttribute("my", true);
            else req.getSession().setAttribute("my", false);
        }
        DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
        if (user== null){
            resp.getWriter().println("ERROR");
            return;
        }
        req.setAttribute("user",user);
        req.getRequestDispatcher("/user-view.jsp").forward(req,resp);
    }
}
