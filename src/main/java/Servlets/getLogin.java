package Servlets;

import DB.DataBase;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getLogin")
public class getLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mentor =  req.getParameter("mentor");
        Long start = Long.parseLong(req.getParameter("start"));
        DataBase.Consultations.Key key = new DataBase.Consultations.Key(mentor,start);
        DataBase.Consultations.Consultation consultation = DataBase.INSTANCE.consultations.findKey(key);
        resp.getWriter().println(new Gson().toJson(consultation));
    }
}
