package org.bug_bounty.service;

import lombok.Data;
import org.bug_bounty.entity.Role;
import org.bug_bounty.entity.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserService {

    private final Map<String, User> users = new HashMap<>();
    private User loggedinUser;

    public void registerUser(String name, String email, Role role, String password) {
        if (users.containsKey(email)) {
            throw new IllegalArgumentException("User with this email already exists.");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        user.setPassword(password);
        users.put(email, user);
        System.out.println("User registered successfully: " + email);
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public void login(String email, String password) {
        User user = users.get(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password.");
        }
        // User is successfully logged in
        System.out.println("User logged in successfully: " + email);
        this.loggedinUser = user;
    }

    public void logout() {
        if (this.loggedinUser != null) {
            System.out.println("User logged out successfully: " + this.loggedinUser.getEmail());
            this.loggedinUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

}
