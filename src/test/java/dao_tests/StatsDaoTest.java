package dao_tests;

import commons.beans.StatsBean;
import commons.beans.UserBean;
import dao.implementation.StatsDao;
import dao.implementation.UserDAO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StatsDaoTest {
    private static UserBean usr1;
    private static UserBean usr2;

    private static StatsDao statsDao;
    private static UserDAO userDAO;

    @BeforeClass
    public static void setUp(){
        statsDao = new StatsDao();
        userDAO = new UserDAO(statsDao);
        usr1 = new UserBean(199,"testUserA", "", "", "password", null);
        usr2 = new UserBean(99,"testUserB", "", "", "password", null);
    }

    @Test
    public void testGetStatsAll(){
        List<StatsBean> statsBeanList = statsDao.getStatsAll();
        assertFalse(statsBeanList.isEmpty());
        assertEquals(3, statsBeanList.size());
        assertEquals(0, statsBeanList.get(0).getPoints());
    }

    @Test
    public void testStatsOnUserCreation(){
        userDAO.addUser(usr1);
        int expected = 0;
        StatsBean found = statsDao.getStatsById(usr1.getId());
        assertNotNull(found);
        assertEquals(expected, found.getGamesPlayed());
        assertEquals(expected, found.getPoints());
        assertEquals(usr1.getId(), found.getUserid());

        userDAO.addUser(usr2);
        found = statsDao.getStatsById(usr2.getId());
        assertNotNull(found);
        assertEquals(expected, found.getGamesPlayed());
        assertEquals(expected, found.getPoints());
        assertEquals(usr2.getId(), found.getUserid());
    }

    @Test
    public void testStatsOnUserDeletion(){
        userDAO.removeUser(usr1.getId());
        StatsBean found = statsDao.getStatsById(usr1.getId());
        assertNull(found);

        userDAO.removeUser(usr2.getId());
        found = statsDao.getStatsById(usr1.getId());
        assertNull(found);
    }
}
