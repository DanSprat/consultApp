package Servlets;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/settings/delete")
public class DeleteSettingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> params = Collections.list(req.getParameterNames());
        if (params.size()!= 1){
            resp.getWriter().println("Беда");
            return;
        }
        if (DataBase.INSTANCE.settings.remove(params.get(0)) == null){
            resp.getWriter().println("Беда");
            return;
        }
        resp.sendRedirect(req.getContextPath()+"/settings/view");
    }
}
