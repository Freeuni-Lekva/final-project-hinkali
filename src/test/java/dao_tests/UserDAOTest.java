package dao_tests;

import commons.beans.UserBean;
import dao.implementation.StatsDao;
import dao.implementation.UserDAO;
import dao.implementation.filters.StringFilterInclusive;
import dao.interfaces.Filter;
import dao.interfaces.StatsDaoInterface;
import dao.interfaces.UserDAOInterface;
import model.UserUtility;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private static UserDAOInterface userDao;
    private static UserBean user1;
    private static UserBean user2;
    private static UserBean user3;

    @BeforeClass
    public static void setUp() throws Exception {
        userDao = new UserDAO();
        Date date = new Date(1);
        user1 = new UserBean("daotest1", "daotest1", "daotest1", "daotest1", date);
        user2 = new UserBean("daotest2", "daotest2", "daotest2", "daotest2", date);
        user3 = new UserBean("daotest3", "daotest3", "daotest3", "daotest3", date);
    }

    @Test
    public void testAddUser() {
        assertTrue(userDao.addUser(user1));
        assertTrue(userDao.addUser(user2));
        assertTrue(userDao.addUser(user3));

        assertFalse(userDao.addUser(user1));
        assertFalse(userDao.addUser(user2));
        assertFalse(userDao.addUser(user3));

        userDao.removeUser(user1.getId());
        userDao.removeUser(user2.getId());
        userDao.removeUser(user3.getId());
    }

    @Test
    public void testGetUser() {
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);

        assertEquals(user1, userDao.getUser(user1.getUsername(), user1.getPassword()));
        assertEquals(user2, userDao.getUser(user2.getUsername(), user2.getPassword()));
        assertEquals(user3, userDao.getUser(user3.getUsername(), user3.getPassword()));

        userDao.removeUser(user1.getId());
        userDao.removeUser(user2.getId());
        userDao.removeUser(user3.getId());

        assertNull(userDao.getUser(user1.getUsername(), user1.getPassword()));
        assertNull(userDao.getUser(user2.getUsername(), user2.getPassword()));
        assertNull(userDao.getUser(user3.getUsername(), user3.getPassword()));
    }

    @Test
    public void testGetUserById() {
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);

        assertEquals(user1, userDao.getUserById(user1.getId()));
        assertEquals(user2, userDao.getUserById(user2.getId()));
        assertEquals(user3, userDao.getUserById(user3.getId()));

        userDao.removeUser(user1.getId());
        userDao.removeUser(user2.getId());
        userDao.removeUser(user3.getId());

        assertNull(userDao.getUserById(user1.getId()));
        assertNull(userDao.getUserById(user2.getId()));
        assertNull(userDao.getUserById(user3.getId()));
    }

    @Test
    public void testRemoveUser() {
        assertTrue(userDao.addUser(user1));
        assertTrue(userDao.addUser(user2));
        assertTrue(userDao.addUser(user3));

        assertTrue(userDao.removeUser(user1.getId()));
        assertTrue(userDao.removeUser(user2.getId()));
        assertTrue(userDao.removeUser(user3.getId()));

        assertNull(userDao.getUserById(user1.getId()));
        assertNull(userDao.getUserById(user2.getId()));
        assertNull(userDao.getUserById(user3.getId()));
    }

    @Test
    public void testGetUsersWithFilter() {
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        List<UserBean> userList = new ArrayList<>(Arrays.asList(user1, user2, user3));

        Filter filter1 = new StringFilterInclusive("username", "daotest");
        assertEquals(userList, userDao.getUsersWithFilter(filter1));

        userDao.removeUser(user1.getId());
        userDao.removeUser(user2.getId());
        userDao.removeUser(user3.getId());
    }

    @Test
    public void testChangeUser() {
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);

        UserBean changedUser = new UserBean(user1.getId(), "newusername123", "", "", "newpassword123", user1.getBirthday());
        assertTrue(userDao.changeUser(changedUser));

        changedUser.setName("varsqen");
        assertTrue(userDao.changeUser(changedUser));

        changedUser.setUsername(user2.getUsername());
        assertFalse(userDao.changeUser(changedUser));

        changedUser.setUsername("newusername123");
        changedUser.setPassword("coolpassword");
        assertTrue(userDao.changeUser(changedUser));

//        userDao.removeUser(user1.getId());
        userDao.removeUser(user2.getId());
        userDao.removeUser(user3.getId());
    }
}
