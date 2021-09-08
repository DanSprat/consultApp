package Servlets.Settings;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DataTruncation;

@WebServlet("/settings/save")
public class SettingsSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String value = req.getParameter("value");
        String edit = req.getParameter("edit");
        if ("true".equals(edit)){
            if (DataBase.INSTANCE.settings.remove(name) == null){
                resp.getWriter().println("Ошибка, параметра не существует");
            }
        }
        DataBase.Settings.Record record = new DataBase.Settings.Record(name,value);
        DataBase.INSTANCE.settings.put(record);
        resp.sendRedirect("/settings/view");

    }
}
