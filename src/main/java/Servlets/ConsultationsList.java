package Servlets;

import DB.DataBase;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@WebServlet("/schedule/list")
public class ConsultationsList extends HttpServlet {
    public static final String ALL = "ALL";
    public static final String PAST = "PAST";
    public static final String FUTURE = "FUTURE";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        String filter = req.getParameter("filter");
        if (filter == null || filter.trim().length() ==0){
            filter = FUTURE;
        }
        if (!(filter.equalsIgnoreCase(ALL) || filter.equalsIgnoreCase(PAST) || filter.equalsIgnoreCase(FUTURE))){
            resp.getWriter().println("Incorrect filter. Use PAST, ALL or FUTURE");
            return;
        }
        int page = Integer.parseInt(req.getParameter("page"));
        int count = Integer.parseInt(req.getParameter("count"));
        Predicate <DataBase.Consultations.Consultation> predicate = null;
        long time = System.currentTimeMillis();
        if (filter.equalsIgnoreCase("future"))
            predicate = consultation -> consultation.start > time;
        else if (filter.equalsIgnoreCase("past"))
            predicate = consultation -> consultation.start <time;
        String mentor = req.getParameter("mentor");
        if (mentor != null && mentor.trim().length() != 0){
            if (predicate!= null)
                predicate.and(consultation -> consultation.mentor.equalsIgnoreCase(mentor));
            else
                predicate = consultation -> consultation.mentor.equalsIgnoreCase(mentor);
        }
        String sortDate = req.getParameter("sortDate");
        Comparator <DataBase.Consultations.Consultation> consultationComparator = null;

        if (sortDate.equalsIgnoreCase("inc"))
            consultationComparator = Comparator.comparing(consultation -> consultation.start);
        else {
            consultationComparator = Comparator.comparing(consultation -> -consultation.start);
        }
        List<DataBase.Consultations.Consultation> consultations = null;
        if (predicate == null){
            consultations = DataBase.INSTANCE.consultations.getAll(consultationComparator);
        } else {
            consultations = DataBase.INSTANCE.consultations.select(predicate,consultationComparator);
        }
        String json = new Gson().toJson(consultations);
        resp.getWriter().println(json);
    }
}
