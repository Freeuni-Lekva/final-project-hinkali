package servlets.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.beans.UserBean;
import model.GameManager;
import model.game.Game;
import model.game.Player;
import model.game.Table;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/game/state")
public class GameStateServlet extends HttpServlet {

    private static class GameState {
        public Table table;
        public Player player;
        public Player opponent;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Max-Age", "1728000");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("started handling game state fetch request");
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null) {
            resp.getWriter().println("unauthorized request");
            resp.setStatus(403);
            System.out.println("player info request unauthorized");
            return;
        }

        int id = (int) req.getSession().getAttribute(UserBean.USER_ATTR);
        GameManager manager = (GameManager) req.getServletContext().getAttribute(GameManager.GAME_MANAGER_ATTR);
        Game game = manager.getUserGameMap().get(id);
        GameState state = new GameState();
        state.table = game.getTable();
        state.player = game.getPlayerById(id);
        state.opponent = game.getOpponentById(id);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String responseJson = gson.toJson(state);
        resp.getWriter().println(responseJson);
    }
}
