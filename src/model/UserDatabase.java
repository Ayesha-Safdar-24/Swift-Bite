package model;

import java.util.ArrayList;

public class UserDatabase {

    private static ArrayList<User> users = new ArrayList<>();

    static {
        users.add(new User("admin", "03000000000", "123"));
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static boolean validateLogin(String username, String password) {

        for (User user : users) {

            if (user.getUsername().equals(username)
                    &&
                user.getPassword().equals(password)) {

                // for saves loggedin user
                SessionManager.setLoggedInUser(
                        user.getUsername(),
                        user.getPhone()
                );

                return true;
            }
        }

        return false;
    }
}