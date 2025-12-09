package org.flipkart_daily.entity;

import lombok.Data;

@Data
public class Product {
    private long id;
    private String brand;
    private String category;
    private double price;
}
