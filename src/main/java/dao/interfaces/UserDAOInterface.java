package dao.interfaces;

import commons.beans.UserBean;

public interface UserDAOInterface {

    boolean addUser(UserBean user);

    UserBean getUser(String username, String password);

    UserBean getUserById(int id);

    boolean removeUser(int id);
}
