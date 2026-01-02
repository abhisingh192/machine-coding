package org.bug_bounty.entity;

import lombok.Data;

@Data
public class User {

    private String name;
    private String email;
    private Role role;
    private String password;

}
