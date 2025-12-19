package org.food_kart.entity;

import lombok.Data;

import java.util.List;

@Data
public class Review {
    private int reviewId;
    private String restaurantName;
    private int restaurantId;
    private double rating;
    private String comment;
    
}
