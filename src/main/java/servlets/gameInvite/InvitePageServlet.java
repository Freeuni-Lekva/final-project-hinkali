package servlets.gameInvite;

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
        if (!ContextListener.TESTING){
            if(SearchServlet.handleUnauthorizedCase(req, resp)) return;
        }

        req.getRequestDispatcher("/invite.html").forward(req, resp);
    }
}
