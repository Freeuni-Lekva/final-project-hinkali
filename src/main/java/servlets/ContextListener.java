package servlets;

import dao.implementation.FriendDao;

import dao.implementation.StatsDao;
import dao.implementation.UserDAO;
import dao.implementation.UserWrapperDao;
import dao.interfaces.FriendDaoInterface;
import dao.interfaces.StatsDaoInterface;
import dao.interfaces.UserDAOInterface;
import dao.interfaces.UserWrapperInterface;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

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
