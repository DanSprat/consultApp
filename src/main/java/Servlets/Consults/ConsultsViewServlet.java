package Servlets.Consults;


import DB.DataBase;
import date.SiteDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;


@WebServlet("/consults/view")
public class ConsultsViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mentorLogin = req.getParameter("mentor");
        if (mentorLogin == null) {
            req.setAttribute("message", "Неверный формат запроса");
            req.setAttribute("action","/");
            req.setAttribute("name_button","На главную");
            req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
        }
        DataBase.Users.User mentor = DataBase.INSTANCE.users.findKey(mentorLogin);
        if (mentor == null) {
            req.setAttribute("message", "Пользователь не найден");
            req.setAttribute("action","/mentors");
            req.setAttribute("name_button","К выбору наставника");
            req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
        }
        List<DataBase.Consultations.Consultation> consultations = DataBase.INSTANCE.consultations.select(o->o.mentor.equals(mentorLogin), (Comparator.comparingLong(o -> o.start)));
        ZonedDateTime zonedDateTime;
        SiteDate siteDate;
        HashMap<SiteDate, ArrayList<DataBase.Consultations.Consultation>> consultationsByDate = new HashMap<>();
        for (var x:consultations){
            zonedDateTime =  ZonedDateTime.ofInstant(Instant.ofEpochMilli(x.start), ZoneId.systemDefault());
            siteDate = new SiteDate(zonedDateTime.getDayOfWeek().getValue(), zonedDateTime.getDayOfMonth(), zonedDateTime.getMonthValue(),x.start);
            ArrayList<DataBase.Consultations.Consultation> getConsults = consultationsByDate.get(siteDate);
            if (getConsults == null){
                getConsults = new ArrayList<>();
                getConsults.add(x);
                consultationsByDate.put(siteDate,getConsults);
            } else {
                getConsults.add(x);
            }
        }
        var consultationsList = consultationsByDate.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).collect(Collectors.toList());

        req.setAttribute("mentor",mentor);
        req.setAttribute("consultationsList",consultationsList);
        req.setAttribute("consultations",consultations);
        req.getRequestDispatcher("/consultations-choose.jsp").forward(req,resp);
    }
}
