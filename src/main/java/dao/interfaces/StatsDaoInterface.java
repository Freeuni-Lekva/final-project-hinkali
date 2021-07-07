package dao.interfaces;

import commons.beans.StatsBean;

import java.sql.Connection;
import java.util.List;

public interface StatsDaoInterface {
    void addStatsForNewUser(int userId, Connection conn);

    boolean setStats(StatsBean stats);

    StatsBean getStatsById(int userId);

    List<StatsBean> getStatsAll();

    void removeStats(int userId, Connection conn);
}
