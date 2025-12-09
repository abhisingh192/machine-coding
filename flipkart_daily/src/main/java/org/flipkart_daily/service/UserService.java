package org.flipkart_daily.service;

import lombok.Data;
import org.flipkart_daily.entity.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserService {

    private final Map<String, User> users = new HashMap<>();

    private static long userIdCounter = 1;

    public void addUser(String name, String address, double walletBalance) {
        if (users.containsKey(name)) {
            throw new IllegalArgumentException("User with name " + name + " already exists.");
        }
        User user = new User();
        user.setId(userIdCounter++);
        user.setName(name);
        user.setAddress(address);
        user.setWalletBalance(walletBalance);

        users.put(name, user);
    }


}
