package Servlets;

import DB.DataBase;
import date.DateFormat;
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


@WebServlet("/consults/select")
public class ConsultationSelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mentor = req.getParameter("mentor");
        String time = req.getParameter("time");
        if (mentor == null || time == null ) { resp.getWriter().println("Error"); }
        DataBase.Consultations.Consultation consultation = DataBase.INSTANCE.consultations.findKey(new DataBase.Consultations.Key(mentor,Long.parseLong(time)));
        if (consultation == null){resp.getWriter().println("Error"); }
        req.setAttribute("consultation",consultation);

        DataBase.Users.User student = DataBase.INSTANCE.users.findKey((String) req.getSession().getAttribute("login"));
        DataBase.Users.User userMentor = DataBase.INSTANCE.users.findKey(mentor);
        req.setAttribute("mentor", userMentor);
        req.setAttribute("student", student);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(consultation.start),ZoneId.systemDefault());
        SiteDate siteDate = new SiteDate(zonedDateTime.getDayOfWeek().getValue(),zonedDateTime.getDayOfMonth(),zonedDateTime.getMonthValue(),consultation.start);
        ZonedDateTime duration = ZonedDateTime.ofInstant(Instant.ofEpochMilli(consultation.duration),ZoneId.systemDefault());

        req.setAttribute("duration", duration.getMinute()+" минут");
        req.setAttribute("dtime",siteDate+" "+ DateFormat.timeToString(zonedDateTime.getHour(),zonedDateTime.getMinute()));
        req.getRequestDispatcher("/consultation-select.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mentor = req.getParameter("mentor");
        Long start = Long.parseLong(req.getParameter("start"));
        DataBase.Consultations.Key key = new DataBase.Consultations.Key(mentor,start);
        DataBase.Consultations.Consultation consultation = DataBase.INSTANCE.consultations.remove(key);
        if (consultation == null){
            resp.getWriter().println("Error");
        }
        String studentLogin = (String) req.getSession().getAttribute("login");
        DataBase.Users.User student = DataBase.INSTANCE.users.findKey(studentLogin);
        String comment = req.getParameter("comment");
        consultation = new DataBase.Consultations.Consultation(consultation.mentor,consultation.start,consultation.duration,student.getLogin(),comment);
        if (DataBase.INSTANCE.consultations.put(consultation)){
            resp.sendRedirect("/consults/my");
        } else {
            resp.getWriter().println("Error");
        }
    }
}
