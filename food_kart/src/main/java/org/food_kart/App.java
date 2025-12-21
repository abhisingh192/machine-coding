package org.food_kart;

import org.food_kart.entity.User;
import org.food_kart.service.RestaurantService;
import org.food_kart.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        UserService userService = new UserService();
        RestaurantService restaurantService = new RestaurantService(userService);

        userService.registerUser("pralove", "M", "p1", "560001", "pass1");
        userService.registerUser("nitesh", "M", "p2", "560002", "pass2");
        userService.registerUser("pralove", "M", "p3", "560002", "pass3");

        userService.loginUser("p1", "pass1");
        restaurantService.registerRestaurant("r1", List.of("560001", "560002"), "NI thali", 100, 5);
        restaurantService.registerRestaurant("r2", List.of("560002"), "Burger", 120, 3);

        userService.loginUser("p2", "pass2");
        restaurantService.registerRestaurant("r3", List.of("560001"), "SI thali", 150, 1);

        userService.loginUser("p3", "pass3");
        restaurantService.listRestaurants("price");
    }
}
