package dao.mappers;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Random;

/**
 * The type User.
 *
 * @author Chenyi He
 */
@Alias("User")
public class User implements Serializable {

    private int user_id;
    private String user_name;
    private String password;
    private boolean is_vip;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUser(String userName, String password) {
        this.user_name = userName;
        this.password = password;
        this.is_vip = false;

        this.user_id = 100000 + new Random().nextInt(900000);
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return user_name;
    }



    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public boolean isVip() {
        return is_vip;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setVip() {this.is_vip = true;}
}
