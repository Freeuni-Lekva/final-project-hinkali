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

    @BeforeClass
    public static void setUp(){
        statsDao = new StatsDao();
        usr1 = new UserBean(199,"testUserA", "", "", "password", null);
        usr2 = new UserBean(99,"testUserB", "", "", "password", null);
    }

    @Test
    public void testGetStatsById(){
        StatsBean statsBean = statsDao.getStatsById(1);
        assertNotNull(statsBean);
    }

    @Test
    public void testGetStatsAll(){
        List<StatsBean> statsBeanList = statsDao.getStatsAll();
        assertFalse(statsBeanList.isEmpty());
        assertEquals(0, statsBeanList.get(0).getPoints());
    }
}
