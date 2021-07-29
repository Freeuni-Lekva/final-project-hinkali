package dao.interfaces;

import commons.beans.UserBean;
import java.util.List;

public interface UserDAOInterface {

    boolean addUser(UserBean user);

    UserBean getUser(String username, String password);

    UserBean getUserById(int id);

    UserBean getUserByUsername(String username);

    boolean removeUser(int id);

    List<UserBean> getUsersWithFilter(Filter f);

    boolean changeUser(UserBean user);
}
