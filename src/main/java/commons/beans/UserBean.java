package commons.beans;

import java.sql.Date;

public class UserBean {
    // put this in setAttribute(key, obj)
    public static final String USER_ATTR = "user";

    private final String username;
    private String name, surname;
    private String password;
    private Date birthday;

    public UserBean(String username, String name, String surname, String password, Date birthday){
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.birthday = birthday;
    }

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
}
