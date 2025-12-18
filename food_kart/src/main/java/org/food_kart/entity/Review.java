package org.food_kart.entity;

import lombok.Data;

@Data
public class Review {
    private int reviewId;
    private String restaurantName;
    private int restaurantId;
    private int rating;
    private String comment;
    
}
