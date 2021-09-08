package Servlets.Schedule;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schedule/add")
public class ScheduleAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("login");
        DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
        if (!user.is_mentor){
            resp.getWriter().println("not enough rights");
        }
        String mentor = req.getParameter("mentor");
        if (mentor != null){
            req.setAttribute("mentor",mentor);
        }
        DataBase.Settings.Record record = DataBase.INSTANCE.settings.findKey("CONSULT_DURATION");
        if (record != null){
            req.setAttribute("duration",Integer.parseInt(record.value) / 1000 / 60 + " минут");
        }
        req.getRequestDispatcher("/schedule-add.jsp").forward(req,resp);

    }
}
