package dao.interfaces;

import commons.beans.UserBean;

import java.util.List;

public interface UserDAOInterface {

    public boolean addUser(UserBean user);

    public UserBean getUser(String username, String password);

    public UserBean getUserById(int id);

    public List<UserBean> getUsersWithFilter(Filter f);
}
