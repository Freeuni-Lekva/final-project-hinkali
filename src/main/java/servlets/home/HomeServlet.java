package servlets.home;

import dao.implementation.StatsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    public static final String PLAY_BUTTON_ID = "0";
    public static final String PROFILE_BUTTON_ID = "1";
    public static final String SEARCH_BUTTON_ID = "2";
    public static final String DECKS_BUTTON_ID = "3";
    public static final String LOG_OUT_BUTTON_ID = "4";
    public static final String INVITE_BUTTON_ID = "5";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String buttonId = req.getParameter("buttonID");

        switch (buttonId) {
            case PLAY_BUTTON_ID:
                resp.sendRedirect("/matchmaking");
                break;
            case PROFILE_BUTTON_ID:
                resp.sendRedirect("/profile");
                break;
            case SEARCH_BUTTON_ID:
                resp.sendRedirect("/search");
                break;
            case DECKS_BUTTON_ID:
                resp.sendRedirect("/choose_deck");
                break;
            case LOG_OUT_BUTTON_ID:
                req.getSession().invalidate();
                resp.sendRedirect("/login");
                break;
            case INVITE_BUTTON_ID:
                resp.sendRedirect("/gameInvite");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/home/home.jsp").forward(req,res);
    }
}
