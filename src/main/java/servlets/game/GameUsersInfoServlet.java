package servlets.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.beans.UserBean;
import dao.implementation.UserWrapperDao;
import dao.interfaces.UserWrapperInterface;
import model.GameManager;
import model.game.modified.GameModified;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/game/info")
public class GameUsersInfoServlet extends HttpServlet {

    private static class ResponseWrapper {
        final UserBean player;
        final UserBean opponent;

        public ResponseWrapper(UserBean player, UserBean opponent) {
            this.player = player;
            this.opponent = opponent;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Max-Age", "1728000");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("started handling player info request");
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null) {
            resp.getWriter().println("unauthorized request");
            resp.setStatus(403);
            System.out.println("player info request unauthorized");
            return;
        }
        // player
        int id = (int) req.getSession().getAttribute(UserBean.USER_ATTR);
        System.out.println("userId: " + id);
        UserWrapperInterface dao = (UserWrapperInterface) req.getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
        UserBean player = dao.getUserById(id);
        player.setPassword("");
        // opponent
        GameManager manager = (GameManager) req.getServletContext().getAttribute(GameManager.GAME_MANAGER_ATTR);
        GameModified game = manager.getUserGameMap().get(id);
        int gameId = game.getGameId();
        Map<Integer, List<Integer>> gameUserIds = manager.getGameUserIds();
        List<Integer> integers = gameUserIds.get(gameId);
        List<Integer> collect = integers.stream().filter(i -> i != id).collect(Collectors.toList());
        UserBean opponent = dao.getUserById(collect.get(0));
        opponent.setPassword("");

        //response building
        ResponseWrapper response = new ResponseWrapper(player, opponent);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String responseJson = gson.toJson(response);
        System.out.println("sending response to client");
        resp.getWriter().println(responseJson);
    }
}
