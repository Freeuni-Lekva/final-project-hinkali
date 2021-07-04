package servlets.register;

import commons.beans.UserBean;
import dao.implementation.UserDAO;
import dao.interfaces.UserDAOInterface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

@WebServlet("/register-attempt")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOInterface userDao = (UserDAOInterface) getServletContext().getAttribute(UserDAO.USER_DAO_ATTR);
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        Date date = Date.valueOf(req.getParameter("birthday"));
        String hash = generateHash(password);
        UserBean user = new UserBean(username, name, surname, hash, date);
        if (userDao.addUser(user)) {
            //req.getRequestDispatcher("").forward(req, resp);
        } else {
            // display error message
        }

    }

    private String generateHash(String password) {
        MessageDigest md = null;
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(passwordBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] bytes = md.digest();
        StringBuilder builder = new StringBuilder();
        for (int aByte : bytes) {
            int val = aByte;
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) builder.append('0'); // leading 0
            builder.append(Integer.toString(val, 16));
        }
        return builder.toString();
    }
}
