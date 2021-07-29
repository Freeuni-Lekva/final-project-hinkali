package commons.beans;

import java.sql.Date;
import java.util.Objects;

public class UserBean {
    // put this in setAttribute(key, obj)
    public static final String USER_ATTR = "user";
    public static final int NO_ID = -1;

    private int id;
    private String username;
    private String name, surname;
    private String password;
    private Date birthday;

    public UserBean(int id, String username, String name, String surname, String password, Date birthday){
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.birthday = birthday;
    }

    public UserBean(String username, String name, String surname, String password, Date birthday) {
        this(NO_ID, username, name, surname, password, birthday);
    }

    public int getId() { return id; }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setId(int id) { this.id = id; }

    public void setUsername(String username) { this.username = username; }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) { this.surname = surname; }

    public void setPassword(String password) { this.password = password; }

    public void setBirthday(Date birthday) { this.birthday = birthday; }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return id == userBean.id && Objects.equals(username, userBean.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
