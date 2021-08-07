package servlets.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.beans.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/game/info")
public class GameUsersInfoServlet extends HttpServlet {

    private class ResponseWrapper {
        final UserBean player;
        final UserBean opponent;

        public ResponseWrapper(UserBean player, UserBean opponent) {
            this.player = player;
            this.opponent = opponent;
        }
    }

    // TODO update mock implementation after others are finished
    // params: none
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UserBean player = new UserBean(0, "playerName", "playerSurname", "playerUsername", "", null);
//        UserBean opponent = new UserBean(0, "opponentName", "opponentSurname", "opponentUsername", "", null);
//        req.getSession().setAttribute(UserBean.USER_ATTR, 0);

        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.getWriter().println("hello");

//        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null){
//            resp.sendRedirect("/");
//            return;
//        }

//        ResponseWrapper response = new ResponseWrapper(player, opponent);
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.create();
//        String responseJson = gson.toJson(response);
//        resp.getWriter().println(responseJson);
    }
}
