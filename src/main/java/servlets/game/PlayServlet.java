package servlets.game;

import commons.beans.UserBean;
import dao.implementation.DeckDAO;
import model.GameManager;
import model.game.Game;
import model.game.modified.Deck;
import model.game.modified.GameModified;
import model.game.modified.Player;

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
            Deck p1Deck = new Deck(userIds.get(0));
            Deck p2Deck = new Deck(userIds.get(0));
            Player p1 = new Player(userIds.get(0), p1Deck);
            Player p2 = new Player(userIds.get(1), p2Deck);
            GameModified game = new GameModified(gameId, p1, p2);
            manager.getGameMap().put(gameId, game);
            for (Integer id : userIds)
                manager.getUserGameMap().put(id, game);
            resp.sendRedirect("http://localhost:3000");
        } else if (currGameUserIdsList.contains(userId)) {
            resp.sendRedirect("http://localhost:3000");
        } else resp.sendRedirect("home");
    }

//    private Deck getDeckFromDao(Integer id, HttpServletRequest req) {
//        DeckDAO dao = (DeckDAO) req.getServletContext().getAttribute(DeckDAO.DECK_DAO_ATTR);
//        int userDeckId = dao.getUserDeckId(id);
//        model.Deck deck = dao.getDeck(userDeckId);
//        return new Deck(deck);
//    }
}
