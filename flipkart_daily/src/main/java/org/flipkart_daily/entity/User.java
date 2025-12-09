package org.flipkart_daily.entity;

import lombok.Data;

@Data
public class User {

    private long id;
    private String name;
    private String address;
    private double walletBalance;
}
