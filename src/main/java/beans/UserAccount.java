package beans;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {

    private int id;
    private String userName;
    private String password;
    private String phone;
    private List<String> roles;

    public UserAccount() {

    }

    public UserAccount(String userName, String password, String phone) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

    public UserAccount(String userName, String password, String phone,String... roles) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.roles = new ArrayList<String>();
        if (roles != null) {
            for (String role :
                    roles) {
                this.roles.add(role);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
