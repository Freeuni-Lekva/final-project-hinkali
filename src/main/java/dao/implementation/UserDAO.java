package dao.implementation;

import commons.beans.UserBean;
import dao.DatabaseUtility;
import dao.interfaces.Filter;
import dao.interfaces.StatsDaoInterface;
import dao.interfaces.UserDAOInterface;
import model.UserUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInterface {

    public static final String USER_DAO_ATTR = "users";

    private PreparedStatement pstmt;

    @Override
    public boolean addUser(UserBean user) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "INSERT INTO users (username, name, surname, password, birthday) VALUES (?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, UserUtility.capitalizeFirstLetter(user.getName()));
            pstmt.setString(3, UserUtility.capitalizeFirstLetter(user.getSurname()));
            pstmt.setString(4, UserUtility.generateHash(user.getPassword()));
            pstmt.setDate(5, user.getBirthday());
            int numInserted = pstmt.executeUpdate();
            if (numInserted == 1) {
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                int userid = rs.getInt(1);
                user.setId(userid);
                result = true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public UserBean getUser(String username, String password) {
        Connection conn = DatabaseUtility.getConnection();
        UserBean result = null;
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, UserUtility.generateHash(password));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Date birthday = rs.getDate("birthday");
                result = new UserBean(id, username, name, surname, password, birthday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public UserBean getUserById(int id) {
        Connection conn = DatabaseUtility.getConnection();
        UserBean result = null;
        try {
            String sql = "SELECT * FROM users WHERE userid = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String password = rs.getString("password");
                Date birthday = rs.getDate("birthday");
                result = new UserBean(id, username, name, surname, password, birthday);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    @Override
    public boolean removeUser(int id) {
        Connection conn = DatabaseUtility.getConnection();
        boolean result = false;
        try {
            String sql = "DELETE FROM users WHERE userid = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int numDeleted = pstmt.executeUpdate();
            if (numDeleted == 1){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }

    public List<UserBean> getUsersWithFilter(Filter f) {
        Connection conn = DatabaseUtility.getConnection();
        List<UserBean> result = new ArrayList<>();
        try {
            String query = f.format();
            if (!query.isEmpty()) query = " WHERE " + query;
            String statement = "SELECT * FROM users " + query;
            pstmt = conn.prepareStatement(statement);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                int userid = rs.getInt("userid");
                String username = rs.getString("username");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                //String password = rs.getString("password");
                //Date birthday = rs.getDate("birthday");
                UserBean newUserBean = new UserBean(userid, username, name, surname, "", null);
                result.add(newUserBean);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DatabaseUtility.closeConnection(conn);
        }
        return result;
    }
}
