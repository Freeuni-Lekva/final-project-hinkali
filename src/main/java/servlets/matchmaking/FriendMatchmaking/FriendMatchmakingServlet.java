package servlets.matchmaking.FriendMatchmaking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.beans.UserBean;
import model.communication.FriendMatchmakingRequest;
import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.matchmaking.FriendMatchmaking.FriendMatchmaker;
import model.matchmaking.FriendMatchmaking.MatchmakingMap;
import model.matchmaking.QueryBasedMatchmaking;
import servlets.ContextListener;
import servlets.search.SearchServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/friend-matchmaking")
public class FriendMatchmakingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //id ipovos username-it da dasetos an sheinaxos aqve sadme an rame
        req.getRequestDispatcher("/friend-matchmaking.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        //FriendMatchmakingRequest request = processJsonBody(req);
        FriendMatchmaker mp = (FriendMatchmaker) req.getServletContext().getAttribute(FriendMatchmaker.FRIEND_MATCHMAKING_ATTR);
        IResponse response = mp.handleMatchmakingRequest(request);
        //System.out.println(matchmaking.getQueue().toString());

        resp.getWriter().println(response.toJson());
    }

    private FriendMatchmakingRequest processJsonBody(HttpServletRequest req) throws IOException {
        //json bodyshi ewereba action da name, name-idan saxeli unda amovigo da
        // id shevinaxo action shemidzlia iyos da aseve gamgzavnis id!!
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        char[] charBuffer = new char[128];
        int bytesRead;
        while ((bytesRead = reader.read(charBuffer)) != -1) {
            sb.append(charBuffer, 0, bytesRead);
        }
        String receivedData = sb.toString();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        FriendMatchmakingRequest result = gson.fromJson(receivedData, FriendMatchmakingRequest.class);
        result.setSender((Integer) req.getSession().getAttribute(UserBean.USER_ATTR));
        return result;
    }

    private class data {

        public String action;
        public String name;


    }
}