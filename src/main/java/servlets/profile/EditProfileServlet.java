package servlets.profile;

import commons.beans.UserBean;
import dao.implementation.UserWrapperDao;
import dao.interfaces.UserWrapperInterface;
import model.UserUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/edit-profile")
public class EditProfileServlet extends HttpServlet {
    public static final int INCORRECT_PASSWORD = 0;
    public static final int USERNAME_INVALID = 1;
    public static final int ERROR = 2;
    public static final int SUCCESS = 3;

    private int state = -1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute(UserBean.USER_ATTR) == null) {
            resp.sendRedirect("index.jsp");
            return;
        }
        int currentUserId = (int) session.getAttribute(UserBean.USER_ATTR);
        String strId = req.getParameter("id");
        int profileId = (strId == null || strId.equals("")) ? -1 : Integer.parseInt(strId);
        if (profileId == -1 || profileId != currentUserId)
            resp.sendRedirect("edit-profile?id=" + currentUserId); // send to own profile edit
        else
            req.getRequestDispatcher("/WEB-INF/profile/edit_profile.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserWrapperInterface userDao = (UserWrapperInterface) getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
        int userId = Integer.parseInt(req.getParameter("userId"));
        String username = req.getParameter("username");
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String dateString = req.getParameter("birthday");
        Date birthday = (dateString.equals("")) ? null : Date.valueOf(dateString);
        UserBean user = userDao.getUserById(userId);
        checkAndTryUpdate(user, username, name, surname, oldPassword, newPassword, birthday, userDao);
        resp.getWriter().print(state);
    }

    private void checkAndTryUpdate(UserBean user, String username, String name, String surname,
                                   String oldPassword, String newPassword, Date birthday, UserWrapperInterface userDao) {
        changeUnconstrainedVariables(name, surname, birthday, user);
        if (user.getUsername().equals(username)) {
            if (!newPassword.isEmpty()) {
                if (checkPasswordIncorrect(userDao, user.getUsername(), oldPassword))
                    state = INCORRECT_PASSWORD;
                else if (checkPasswordLength(newPassword)) {
                    user.setPassword(newPassword);

                    tryChangeUserWithSameUsername(userDao, user);
                }
            } else {
                tryChangeUserWithSameUsername(userDao, user);
            }
        } else {
            if (!newPassword.isEmpty()) {
                if (checkPasswordIncorrect(userDao, user.getUsername(), oldPassword))
                    state = INCORRECT_PASSWORD;
                else if (checkPasswordLength(newPassword)) {
                    user.setPassword(newPassword);
                    user.setUsername(username);
                    tryChangeUserWithNewUsername(userDao, user);
                }
            } else {
                user.setUsername(username);
                tryChangeUserWithNewUsername(userDao, user);
            }
        }
    }

    private void changeUnconstrainedVariables(String name, String surname, Date birthday, UserBean user) {
        user.setName(name);
        user.setSurname(surname);
        user.setBirthday(birthday);
    }

    private boolean checkPasswordLength(String password) {
        return password.length() > 4 && password.length() < 61;
    }

    private void tryChangeUserWithSameUsername(UserWrapperInterface userDao, UserBean user) {
        if (userDao.changeUser(user))
            state = SUCCESS;
        else
            state = ERROR;
    }

    private void tryChangeUserWithNewUsername(UserWrapperInterface userDao, UserBean user) {
        if (userDao.changeUser(user))
            state = SUCCESS;
        else
            state = USERNAME_INVALID;
    }

    private boolean checkPasswordIncorrect(UserWrapperInterface userDao, String username, String password) {
        return userDao.getUser(username, password) == null;
    }
}