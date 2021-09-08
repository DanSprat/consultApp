package Servlets.Schedule;

import DB.DataBase;
import date.DateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schedule/edit")
public class ScheduleEditServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mentor = req.getParameter("mentor");
        String start = req.getParameter("start");
        String day_of_week = req.getParameter("day_of_week");
        if (mentor == null || start == null || day_of_week == null){
            resp.getWriter().println("Объект не найден");
            return;
        }
        DataBase.Schedule.Key key = new DataBase.Schedule.Key(mentor,Integer.parseInt(day_of_week),Long.parseLong(start));
        DataBase.Schedule.Value value = DataBase.INSTANCE.schedule.findKey(key);
        if (value == null){
            resp.getWriter().println("Объект не найден");
            return;
        }
        DateFormat dateFormat = new DateFormat();
        dateFormat.setDate(value.start);
        req.setAttribute("time",dateFormat.getTime());
        req.setAttribute("schedule",value);
        req.setAttribute("duration",value.duration / 1000 / 60);
        req.getRequestDispatcher("/schedule-edit.jsp").forward(req,resp);
    }
}
