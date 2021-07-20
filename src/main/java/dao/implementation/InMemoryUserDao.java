package dao.implementation;

import commons.beans.UserBean;
import dao.interfaces.Filter;
import dao.interfaces.UserDAOInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryUserDao implements UserDAOInterface {
    private final List<UserBean> users;

    public InMemoryUserDao(){
        users = new ArrayList<>();
    }
    
    @Override
    public boolean addUser(UserBean user) {
        users.add(user);
        return true;
    }

    @Override
    public UserBean getUser(String username, String password) {
        List<UserBean> results = users.stream().filter(userBean -> userBean.getUsername().equals(username) && userBean.getPassword().equals(password)).collect(Collectors.toList());
        if (results.isEmpty()) return null;
        return results.get(0);
    }

    @Override
    public UserBean getUserById(int id) {
        List<UserBean> results = users.stream().filter(userBean -> userBean.getId() == id).collect(Collectors.toList());
        if (results.isEmpty()) return null;
        return results.get(0);
    }

    @Override
    public boolean removeUser(int id) {
        for (UserBean u :
                users) {
            if (u.getId() == id) {
                users.remove(u);
                return true;
            }
        }
        
        return false;
    }

    // return all
    @Override
    public List<UserBean> getUsersWithFilter(Filter f) {
        return users;
    }

    @Override
    public boolean changeUser(UserBean user) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == user.getId()){
                index = i;
                break;
            }
        }
        if (index == -1) return false;
        users.set(index, user);

        return false;
    }
}
