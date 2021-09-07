package Servlets;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBase.Users.User user = DataBase.INSTANCE.users.findKey((String) req.getSession().getAttribute("login"));
        req.getSession().setAttribute("user",user);
        req.getRequestDispatcher("/user-edit.jsp").forward(req,resp);
    }
}
