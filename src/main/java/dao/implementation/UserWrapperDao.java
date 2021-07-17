package dao.implementation;

import commons.beans.StatsBean;
import commons.beans.UserBean;
import dao.interfaces.Filter;
import dao.interfaces.StatsDaoInterface;
import dao.interfaces.UserDAOInterface;
import dao.interfaces.UserWrapperInterface;

import java.util.ArrayList;
import java.util.List;

public class UserWrapperDao implements UserWrapperInterface {
    public static final String USER_WRAPPER_ATTR = UserDAO.USER_DAO_ATTR;

    private final UserDAOInterface userDao;
    private final StatsDaoInterface statsDao;

    public UserWrapperDao(){
        this.userDao = new UserDAO();
        this.statsDao = new StatsDao();
    }

    @Override
    public boolean addUser(UserBean user) {
        if (userDao.addUser(user)){
            statsDao.addStatsForNewUser(user.getId());
            return true;
        }

        return false;
    }

    @Override
    public UserBean getUser(String username, String password) {
        return userDao.getUser(username, password);
    }

    @Override
    public UserBean getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public boolean removeUser(int id) {
        // delete child table first
        if (statsDao.removeStats(id)){
            userDao.removeUser(id);
            return true;
        }

        return false;
    }

    @Override
    public List<UserBean> getUsersWithFilter(Filter f) {
        return userDao.getUsersWithFilter(f);
    }

    @Override
    public boolean changeUser(UserBean user) { return userDao.changeUser(user); }

    @Deprecated
    public void addStatsForNewUser(int userId) {
    }

    @Override
    public boolean setStats(StatsBean stats) {
        return statsDao.setStats(stats);
    }

    @Override
    public StatsBean getStatsById(int userId) {
        return statsDao.getStatsById(userId);
    }

    @Override
    public List<StatsBean> getStatsAll() {
        return statsDao.getStatsAll();
    }

    @Override
    public ArrayList<StatsBean> getStatsWithDescendingPoints() { return statsDao.getStatsWithDescendingPoints();}

    @Deprecated
    public boolean removeStats(int userId) {
        return false;
    }

    @Override
    public int getRankById(int userId) { return statsDao.getRankById(userId);}
}
