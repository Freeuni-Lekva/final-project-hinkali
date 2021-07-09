package dao_tests;

import commons.beans.UserBean;
import dao.implementation.UserDAO;
import dao.implementation.UserWrapperDao;
import dao.interfaces.UserWrapperInterface;
import model.UserUtility;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class WrapperDaoTest {
    private static UserWrapperInterface wrapperDao;
    private static UserBean user1;
    private static UserBean user2;
    private static UserBean user3;

    @BeforeClass
    public static void setUp() throws Exception {
        wrapperDao = new UserWrapperDao();
        Date date = new Date(1);
        user1 = new UserBean(150,"daotest1", "daotest1", "daotest1", UserUtility.generateHash("daotest1"), date);
        user2 = new UserBean(300,"daotest2", "daotest2", "daotest2", UserUtility.generateHash("daotest2"), date);
        user3 = new UserBean(450,"daotest3", "daotest3", "daotest3", UserUtility.generateHash("daotest3"), date);
    }

    @Test
    public void testStatCreationOnAccCreation1(){
        assertTrue(wrapperDao.addUser(user1));
        assertNotNull(wrapperDao.getStatsById(user1.getId()));
        assertEquals(user1.getId(), wrapperDao.getStatsById(user1.getId()).getUserid());
    }

    @Test
    public void testStatCreationOnAccCreation2(){
        assertTrue(wrapperDao.addUser(user2));
        assertNotNull(wrapperDao.getStatsById(user2.getId()));
        assertEquals(user2.getId(), wrapperDao.getStatsById(user2.getId()).getUserid());
    }

    @Test
    public void testStatDeletionOnAccDeletion1(){
        assertTrue(wrapperDao.removeUser(user1.getId()));
        assertNull(wrapperDao.getUserById(user1.getId()));
    }

    @Test
    public void testStatDeletionOnAccDeletion2(){
        assertTrue(wrapperDao.removeUser(user2.getId()));
        assertNull(wrapperDao.getUserById(user2.getId()));
    }

    @Test
    public void testCheckFailedRemoval(){
        assertFalse(wrapperDao.removeUser(user3.getId()));
    }

}
