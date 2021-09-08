package Servlets.Schedule;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/schedule/save")
public class ScheduleSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mode = req.getParameter("mode");
        String time = req.getParameter("time");
        String mentor = req.getParameter("mentor");
        String duration = req.getParameter("duration");
        String dayOfWeek = req.getParameter("dayofweek");
        String start = req.getParameter("start");

        if (mode ==null || time == null || mentor == null || duration == null || dayOfWeek == null){
            resp.getWriter().println("Error");
        }
        String [] times = time.split(":");
        long millis= Long.parseLong(times[0])*60*60*1000 + Long.parseLong(times[1])*60*1000;
        long millisDuration = Long.parseLong(duration)*60*1000;


        if ("edit".equals(mode)){
            DataBase.Schedule.Key key = new DataBase.Schedule.Key(mentor,Integer.parseInt(dayOfWeek),Long.parseLong(start));
            DataBase.Schedule.Value value = DataBase.INSTANCE.schedule.remove(key);
            if (value == null){
                resp.getWriter().println("Element not found");
                return;
            }
        }
        DataBase.Schedule.Value value = new DataBase.Schedule.Value(mentor,Integer.parseInt(dayOfWeek),millis,millisDuration);
        DataBase.INSTANCE.schedule.put(value);
        resp.sendRedirect("/schedule/view?mentor="+mentor);
    }
}
