package config;

/**
 * The SessionManager class manages the current user's session.
 * It provides methods to set and get the current username.
 *
 * @author He Chenyi
 */
public class SessionManager {

    // Stores the current username
    private static String currentUserName;

    /**
     * Sets the current username.
     *
     * @param userName the username to set
     */
    public static void setCurrentUserName(String userName) {
        currentUserName = userName;
    }

    /**
     * Gets the current username.
     *
     * @return the current username
     */
    public static String getCurrentUserName() {
        return currentUserName;
    }
}
