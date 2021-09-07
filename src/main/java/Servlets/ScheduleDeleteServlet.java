package Servlets;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schedule/delete")
public class ScheduleDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String start = req.getParameter("start");
        String mentor = req.getParameter("mentor");
        String dayOfWeek = req.getParameter("dayofweek");
        if (start == null || mentor == null || dayOfWeek == null){
            resp.getWriter().println("Error");
            return;
        }
        DataBase.Schedule.Key key = new DataBase.Schedule.Key(mentor,Integer.parseInt(dayOfWeek),Long.parseLong(start));
        DataBase.INSTANCE.schedule.remove(key);
        resp.sendRedirect("/schedule/view?mentor="+mentor);
    }
}
