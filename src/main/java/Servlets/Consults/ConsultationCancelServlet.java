package Servlets.Consults;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/consults/cancel")
public class ConsultationCancelServlet extends HttpServlet {

    // Вопрос по доступу к POST - request
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mentor = req.getParameter("mentor_login");
        String start = req.getParameter("start");
        if (mentor == null || start == null){
            resp.getWriter().println("Error");
            return;
        }
        String student =(String) req.getSession().getAttribute("login");
        DataBase.Consultations.Key key = new DataBase.Consultations.Key(mentor,Long.parseLong(start));
        DataBase.Consultations.Consultation consultation = DataBase.INSTANCE.consultations.remove(key);
        if (consultation == null || consultation.student == null){
            resp.getWriter().println("Консультация не найдена");
            return;
        }

        if(!consultation.student.equals(student)){
            resp.getWriter().println("not enough rights");
            return;
        }
        DataBase.INSTANCE.consultations.put(new DataBase.Consultations.Consultation(consultation.mentor,consultation.start,consultation.duration, null, ""));
        resp.sendRedirect("/consults/my");
    }
}
