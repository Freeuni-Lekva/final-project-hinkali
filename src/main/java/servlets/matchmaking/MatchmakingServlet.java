package servlets.matchmaking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commons.beans.UserBean;
import model.communication.IResponse;
import model.communication.MatchmakingRequest;
import model.matchmaking.BasicMatchmakingQueue;
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

@WebServlet("/matchmaking")
public class MatchmakingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ContextListener.TESTING){
            if(SearchServlet.handleUnauthorizedCase(req, resp)) return;
        }

        req.getRequestDispatcher("/matchmaking.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ContextListener.TESTING){
            if(SearchServlet.handleUnauthorizedCase(req, resp)) return;
        }

        MatchmakingRequest request = proccessJsonBody(req);
        QueryBasedMatchmaking matchmaking = (QueryBasedMatchmaking) req.getServletContext().getAttribute(BasicMatchmakingQueue.MATCHMAKING_ATTR);
        IResponse response = matchmaking.handleMatchmakingRequest(request);

        resp.getWriter().println(response.toJson());
    }

    private MatchmakingRequest proccessJsonBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        char[] charBuffer = new char[128];
        int bytesRead;
        while ( (bytesRead = reader.read(charBuffer)) != -1 ) {
            sb.append(charBuffer, 0, bytesRead);
        }
        String receivedData = sb.toString();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        MatchmakingRequest result = gson.fromJson(receivedData, MatchmakingRequest.class);
        result.setId((Integer) req.getSession().getAttribute(UserBean.USER_ATTR));
        return  result;
    }
}
