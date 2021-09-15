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
            req.setAttribute("message", "Недостаточно прав");
            req.setAttribute("action","/");
            req.setAttribute("name_button","На главную");
            req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
            return;
        }
        String mentor = req.getParameter("mentor");
        if (mentor != null){
            req.setAttribute("mentor",mentor);
        } else  {
            req.setAttribute("message", "Неверный формат запроса");
            req.setAttribute("action","/");
            req.setAttribute("name_button","На главную");
            req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
            return;
        }
        DataBase.Settings.Record record = DataBase.INSTANCE.settings.findKey("CONSULT_DURATION");
        if (record != null){
            System.out.println(record.value);
            req.setAttribute("duration",Integer.parseInt(record.value) / 1000 / 60 );
        }
        req.getRequestDispatcher("/schedule-add.jsp").forward(req,resp);

    }
}
