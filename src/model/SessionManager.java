package model;

public class SessionManager {

    private static String loggedInUsername = "";
    private static String loggedInPhone = "";

    public static void setLoggedInUser(String username, String phone) {
        loggedInUsername = username;
        loggedInPhone = phone;
    }

    public static String getUsername() {
        return loggedInUsername;
    }

    public static String getPhone() {
        return loggedInPhone;
    }

    public static void logout() {
        loggedInUsername = "";
        loggedInPhone = "";
    }
}