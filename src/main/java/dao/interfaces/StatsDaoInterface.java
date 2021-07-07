package dao.interfaces;

import commons.beans.StatsBean;

import java.sql.Connection;
import java.util.List;

public interface StatsDaoInterface {
    void addStatsForNewUser(int userId);

    boolean setStats(StatsBean stats);

    StatsBean getStatsById(int userId);

    List<StatsBean> getStatsAll();

    boolean removeStats(int userId);
}
