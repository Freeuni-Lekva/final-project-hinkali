package servlets.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.beans.UserBean;
import dao.implementation.UserWrapperDao;
import dao.interfaces.UserWrapperInterface;

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
        System.out.println("handling player info fetch request");
        if (req.getSession().getAttribute(UserBean.USER_ATTR) == null) {
            resp.sendRedirect("/");
            return;
        }
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Max-Age", "1728000");

        int id = (int) req.getSession().getAttribute(UserBean.USER_ATTR);
        UserWrapperInterface dao = (UserWrapperInterface) req.getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
        UserBean player = dao.getUserById(id);
        UserBean opponent = new UserBean(0, "opponentName", "opponentSurname", "opponentUsername", "", null);
        ResponseWrapper response = new ResponseWrapper(player, opponent);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String responseJson = gson.toJson(response);
        resp.getWriter().println(responseJson);
    }
}
