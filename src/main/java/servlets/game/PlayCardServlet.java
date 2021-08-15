package servlets.game;

import commons.beans.UserBean;
import model.GameManager;
import model.game.modified.GameModified;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/game/playCard")
public class PlayCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Max-Age", "1728000");
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        int cardId = Integer.parseInt(req.getParameter("id"));
        int id = (int) req.getSession().getAttribute(UserBean.USER_ATTR);
        GameManager manager = (GameManager) req.getServletContext().getAttribute(GameManager.GAME_MANAGER_ATTR);
        GameModified game = manager.getUserGameMap().get(id);
        game.playCard(cardId);

        resp.getWriter().println("success");
    }
}
