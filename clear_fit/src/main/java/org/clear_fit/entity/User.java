package org.clear_fit.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private int userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private Set<String> userBookings = new HashSet<>();
}
