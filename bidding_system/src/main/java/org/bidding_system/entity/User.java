package org.bidding_system.entity;

import lombok.Data;

@Data
public class User {
    private long id;
    private String name;
    private long superCoins;
}
