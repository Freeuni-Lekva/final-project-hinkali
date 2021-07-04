package dao.implementation;

import commons.beans.UserBean;
import dao.DatabaseUtility;
import dao.interfaces.FriendDaoInterface;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class FriendDao implements FriendDaoInterface {

    @Override
    public boolean sendFriendRequest(int senderId, int receiverId) {
        Connection conn = DatabaseUtility.getConnection();
        String sql = "INSERT INTO pending (user_id, friend_id) VALUES (?, ?);";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, receiverId);
            pstmt.executeUpdate();
        }catch (SQLException throwables) {
            return false;
        }finally {
            DatabaseUtility.closeConnection(conn);
        }
        return true;
    }

    @Override
    public boolean acceptFriendRequest(int senderId, int receiverId) {
        Connection conn = DatabaseUtility.getConnection();
        String sql = "INSERT INTO friends(user_id, friend_id) VALUES (?, ?);";
        String sql1 = "DELETE FROM pending WHERE user_id = ? && friend_id = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, receiverId);;
            pstmt.executeUpdate();

            pstmt.setInt(1, receiverId);
            pstmt.setInt(2, senderId);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(sql1);
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, receiverId);
            pstmt.executeUpdate();
        }catch (SQLException throwables) {
            return false;
        }finally {
            DatabaseUtility.closeConnection(conn);
        }

        return true;
    }

    @Override
    public boolean rejectFriendRequest(int senderId, int receiverId) {
        Connection conn = DatabaseUtility.getConnection();
        String sql = "DELETE FROM pending WHERE user_id = ? && friend_id = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, receiverId);
            pstmt.executeUpdate();
        }catch (SQLException throwables){
            return false;
        }finally {
            DatabaseUtility.closeConnection(conn);
        }
        return true;
    }

    @Override
    public boolean unfriend(int senderId, int receiverId) {
        Connection conn = DatabaseUtility.getConnection();
        String sql = "DELETE FROM friends WHERE user_id = ? && friend_id = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, receiverId);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, receiverId);
            pstmt.setInt(2, senderId);
            pstmt.executeUpdate();
        }catch (SQLException throwables){
            return false;
        }finally {
            DatabaseUtility.closeConnection(conn);
        }
        return true;
    }

    @Override
    public Set<Integer> friendIdList(int userId) {
        Set<Integer> friendIds = new HashSet<Integer>();
        Connection conn = DatabaseUtility.getConnection();
        String sql = "SELECT * FROM friends WHERE user_id = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                friendIds.add(rs.getInt("friend_id"));
            }
        }catch (SQLException throwables){
            return null;
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return friendIds;
    }
}
