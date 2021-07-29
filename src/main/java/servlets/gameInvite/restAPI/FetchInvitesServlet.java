package servlets.gameInvite.restAPI;

import commons.beans.UserBean;
import dao.implementation.UserWrapperDao;
import dao.interfaces.UserWrapperInterface;
import model.communication.IResponse;
import model.communication.gameInviteComms.InvitesListResponse;
import model.gameInviteHandler.IGameInviteManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/gameInvite/fetchInvites")
public class FetchInvitesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FetchInviteServlet: handling request");
        Integer userId = (Integer) req.getSession().getAttribute(UserBean.USER_ATTR);
        if (userId == null){
            System.err.println("Servlet: no user authenticated");
            return;
        }
        UserWrapperInterface dao = (UserWrapperInterface) req.getServletContext().getAttribute(UserWrapperDao.USER_WRAPPER_ATTR);
        UserBean user = dao.getUserById(userId);
        IGameInviteManager inviteManager = (IGameInviteManager) req.getServletContext().getAttribute(IGameInviteManager.INVITE_MANAGER_ATTR);
        List<UserBean> allInvites = inviteManager.getAllInvites(user);
        IResponse response = new InvitesListResponse(allInvites);
        resp.getWriter().println(response.toJson());
    }
}
