package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "როოტროოტ";
    public static final String DATABASE_NAME = "occultexpress";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DATABASE_URL + "/" + DATABASE_NAME
                    + "?verifyServerCertificate=false&useSSL=true", DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}