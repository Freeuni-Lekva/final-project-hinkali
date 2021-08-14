package servlets.game;

import commons.beans.UserBean;
import model.GameManager;
import model.game.Game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/play")
public class PlayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null) {
            resp.sendRedirect("register");
            return;
        }
        GameManager manager = (GameManager) getServletContext().getAttribute(GameManager.GAME_MANAGER_ATTR);
        int gameId = Integer.parseInt(req.getParameter("gameId"));
        int userId = (int) req.getSession().getAttribute(UserBean.USER_ATTR);
        Map<Integer, List<Integer>> gameUserIds = manager.getGameUserIds();
        List<Integer> currGameUserIdsList = gameUserIds.get(gameId);
        if (currGameUserIdsList == null) {
            currGameUserIdsList = new ArrayList<>();
            currGameUserIdsList.add(userId);
            gameUserIds.put(gameId, currGameUserIdsList);
            resp.sendRedirect("play?gameId=" + gameId);
        } else if (currGameUserIdsList.size() == 1 && !currGameUserIdsList.contains(userId)) {
            currGameUserIdsList.add(userId);
            List<Integer> userIds = gameUserIds.get(gameId);
            Game game = new Game(gameId, userIds.get(0), userIds.get(1));
            manager.getGameMap().put(gameId, game);
            for (Integer id : userIds)
                manager.getUserGameMap().put(id, game);
            resp.sendRedirect("http://localhost:3000");
        } else if (currGameUserIdsList.contains(userId)) {
            resp.sendRedirect("http://localhost:3000");
        } else resp.sendRedirect("home");
    }
}
