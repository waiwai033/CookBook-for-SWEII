package dao.mappers;
import org.apache.ibatis.type.Alias;
import java.io.Serializable;
import java.util.Random;

/**
 * The type User.
 * This class represents a User entity with properties such as user ID, user name,
 * password, and VIP status. It provides methods to get and set these properties.
 * The user ID is generated randomly.
 *
 * @author He Chenyi
 */
@Alias("User")
public class User implements Serializable {

    private int user_id;
    private String user_name;
    private String password;
    private boolean is_vip;

    /**
     * Default constructor for the User class.
     */
    public User(){
    }

    /**
     * Constructor for the User class with parameters.
     * @param user_id    The unique identifier of the user.
     * @param user_name  The name of the user.
     * @param password   The password of the user.
     * @param is_vip     The vip status of the user.
     */
    public User(int user_id, String user_name, String password, boolean is_vip) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.is_vip = is_vip;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * Sets the user properties including user name and password.
     * Also generates a random user ID and sets the VIP status to false.
     *
     * @param userName the user name
     * @param password the password
     */

    public void setUser(String userName, String password) {
        this.user_name = userName;
        this.password = password;
        this.is_vip = false;
        this.user_id = 10000000 + new Random().nextInt(90000000);
    }

    /**
     * Gets the user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return user_name;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Checks if the user is a VIP.
     *
     * @return true if the user is a VIP, false otherwise
     */
    public boolean isVip() {
        return is_vip;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the user as VIP.
     */
    public void setVip() {
        this.is_vip = true;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", is_vip=" + is_vip +
                '}';
    }
}
