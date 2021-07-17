package dao.implementation;

import commons.beans.StatsBean;
import dao.DatabaseUtility;
import dao.interfaces.StatsDaoInterface;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatsDao implements StatsDaoInterface {
    public static final String STATS_DAO_ATTR = "stats";

    @Override
    public void addStatsForNewUser(int userId) {
        Connection conn = DatabaseUtility.getConnection();
        String update = "INSERT INTO stats (userId) VALUES (?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userId);
            int numInserted = preparedStatement.executeUpdate();
        } catch (SQLException ignored) {}
        finally {
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }

    @Override
    public boolean setStats(StatsBean stats) {
        boolean result = true;
        Connection conn = DatabaseUtility.getConnection();
        String update = "UPDATE stats SET games_played = (?), wins = (?), draws = (?), losses = (?), points = (?) WHERE userid = (?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(update);
            preparedStatement.setInt(1, stats.getGamesPlayed());
            preparedStatement.setInt(2, stats.getWins());
            preparedStatement.setInt(3, stats.getDraws());
            preparedStatement.setInt(4, stats.getLosses());
            preparedStatement.setInt(5, stats.getPoints());
            preparedStatement.setInt(6, stats.getUserid());
            int numUpdated = preparedStatement.executeUpdate();
            if (numUpdated != 1) result = false;
        } catch (SQLException throwables) {
            result = false;
        }finally {
            try {
                conn.close();
            } catch (SQLException ignored) {}
        }

        return result;
    }

    @Override
    public StatsBean getStatsById(int userId) {
        StatsBean result = null;
        Connection conn = DatabaseUtility.getConnection();
        String query = "SELECT * FROM stats WHERE userid = (?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                result = new StatsBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException ignored) {}
        }

        return result;
    }

    @Override
    public List<StatsBean> getStatsAll() {
        List<StatsBean> result = new ArrayList<>();
        Connection conn = DatabaseUtility.getConnection();
        String query = "SELECT * FROM stats";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result.add( new StatsBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException ignored) {}
        }

        return result;
    }


    @Override
    public ArrayList<StatsBean> getStatsWithDescendingPoints() {
        ArrayList<StatsBean> result = new ArrayList<>();
        Connection conn = DatabaseUtility.getConnection();
        String query = "SELECT * FROM stats ORDER BY points DESC LIMIT 10 ";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result.add( new StatsBean(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean removeStats(int userId) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = true;
        String remove = "DELETE FROM stats WHERE userid = (?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(remove);
            preparedStatement.setInt(1, userId);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted == 0) result = false;
        } catch (SQLException ignored) {}
        finally {
            try {
                conn.close();
            } catch (SQLException ignored) {}
        }

        return result;
    }
}
