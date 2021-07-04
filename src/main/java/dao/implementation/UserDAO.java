package dao.implementation;

import commons.beans.UserBean;
import dao.DatabaseUtility;
import dao.interfaces.Filter;
import dao.interfaces.UserDAOInterface;

import javax.xml.registry.infomodel.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInterface {

    public static final String USER_DAO_ATTR = "users";

    private PreparedStatement pstmt;

    public UserDAO() {}

    @Override
    public boolean addUser(UserBean user) {
        Connection conn = DatabaseUtility.getConnection();
        try {
            String sql = "INSERT INTO users (username, name, surname, password, birthday) VALUES (?, ?, ?, ?, ?);";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getSurname());
            pstmt.setString(4, user.getPassword());
            pstmt.setDate(5, user.getBirthday());
            int numInserted = pstmt.executeUpdate();
            if (numInserted != 1)
                return false;
            else {
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                user.setId(rs.getInt(1));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return false;
    }

    @Override
    public UserBean getUser(String username, String password) {
        Connection conn = DatabaseUtility.getConnection();
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("userid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Date birthday = rs.getDate("birthday");
                return new UserBean(id, username, name, surname, password, birthday);
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return null;
    }

    @Override
    public UserBean getUserById(int id) {
        Connection conn = DatabaseUtility.getConnection();
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
                return new UserBean(id, username, name, surname, password, birthday);
            } else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtility.closeConnection(conn);
        }
        return null;
    }

    // TODO placeholder implementation
    @Override
    public List<UserBean> getUsersWithFilter(Filter f) {
        Connection conn = DatabaseUtility.getConnection();
        List<UserBean> result = new ArrayList<>();

        try {
            String statement = "SELECT * FROM users" + f.format();
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

        }finally {
            DatabaseUtility.closeConnection(conn);
        }

        return result;
    }
}
