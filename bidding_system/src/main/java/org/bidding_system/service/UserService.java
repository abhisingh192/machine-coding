package org.bidding_system.service;

import lombok.Data;
import org.bidding_system.entity.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserService {

    private final Map<Long, User> userRepository = new HashMap<>();
    private final Map<String, Long> userNameToIdMap = new HashMap<>();
    private static long userIdCounter = 1;

    public void addUser(String name, long superCoins) {
        if (userNameToIdMap.containsKey(name)) {
            System.out.println("User with name " + name + " already exists.");
            return;
        }
        User user = new User();
        user.setId(userIdCounter++);
        user.setName(name);
        if (superCoins < 0) {
            System.out.println("SuperCoins cannot be negative");
            return;
        }
        user.setSuperCoins(superCoins);
        userRepository.put(user.getId(), user);
        userNameToIdMap.put(name, user.getId());
    }
}
