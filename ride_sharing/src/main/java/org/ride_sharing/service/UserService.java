package org.ride_sharing.service;

import lombok.Data;
import org.ride_sharing.entity.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserService {
    
    private final Map<String, User> userRepo = new HashMap<>(); //username -> user map
    private static int userIdCounter = 1;
    
    public void addUser(String name, String gender, int age) {
        if (userRepo.containsKey(name)) {
            System.out.println("user already exists");
            return;
        }
        User user = new User();
        user.setId(userIdCounter++);
        user.setAge(age);
        user.setName(name);
        user.setGender(gender);
        
        userRepo.put(name, user);
        
    }
}
