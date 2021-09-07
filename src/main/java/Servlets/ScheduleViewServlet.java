package Servlets;

import DB.DataBase;
import date.DayOfWeek;
import date.SiteDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/schedule/view")
public class ScheduleViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mentor = req.getParameter("mentor");
        if (mentor == null){
            resp.getWriter().println("Error");
        }
        List <DataBase.Schedule.Value> schedule = DataBase.INSTANCE.schedule.select((o)->o.mentor.equals(mentor),Comparator.comparing(o->o.start));
        DayOfWeek [] dayOfWeeks = DayOfWeek.values();
        HashMap<DayOfWeek, ArrayList<DataBase.Schedule.Value>> hashMap = new HashMap<>();
        ArrayList <DataBase.Schedule.Value> list;
        for (var day: dayOfWeeks){
            hashMap.put(day,new ArrayList<>());
        }
        for (var item: schedule){
            hashMap.get(dayOfWeeks[item.day_of_week]).add(item);
        }
        var sortedSchedule = hashMap.entrySet().stream().sorted(Comparator.comparing(o->o.getKey())).collect(Collectors.toList());
        req.setAttribute("schedule",sortedSchedule);
        req.setAttribute("mentor",mentor);
        req.setAttribute("name",DataBase.INSTANCE.users.findKey(mentor).name);
        req.getRequestDispatcher("/schedule-view.jsp").forward(req,resp);
    }
}
