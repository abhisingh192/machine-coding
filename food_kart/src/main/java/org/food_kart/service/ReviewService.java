package org.food_kart.service;

import org.food_kart.entity.Review;

public class ReviewService {

    private final RestaurantService restaurantService;
    private final UserService userService;
    private static int reviewIdCounter = 1;

    public ReviewService(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public void rateRestaurant(String restaurantName, int rating, String comment) {
        if(userService.getLoggedInUser() == null) {
            System.out.println("Please log in to rate a restaurant.");
            return;
        }

        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5");
            return;
        }
        Review review = new Review();
        String userId = restaurantService.getRestaurantNameUserPhoneNumberMap().get(restaurantName);
        int restaurantId = restaurantService.getRestaurantRepo().get(userId).getRestaurantId();
        review.setRestaurantId(restaurantId);
        review.setReviewId(reviewIdCounter++);
        review.setRating(rating);
        review.setComment(comment);
        review.setRestaurantName(restaurantName);


        restaurantService.getRestaurantRepo().get(userId).getReview().add(review);
        System.out.println("Thank you for rating the restaurant!");
    }
}
