package dao.interfaces;

import commons.beans.UserBean;

public interface UserDAOInterface {

    public boolean addUser(UserBean user);

    public UserBean getUser(String username, String password);

    public UserBean getUserById(int id);
}
