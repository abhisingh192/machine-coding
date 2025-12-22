package org.clear_fit.service;

import lombok.Data;
import org.clear_fit.entity.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserService {

    private static int userIdCounter = 1;
    private final Map<Integer, User> userRepo = new HashMap<>();

    public int registerUser(String name, String email, String phoneNumber, String password) {
        // Logic to register user
        int userId = userIdCounter++;
        User user = new User();
        user.setUserId(userId);
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        userRepo.put(userId, user);

        System.out.println("User registered successfully with ID: " + userId);
        return userId;
    }
}
