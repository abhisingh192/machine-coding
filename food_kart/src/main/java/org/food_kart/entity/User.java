package org.food_kart.entity;

import lombok.Data;

@Data
public class User {
    private String name;
    private String gender;
    private String phoneNumber;
    private String pincode;
    private String password;
}
