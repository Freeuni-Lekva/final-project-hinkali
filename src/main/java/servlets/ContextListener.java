package servlets;

import dao.implementation.FriendDao;
import dao.implementation.UserWrapperDao;
import dao.interfaces.FriendDaoInterface;
import dao.interfaces.UserWrapperInterface;
import model.matchmaking.BasicMatchmakingQueue;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public static final boolean TESTING = true;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is being Initialized");

        addMatchmakingQueue(sce);

        UserWrapperInterface userWrapperDao = new UserWrapperDao();
        sce.getServletContext().setAttribute(UserWrapperDao.USER_WRAPPER_ATTR, userWrapperDao);

        FriendDaoInterface friendDao = new FriendDao();
        sce.getServletContext().setAttribute(FriendDao.FRIEND_DAO_ATTR, friendDao);
    }

    private void addMatchmakingQueue(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(BasicMatchmakingQueue.MATCHMAKING_ATTR, new BasicMatchmakingQueue());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context is being destroyed");
    }
}
