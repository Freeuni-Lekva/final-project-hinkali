package servlets.login;

import commons.beans.UserBean;
import dao.implementation.UserDAO;
import dao.interfaces.UserDAOInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    public static final String SUCCESS_STR = "success";
    public static final String FAILED_STR = "failed";
    public static final String PASSWORD_FAILED_STR = "Pfailed";






    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.html").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOInterface userDao = (UserDAOInterface) getServletContext().getAttribute(UserDAO.USER_DAO_ATTR);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String state;

            UserBean currUser = userDao.getUser(username, password);
            UserBean userByName= userDao.getUserByUsername(username);

        if(currUser != null){
            state = SUCCESS_STR;
            //resp.sendRedirect("home");
            req.getSession().setAttribute(UserBean.USER_ATTR, currUser.getId());
        }else{
            state = FAILED_STR;
        }
        if(currUser==null&&userByName!=null)
            state=PASSWORD_FAILED_STR;
        resp.getWriter().print(state);
    }

}
