package org.food_kart.entity;

import lombok.Data;

@Data
public class Order {
    private int orderId;
    private int restaurantId;
    private String restaurantName;
    private int quantity;
    private double totalPrice;
}
