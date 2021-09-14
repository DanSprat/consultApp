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
        if (name == null || value == null){
            req.setAttribute("message", "Неверный формат запроса");
            req.setAttribute("action","/");
            req.setAttribute("name_button","На главную");
            req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
            return;
        }
        String edit = req.getParameter("edit");
        if ("true".equals(edit)){
            if (DataBase.INSTANCE.settings.remove(name) == null){
                req.setAttribute("message", "Настройка не найдена");
                req.setAttribute("action","/");
                req.setAttribute("name_button","На главную");
                req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
                return;
            }
        }
        DataBase.Settings.Record record = new DataBase.Settings.Record(name,value);
        DataBase.INSTANCE.settings.put(record);
        resp.sendRedirect("/settings/view");

    }
}
