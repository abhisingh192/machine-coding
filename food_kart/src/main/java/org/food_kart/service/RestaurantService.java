package org.food_kart.service;

import lombok.Data;
import org.food_kart.entity.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class RestaurantService {
    
    private final UserService userService;
    private static int restaurantIdCounter = 1;
    private final Map<String, Restaurant> restaurantRepo = new HashMap<>();//user -> restaurant
    private final Map<String, String> restaurantNameUserPhoneNumberMap = new HashMap<>();
    
    
    public void registerRestaurant(String name, List<String> pincodes, String foodItem, double price, int initialQuantity) {
        if(userService.getLoggedInUser()==null) {
            System.out.println("no user logged in, please login first");
            return;
        } else {
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(restaurantIdCounter++);
            restaurant.setRestaurantName(name);
            restaurant.setPrice(price);
            restaurant.setFoodItem(foodItem);
            restaurant.setServiceablePincodes(pincodes);
            restaurant.setCurrentQuantity(initialQuantity);
            
            restaurantRepo.put(userService.getLoggedInUser(), restaurant);
            restaurantNameUserPhoneNumberMap.put(name, userService.getLoggedInUser());
            System.out.println("restaurant registered successfully");
        }
    }
    
    public void updateQuantity(String restaurantName, int quantity) {
        if(restaurantNameUserPhoneNumberMap.containsKey(restaurantName)) {
            System.out.println("invalid restaurant name, please try again");
            return;
        }
        String loggedInUser = restaurantNameUserPhoneNumberMap.get(restaurantName);
        
        if(userService.getLoggedInUser()!=loggedInUser) {
            System.out.println("please logic first to update the qty of your restaurant");
            return;
        }
        
        restaurantRepo.get(loggedInUser).setCurrentQuantity(quantity);

        System.out.println("quantity updated successfully");
    }
    
    public void listRestaurants(String criteria) {
        if (criteria.equalsIgnoreCase("rating")) {
            List<String> sortedRestaurantKeys =
                    restaurantRepo.entrySet()
                            .stream()
                            .filter(entry -> entry.getValue().getServiceablePincodes()
                                    .contains(userService.getUserRepo().get(entry.getKey()).getPincode()))
                            .sorted((e1, e2) ->
                                    Double.compare(
                                            e2.getValue().getAverageRating(),
                                            e1.getValue().getAverageRating()
                                    )
                            )
                            .map(Map.Entry::getKey)
                            .toList();
            for (String key : sortedRestaurantKeys) {
                Restaurant restaurant = restaurantRepo.get(key);
                System.out.println("Restaurant Name: " + restaurant.getRestaurantName() +
                        ", Food Item: " + restaurant.getFoodItem() +
                        ", Price: " + restaurant.getPrice() +
                        ", Available Quantity: " + restaurant.getCurrentQuantity());
            }
            
            
        } else if (criteria.equalsIgnoreCase("price")) {
            List<String> sortedRestaurantKeys =
                    restaurantRepo.entrySet()
                            .stream()
                            .filter(entry -> entry.getValue().getServiceablePincodes()
                                    .contains(userService.getUserRepo().get(entry.getKey()).getPincode()))
                            .sorted((e1, e2) ->
                                    Double.compare(
                                            e2.getValue().getPrice(),
                                            e1.getValue().getPrice()
                                    )
                            )
                            .map(Map.Entry::getKey)
                            .toList();
            for (String key : sortedRestaurantKeys) {
                Restaurant restaurant = restaurantRepo.get(key);
                System.out.println("Restaurant Name: " + restaurant.getRestaurantName() +
                        ", Food Item: " + restaurant.getFoodItem() +
                        ", Price: " + restaurant.getPrice() +
                        ", Available Quantity: " + restaurant.getCurrentQuantity());
            }
            
        } else {
            System.out.println("invalid criteria");
            return;
            
        }
    }
}
