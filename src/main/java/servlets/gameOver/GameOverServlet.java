package servlets.gameOver;

import commons.beans.StatsBean;
import commons.beans.UserBean;
import dao.implementation.StatsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/game_over")
public class GameOverServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute(UserBean.USER_ATTR);
        StatsDao dao = (StatsDao) req.getServletContext().getAttribute(StatsDao.STATS_DAO_ATTR);
        StatsBean stats = dao.getStatsById(id);
        stats.setGamesPlayed(stats.getGamesPlayed() + 1);
        if(Integer.parseInt(req.getParameter("winner")) == id){
            stats.setWins(stats.getWins() + 1);
            stats.setPoints(stats.getPoints() + 3);
        }
        if(Integer.parseInt(req.getParameter("loser")) == id){
            stats.setLosses(stats.getLosses() + 1);
        }
        if(Integer.parseInt(req.getParameter("draw")) == id){
            stats.setDraws(stats.getDraws() + 1);
            stats.setPoints(stats.getPoints() + 1);
        }
        dao.setStats(stats);
        resp.sendRedirect("/home");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/gameOver/gameOver.jsp").forward(req,resp);
    }
}
