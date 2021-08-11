package Servlets;

import DB.DataBase;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mentor/info")
public class MentorInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String check = req.getParameter("check");
        if (check !=null && check.trim().length() != 0)
            System.out.println(check);
        else System.out.println("check is null");
        String id = req.getParameter("id");
        DataBase.Users.User someUser = DataBase.INSTANCE.users.findKey(id);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        if (someUser == null || !someUser.is_mentor)
            resp.getWriter().println("Ментор не найден");
        else {
            String str = new Gson().toJson(someUser);
            resp.getWriter().println(str);
        }
    }
}
