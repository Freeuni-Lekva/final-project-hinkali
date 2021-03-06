package servlets;

import dao.implementation.DeckWrapperDAO;
import dao.implementation.FriendDao;
import dao.implementation.StatsDao;
import dao.implementation.UserWrapperDao;
import dao.interfaces.DeckWrapperInterface;
import dao.interfaces.FriendDaoInterface;
import dao.interfaces.UserWrapperInterface;
import model.GameManager;
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

        DeckWrapperInterface deckWrapperDao = new DeckWrapperDAO();
        sce.getServletContext().setAttribute(DeckWrapperDAO.DECK_WRAPPER_ATTR, deckWrapperDao);

        GameManager manager = new GameManager();
        sce.getServletContext().setAttribute(GameManager.GAME_MANAGER_ATTR, manager);

        StatsDao statsDao = new StatsDao();
        sce.getServletContext().setAttribute(StatsDao.STATS_DAO_ATTR, statsDao);
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