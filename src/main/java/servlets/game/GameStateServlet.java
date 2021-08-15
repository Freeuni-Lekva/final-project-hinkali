package servlets.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.beans.UserBean;
import model.GameManager;
import model.game.modified.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/game/state")
public class GameStateServlet extends HttpServlet {

    private static class GameStateWrapper {
        public PlayerDTO player;
        public PlayerDTO opponent;
    }

    private static class PlayerDTO {
        public final boolean isPlayerTurn;
        private final boolean isPlayer;
        private final int livesLeft;
        private final HalfBoard board;
        private final List<Card> hand;
        private final Deck deck;
        private final int total;

        public PlayerDTO(Player p, boolean isPlayerTurn, boolean isPlayer) {
            this.isPlayerTurn = isPlayerTurn;
            this.livesLeft = p.getLivesLeft();
            this.board = p.getBoard();
            this.hand = p.getHand();
            this.deck = p.getDeck();
            this.isPlayer = isPlayer;
            this.total = p.getBoard().getHalfBoardSum();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Max-Age", "1728000");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null) {
            resp.getWriter().println("unauthorized request");
            resp.setStatus(403);
            System.out.println("player info request unauthorized");
            return;
        }

        int id = (int) req.getSession().getAttribute(UserBean.USER_ATTR);
        GameManager manager = (GameManager) req.getServletContext().getAttribute(GameManager.GAME_MANAGER_ATTR);
        GameModified game = manager.getUserGameMap().get(id);
        GameStateWrapper info = getWrapper(game, id);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String responseJson = gson.toJson(info);
        resp.getWriter().println(responseJson);
    }

    private GameStateWrapper getWrapper(GameModified game, int userId) {
        boolean isPlayerTurn = game.getTurnOfPlayer() == userId;
        Player playerEntity = game.getPlayerById(userId);
        Player opponentEntity = game.getOpponentByPlayerId(userId);
        PlayerDTO playerDto = new PlayerDTO(playerEntity, isPlayerTurn, true);
        PlayerDTO opponentDto = new PlayerDTO(opponentEntity, !isPlayerTurn, false);
        GameStateWrapper result = new GameStateWrapper();
        result.player = playerDto;
        result.opponent = opponentDto;
        return result;
    }
}
