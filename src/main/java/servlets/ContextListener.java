package servlets;

import dao.implementation.FriendDao;
import dao.implementation.UserWrapperDao;
import dao.interfaces.FriendDaoInterface;
import dao.interfaces.UserWrapperInterface;
import model.gameInviteHandler.GameInviteManager;
import model.gameInviteHandler.IGameInviteManager;
import model.matchmaking.BasicMatchmakingQueue;
import model.matchmaking.QueryBasedMatchmaking;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public static final boolean TESTING = true;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is being initialized");

        addMatchmakingQueue(sce);
        addInviteManager(sce);

        UserWrapperInterface userWrapperDao = new UserWrapperDao();
        sce.getServletContext().setAttribute(UserWrapperDao.USER_WRAPPER_ATTR, userWrapperDao);


        FriendDaoInterface friendDao = new FriendDao();
        sce.getServletContext().setAttribute(FriendDao.FRIEND_DAO_ATTR, friendDao);
    }

    private void addInviteManager(ServletContextEvent sce) {
        IGameInviteManager inviteManager = new GameInviteManager();
        sce.getServletContext().setAttribute(IGameInviteManager.INVITE_MANAGER_ATTR, inviteManager);
    }

    private void addMatchmakingQueue(ServletContextEvent sce) {
        //sce.getServletContext().setAttribute(BasicMatchmakingQueue.MATCHMAKING_ATTR, new BasicMatchmakingQueue());
        sce.getServletContext().setAttribute(BasicMatchmakingQueue.MATCHMAKING_ATTR, new QueryBasedMatchmaking());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context is being destroyed");
    }
}