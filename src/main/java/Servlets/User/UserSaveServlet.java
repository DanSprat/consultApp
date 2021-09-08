package Servlets.User;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringReader;

@WebServlet("/user/save")
public class UserSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mode = req.getParameter("mode");
        switch (mode){
            case "add": {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                String confirmPassword = req.getParameter("confirmPassword");
                if (!password.equals(confirmPassword)){
                    resp.getWriter().println("Error");
                    return;
                }
                DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
                if (user != null){
                    resp.getWriter().println("User already exist");
                    return;
                }
                user = new DataBase.Users.User(login,password,"",false,"");
                DataBase.INSTANCE.users.put(user);
                resp.sendRedirect("/login");
                break;
            }
            case "edit": {
                DataBase.Users.User user = DataBase.INSTANCE.users.findKey((String) req.getSession().getAttribute("login"));
                String name = req.getParameter("name");
                String email =  req.getParameter("email");
                String image =  req.getParameter("image");
                String discord =  req.getParameter("discord");
                String progwardsLink = req.getParameter("link");
                user = new DataBase.Users.User(user.login, user.password, user.needChangePassword,name,user.is_mentor,email,progwardsLink,discord,image);
                DataBase.INSTANCE.users.remove(user.login);
                DataBase.INSTANCE.users.put(user);
                req.getSession().removeAttribute("name");
                req.getSession().setAttribute("name",user.name);
                resp.sendRedirect("/user/view");
                break;
            }
            case "changePWD":{
                System.out.println("На смене пароля");
                String login = (String)req.getSession().getAttribute("login");
                String reqLogin = req.getParameter("login");
                if (!reqLogin.equals(login)){
                    resp.getWriter().println("Error");
                    return;
                }
                String password =  req.getParameter("password");
                String samePassword = req.getParameter("samePassword");
                if (password.equals(samePassword)){
                    resp.getWriter().println("Error");
                }
                DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
                DataBase.INSTANCE.users.remove(login);
                DataBase.Users.User newUser = new DataBase.Users.User(user.login,password,user.needChangePassword, user.name,user.is_mentor,user.email,user.progwardsAccountLink,user.discordName,user.image);
                DataBase.INSTANCE.users.put(newUser);
                resp.sendRedirect("/user/view");
                break;
            }
            default: {
                resp.getWriter().println("Error");
            }
        }

    }
}
