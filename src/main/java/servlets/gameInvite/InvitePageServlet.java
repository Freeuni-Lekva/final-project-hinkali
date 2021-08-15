package servlets.gameInvite;

import commons.beans.UserBean;
import servlets.ContextListener;
import servlets.search.SearchServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gameInvite")
public class InvitePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null){
            resp.sendRedirect("/");
            return;
        }

        req.getRequestDispatcher("/invite.html").forward(req, resp);
    }
}
