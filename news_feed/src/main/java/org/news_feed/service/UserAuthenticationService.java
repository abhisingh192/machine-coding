package org.news_feed.service;

import lombok.Data;
import org.news_feed.entity.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class UserAuthenticationService {

    /*
    this class will implement signUp, login, logout functionalities for users

     */

    private final Map<String, User> users = new HashMap<>();
    private final Set<String> loggedInUsers = new HashSet<>();

    public void signUp(String username, String password, String name) {
        // Implementation for user sign-up
        if (users.containsKey(username)) {
            System.out.println("Username already exists");
        }
        else {
            User newUser = new User(username, password, name);
            users.put(username, newUser);
            System.out.println("User signed up successfully");
        }

    }

    public void login(String username, String password) {
        // Implementation for user login
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUsers.add(username);
            System.out.println("User logged in successfully");
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public void logout(String username) {
        // Implementation for user logout
        if (loggedInUsers.remove(username)) {
            System.out.println("User logged out successfully");
        } else {
            System.out.println("User is not logged in");
        }
    }
}
