package org.flipkart_daily.entity;

import lombok.Data;

import java.util.List;

@Data
public class Cart {

    private long id;
    private String userName;

    private List<Product> products;

    private double totalAmount;


}
