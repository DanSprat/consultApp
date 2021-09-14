package Servlets;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
        if (user == null || !user.password.equals(password)){
            req.setAttribute("message", "Пользователь не найден");
            req.setAttribute("action","/");
            req.setAttribute("name_button","На главную");
            req.getRequestDispatcher("error-page.jsp").forward(req,resp);
            return;
        }
        session.setAttribute("login",login);
        session.setAttribute("name",user.name);
        session.setAttribute("is_mentor",user.is_mentor);
        DataBase.Settings.Record record = DataBase.INSTANCE.settings.findKey("MAX_INACTIVE_INTERVAL");
        int interval = -1;
        if (record!= null){
            try {
                interval = Integer.parseInt(record.value);
            } catch (NumberFormatException exception){
                exception.printStackTrace();
            }
        }
        req.getSession().setMaxInactiveInterval(interval);
        String target = (String) session.getAttribute("targetURL");
        if (target == null){
            target ="/";
        } else  {
            session.removeAttribute("targetURL");
        }
        resp.sendRedirect(target);

    }
}
