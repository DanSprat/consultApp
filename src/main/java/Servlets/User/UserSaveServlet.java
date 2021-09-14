package Servlets.User;

import DB.DataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@MultipartConfig
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
                    req.setAttribute("message", "Пароли не совпадают");
                    req.setAttribute("action","/login");
                    req.setAttribute("name_button","Назад");
                    req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
                    return;
                }
                DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
                if (user != null){
                    req.setAttribute("message", "Пользователь уже существует");
                    req.setAttribute("action","/login");
                    req.setAttribute("name_button","Назад");
                    req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
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
                String discord =  req.getParameter("discord");
                String progwardsLink = req.getParameter("link");
                Properties properties = new Properties();
                properties.load(new FileInputStream(req.getServletContext().getRealPath("/server.properties")));

                Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
                String image = user.image;
                if (filePart != null){
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                    InputStream fileContent = filePart.getInputStream();
                    Path path = Paths.get(properties.getProperty("HOME")).resolve(user.login);
                    Files.createDirectories(path);
                    Path photoPath = path.resolve(fileName);
                    if (Files.exists(photoPath)){
                        Files.delete(photoPath);
                    }
                    Files.createFile(photoPath);
                    FileOutputStream fileOutputStream = new FileOutputStream(photoPath.toString());
                    fileOutputStream.write(fileContent.readAllBytes());
                    image = "/server/"+user.login+"/"+fileName;
                }

                user = new DataBase.Users.User(user.login, user.password, user.needChangePassword,name,user.is_mentor,email,progwardsLink,discord,image);
                DataBase.INSTANCE.users.remove(user.login);
                DataBase.INSTANCE.users.put(user);
                req.getSession().removeAttribute("name");
                req.getSession().setAttribute("name",user.name);
                resp.sendRedirect("/user/view");
                break;
            }
            case "changePWD":{
                String login = (String)req.getSession().getAttribute("login");
                String reqLogin = req.getParameter("login");
                if (!reqLogin.equals(login)){
                    req.setAttribute("message", "Недостаточно прав");
                    req.setAttribute("action","/");
                    req.setAttribute("name_button","На главную");
                    req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
                    return;
                }
                String password =  req.getParameter("password");
                String samePassword = req.getParameter("samePassword");
                if (password.equals(samePassword)){
                    req.setAttribute("message", "Пароли не совпадают");
                    req.setAttribute("action","/");
                    req.setAttribute("name_button","На главную");
                    req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
                    return;
                }
                DataBase.Users.User user = DataBase.INSTANCE.users.findKey(login);
                DataBase.INSTANCE.users.remove(login);
                DataBase.Users.User newUser = new DataBase.Users.User(user.login,password,user.needChangePassword, user.name,user.is_mentor,user.email,user.progwardsAccountLink,user.discordName,user.image);
                DataBase.INSTANCE.users.put(newUser);
                resp.sendRedirect("/user/view");
                break;
            }
            default: {
                req.setAttribute("message", "Ресурс не найден");
                req.setAttribute("action","/");
                req.setAttribute("name_button","На главную");
                req.getRequestDispatcher("/error-page.jsp").forward(req,resp);
                return;
            }
        }

    }
}
