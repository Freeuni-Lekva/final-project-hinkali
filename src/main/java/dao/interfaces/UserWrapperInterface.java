package dao.interfaces;

import commons.beans.StatsBean;
import commons.beans.UserBean;

import java.util.ArrayList;
import java.util.List;

public interface UserWrapperInterface extends UserDAOInterface, StatsDaoInterface{
    boolean addUser(UserBean user);

    UserBean getUser(String username, String password);

    UserBean getUserById(int id);

    boolean removeUser(int id);

    public List<UserBean> getUsersWithFilter(Filter f);

    void addStatsForNewUser(int userId);

    boolean setStats(StatsBean stats);

    StatsBean getStatsById(int userId);

    List<StatsBean> getStatsAll();

    ArrayList<StatsBean> getStatsWithDescendingPoints();

    boolean removeStats(int userId);
}
