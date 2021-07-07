package servlets;

import dao.implementation.FriendDao;
import dao.implementation.StatsDao;
import dao.implementation.UserDAO;
import dao.interfaces.FriendDaoInterface;
import dao.interfaces.StatsDaoInterface;
import dao.interfaces.UserDAOInterface;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context is being Initialized");
        StatsDaoInterface statsDao = new StatsDao();
        sce.getServletContext().setAttribute(StatsDao.STATS_DAO_ATTR, statsDao);

        UserDAOInterface userDao = new UserDAO(statsDao);
        sce.getServletContext().setAttribute(UserDAO.USER_DAO_ATTR, userDao);

        FriendDaoInterface friendDao = new FriendDao();
        sce.getServletContext().setAttribute(FriendDao.FRIEND_DAO_ATTR, friendDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context is being destroyed");
    }
}
