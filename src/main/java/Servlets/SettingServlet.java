package Servlets;
import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/settings")
public class SettingServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<DataBase.Settings.Record> list  = DataBase.INSTANCE.settings.getAll();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        if (list == null || list.size() == 0)
            resp.getWriter().println("Список параметров пуст");
        else {
            for (DataBase.Settings.Record record: list)
                resp.getWriter().println(record.name+": "+record.value);
        }
    }
}
