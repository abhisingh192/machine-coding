package org.lib_mgmt_system.service;

import lombok.Data;
import org.lib_mgmt_system.entity.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserService {

    private final Map<Long, User> userDatabase = new HashMap<>();
    private static Long userIdCounter = 1L;

    public void registerUser(String username) {
        User user = new User();
        user.setId(userIdCounter++);
        user.setUsername(username);
        userDatabase.put(user.getId(), user);
        System.out.println("User registered: " + username);
    }

}
