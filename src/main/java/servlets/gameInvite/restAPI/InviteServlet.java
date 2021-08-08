package servlets.gameInvite.restAPI;

import com.google.gson.Gson;
import commons.beans.UserBean;
import dao.implementation.UserWrapperDao;
import dao.interfaces.UserWrapperInterface;
import model.gameInviteHandler.IGameInviteManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gameInvite/invite")
// params
// 0: ?username=
public class InviteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("InviteServlet: handling request");
        Integer userId = (Integer) req.getSession().getAttribute(UserBean.USER_ATTR);
        if (userId == null){
            System.err.println("Servlet: no user authenticated");
            return;
        }
        UserWrapperInterface dao = (UserWrapperInterface) req.getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
        UserBean user = dao.getUserById(userId);
        IGameInviteManager inviteManager = (IGameInviteManager) req.getServletContext().getAttribute(IGameInviteManager.INVITE_MANAGER_ATTR);
        String receiverUsername = req.getParameter("username");
        if (receiverUsername == null || receiverUsername.isEmpty()){
            System.err.println("InviteServlet: no parameter received");
            String errorStr = "error";
            Gson gson = new Gson();
            resp.setStatus(404);
            resp.getWriter().println(gson.toJson(errorStr));
            return;
        }
        UserBean receiver = dao.getUserByUsername(receiverUsername);
        if (receiver == null){
            System.err.println("InviteServlet: no such user found");
            String errorStr = "error";
            Gson gson = new Gson();
            resp.setStatus(404);
            resp.getWriter().println(gson.toJson(errorStr));
            return;
        }

        inviteManager.handleInviteRequest(user, receiver);
        Gson gson = new Gson();
        resp.getWriter().println(gson.toJson("waiting"));
    }
}
