package org.food_kart.service;

import lombok.Data;
import org.food_kart.entity.User;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserService {
    
    private final Map<String, User> userRepo = new HashMap<>(); // phone -> user
    private String loggedInUser; // only one user can be logged in at a time
    
    public void registerUser(String name, String gender, String phoneNumber, String pincode, String password) {
        
        if(userRepo.containsKey(phoneNumber)) {
            System.out.println("phone number already connected to a user, try with different phone number");
            return;
        }
        
        User user = new User();
        user.setGender(gender);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPincode(pincode);
        user.setPassword(password);
        
        userRepo.put(phoneNumber, user);

        System.out.println("User "+name+ " registered successfully");
    }
    
    public void loginUser(String phoneNumber, String password) {
        if (!userRepo.containsKey(phoneNumber)) {
            System.out.println("invalid phone number");
            return;
        }
        User user = userRepo.get(phoneNumber);
        
        if (!user.getPassword().equals(password)) {
            System.out.println("invalid password, please try again");
            return;
        }
        System.out.println("User "+user.getName()+ " logged in successfully");
        loggedInUser = user.getPhoneNumber();
    }
}
