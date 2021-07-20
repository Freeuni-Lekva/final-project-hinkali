package dao.implementation;

import commons.beans.StatsBean;
import commons.beans.UserBean;
import dao.interfaces.Filter;
import dao.interfaces.UserWrapperInterface;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserWrapperDao implements UserWrapperInterface {
    private final InMemoryUserDao userDao;
    private final InMemoryStatsDao statsDao;

    public InMemoryUserWrapperDao(){
        userDao = new InMemoryUserDao();
        statsDao = new InMemoryStatsDao();
    }

    @Override
    public int getRankById(int userId) {
        return statsDao.getRankById(userId);
    }

    @Override
    public boolean changeUser(UserBean user) {
        return userDao.changeUser(user);
    }

    @Override
    public boolean addUser(UserBean user) {
        return userDao.addUser(user);
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
        return userDao.removeUser(id);
    }

    @Override
    public List<UserBean> getUsersWithFilter(Filter f) {
        return userDao.getUsersWithFilter(f);
    }

    @Override
    public void addStatsForNewUser(int userId) {
        statsDao.addStatsForNewUser(userId);
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
    public ArrayList<StatsBean> getStatsWithDescendingPoints() {
        return statsDao.getStatsWithDescendingPoints();
    }

    @Override
    public boolean removeStats(int userId) {
        return statsDao.removeStats(userId);
    }
}
