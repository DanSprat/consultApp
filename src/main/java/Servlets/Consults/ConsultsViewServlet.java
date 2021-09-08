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
        DataBase.Users.User mentor = DataBase.INSTANCE.users.findKey(mentorLogin);
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
