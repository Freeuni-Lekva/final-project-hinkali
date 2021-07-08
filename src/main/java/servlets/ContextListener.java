package servlets;

import dao.implementation.FriendDao;
import dao.implementation.UserWrapperDao;
import dao.interfaces.FriendDaoInterface;
import dao.interfaces.UserWrapperInterface;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    public static final boolean TESTING = true;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is being Initialized");

        UserWrapperInterface userWrapperDao = new UserWrapperDao();
        sce.getServletContext().setAttribute(UserWrapperDao.USER_WRAPPER_ATTR, userWrapperDao);

        FriendDaoInterface friendDao = new FriendDao();
        sce.getServletContext().setAttribute(FriendDao.FRIEND_DAO_ATTR, friendDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context is being destroyed");
    }
}
