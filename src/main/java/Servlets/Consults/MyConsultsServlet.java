package Servlets.Consults;

import DB.DataBase;
import OutputClasses.InfoConsult;
import date.DateFormat;
import date.SiteDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/consults/my")
public class MyConsultsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login =(String) req.getSession().getAttribute("login");
        String mentor = req.getParameter("mentor");
        String startString = req.getParameter("start");
        DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
        if (mentor !=null && startString!= null ){
            DataBase.Consultations.Consultation consultation = DataBase.INSTANCE.consultations.findKey(new DataBase.Consultations.Key(mentor,Long.parseLong(startString)));
            String person;
            if (user.is_mentor){
                person = consultation.mentor;
            } else {
                person = consultation.student;
            }
            if (consultation!= null && login.equals(person)){
                req.setAttribute("mentor_login",mentor);
                req.setAttribute("start",consultation.start);
                req.setAttribute("mentor",DataBase.INSTANCE.users.findKey(mentor).name);
                req.setAttribute("student",DataBase.INSTANCE.users.findKey(consultation.student).name);
                req.setAttribute("duration",(consultation.duration / 1000 / 60)+" минут");
                DateFormat dateFormat = new DateFormat();
                dateFormat.setDate(consultation.start);
                req.setAttribute("datetime",new SiteDate(consultation.start)+" "+ dateFormat.getTime());
                req.setAttribute("comment",consultation.comment);
                req.getRequestDispatcher("/consultation-view.jsp").forward(req,resp);
            } else {
                req.setAttribute("message", "Консультация не найдена");
                req.setAttribute("action","/consults/my");
                req.setAttribute("name_button","Назад");
                req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
                return;
            }
        } else {
            List<DataBase.Consultations.Consultation> consultations;
            if (user.is_mentor){
                consultations = DataBase.INSTANCE.consultations.select((o)->(o.mentor.equals(login) && o.student!=null), Comparator.comparing(o->o.start));
            } else {
                consultations = DataBase.INSTANCE.consultations.select((o)->(o.student!=null && o.student.equals(login)),Comparator.comparing(o->o.start));
            }

            ArrayList<InfoConsult> infoConsults = new ArrayList<>();
            consultations.forEach(consultation -> {infoConsults.add(new InfoConsult(consultation));});
            req.setAttribute("consultations",infoConsults);
            req.getRequestDispatcher("/my-consultations.jsp").forward(req,resp);
        }
    }
}
