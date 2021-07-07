package dao.implementation;

import commons.beans.UserBean;
import dao.DatabaseUtility;
import dao.interfaces.FriendDaoInterface;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class FriendDao implements FriendDaoInterface {
    public static final String FRIEND_DAO_ATTR = "friends";

    @Override
    public boolean sendFriendRequest(int senderId, int receiverId) {
        Connection conn = DatabaseUtility.getConnection();
        String sql = "INSERT INTO pending (sender_id, receiver_id) VALUES (?, ?);";
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
        String sql1 = "DELETE FROM pending WHERE sender_id = ? && receiver_id = ?;";
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
        String sql = "DELETE FROM pending WHERE sender_id = ? && receiver_id = ?;";
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
    public boolean unfriend(int userId, int friendId) {
        Connection conn = DatabaseUtility.getConnection();
        String sql = "DELETE FROM friends WHERE user_id = ? && friend_id = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, friendId);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, friendId);
            pstmt.setInt(2, userId);
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

    @Override
    public Set<Integer> pendingList(int userId) {
        Set<Integer> pendingIds = new HashSet<Integer>();
        Connection conn = DatabaseUtility.getConnection();
        String sql = "SELECT * FROM pending WHERE receiver_id = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                pendingIds.add(rs.getInt("sender_id"));
            }
        }catch (SQLException throwables){
            return null;
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return pendingIds;
    }

    @Override
    public boolean isFriend(int currUserId, int userToCheck) {
        return friendIdList(currUserId).contains(userToCheck);
    }

    @Override
    public boolean isPendingFriend(int currUserId, int userToCheck) {
        return pendingList(currUserId).contains(userToCheck);
    }
}
