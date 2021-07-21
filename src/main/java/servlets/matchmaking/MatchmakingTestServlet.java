package servlets.matchmaking;

import commons.beans.UserBean;
import model.matchmaking.BasicMatchmakingQueue;
import servlets.ContextListener;
import servlets.search.SearchServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Deprecated
public class MatchmakingTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ContextListener.TESTING){
            if(SearchServlet.handleUnauthorizedCase(req, resp)) return;
        }

        req.getRequestDispatcher("/matchmaking.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!ContextListener.TESTING){
            if(SearchServlet.handleUnauthorizedCase(req, resp)) return;
        }

        BasicMatchmakingQueue queue = (BasicMatchmakingQueue) req.getServletContext().getAttribute(BasicMatchmakingQueue.MATCHMAKING_ATTR);
        String processed = queue.processMatchmakingRequestById((Integer) req.getSession().getAttribute(UserBean.USER_ATTR));
        String jsonResponse = "{ \"response\": \"" + processed + "\"}";

        try{
            int gameId = Integer.parseInt(processed);
            jsonResponse = "{ \"response\": \"success\",\n \"gameId\": " + gameId + "}";
        }catch (NumberFormatException ignored){}

        resp.setStatus(200);
        resp.getWriter().println(jsonResponse);
    }
}
