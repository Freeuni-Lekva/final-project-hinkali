package servlets.register;

import commons.beans.UserBean;
import dao.implementation.UserDAO;
import dao.interfaces.UserDAOInterface;
import model.UserUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    public static final String SUCCESS_STR = "success";
    public static final String FAILED_STR = "failed";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOInterface userDao = (UserDAOInterface) getServletContext().getAttribute(UserDAO.USER_DAO_ATTR);
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        String dateString = req.getParameter("birthday");
        Date date = (dateString.equals("")) ? null : Date.valueOf(dateString);
        UserBean user = new UserBean(username, name, surname, password, date);
        String state = SUCCESS_STR;
        if (userDao.addUser(user)) {
            req.getSession().setAttribute(UserBean.USER_ATTR, user.getId());
        } else {
            state = FAILED_STR;
        }
        resp.getWriter().print(state);
    }
}
