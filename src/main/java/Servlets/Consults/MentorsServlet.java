package Servlets.Consults;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/mentors")
public class MentorsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        if (mode == null){
            req.setAttribute("mode","consults");
        } else {
            if (!mode.equals("consults") && !mode.equals("schedule")){
                req.setAttribute("message", "Неверный формат запроса");
                req.setAttribute("action","/");
                req.setAttribute("name_button","На главную");
                req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
                return;
            }
            req.setAttribute("mode",mode);
        }
       Set<DataBase.Users.User> mentors =  DataBase.INSTANCE.users.getAll().stream().filter(c->c.is_mentor).collect(Collectors.toSet());
        req.setAttribute("mentors",mentors);
        req.getRequestDispatcher("/mentors.jsp").forward(req,resp);
    }
}
