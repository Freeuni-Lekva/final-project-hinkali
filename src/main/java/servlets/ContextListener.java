package servlets;

import dao.implementation.FriendDao;
import dao.implementation.InMemoryFriendDao;
import dao.implementation.InMemoryUserWrapperDao;
import dao.implementation.UserWrapperDao;
import dao.interfaces.FriendDaoInterface;
import dao.interfaces.UserWrapperInterface;
import model.matchmaking.BasicMatchmakingQueue;
import model.matchmaking.QueryBasedMatchmaking;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public static final boolean TESTING = true;
    public static final boolean IN_MEMORY = false;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is being initialized");

        addMatchmakingQueue(sce);
        if (IN_MEMORY){
            setupInMemoryDaos(sce);
            return;
        }

        UserWrapperInterface userWrapperDao = new UserWrapperDao();
        sce.getServletContext().setAttribute(UserWrapperDao.USER_WRAPPER_ATTR, userWrapperDao);


        FriendDaoInterface friendDao = new FriendDao();
        sce.getServletContext().setAttribute(FriendDao.FRIEND_DAO_ATTR, friendDao);
    }

    private void setupInMemoryDaos(ServletContextEvent sce) {
        UserWrapperInterface wrapperInterface = new InMemoryUserWrapperDao();
        FriendDaoInterface friendDaoInterface = new InMemoryFriendDao();
        sce.getServletContext().setAttribute(UserWrapperDao.USER_WRAPPER_ATTR,  wrapperInterface);
        sce.getServletContext().setAttribute(FriendDao.FRIEND_DAO_ATTR, friendDaoInterface);
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