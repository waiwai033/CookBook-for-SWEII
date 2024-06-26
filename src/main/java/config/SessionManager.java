package config;

public class SessionManager {
    private static String currentUserName;

    public static void setCurrentUserName(String userName) {
        currentUserName = userName;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }
}
