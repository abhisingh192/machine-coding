package org.food_kart.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Restaurant {
    private int restaurantId;
    private String restaurantName;
    private List<String> serviceablePincodes;
    private String foodItem;
    private double price;
    private int currentQuantity;
    private double averageRating;
    private List<Review> review;
}
