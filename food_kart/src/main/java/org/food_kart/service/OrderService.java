package org.food_kart.service;

import lombok.Data;
import org.food_kart.entity.Order;
import org.food_kart.entity.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class OrderService {

    private final RestaurantService restaurantService;
    private final UserService userService;
    private final Map<String, List<Order>> orderRepo = new HashMap<>(); // user -> order
    private static int orderIdCounter = 1;

    public OrderService(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public void placeOrder(String restaurantName, int quantity) {
        if (userService.getLoggedInUser() == null) {
            System.out.println("Please log in to place an order.");
            return;
        }

        if(!restaurantService.getRestaurantNameUserPhoneNumberMap().containsKey(restaurantName)) {
            System.out.println("invalid restaurant name, please try again");
            return;
        }

        Restaurant restaurant = restaurantService.getRestaurantRepo().get(restaurantService.getRestaurantNameUserPhoneNumberMap().get(restaurantName));


        if (quantity <= 0 || quantity>restaurant.getCurrentQuantity()) {
            System.out.println("invalid quantity, please try again");
            return;
        }
        Order order = new Order();
        order.setRestaurantName(restaurantName);
        order.setQuantity(quantity);
        order.setTotalPrice(restaurant.getPrice() * quantity);
        order.setOrderId(orderIdCounter++);
        order.setRestaurantId(restaurant.getRestaurantId());

        restaurant.setCurrentQuantity(restaurant.getCurrentQuantity() - quantity);

        orderRepo.computeIfAbsent(userService.getLoggedInUser(), k -> new java.util.ArrayList<>()).add(order);
        System.out.println("Order placed successfully at " + restaurantName + " for quantity " + quantity);
    }

    public void viewOrders() {
        if (userService.getLoggedInUser() == null) {
            System.out.println("Please log in to view your orders.");
            return;
        }

        List<Order> orders = orderRepo.get(userService.getLoggedInUser());
        if (orders == null || orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("Your Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId() +
                               ", Restaurant: " + order.getRestaurantName() +
                               ", Quantity: " + order.getQuantity() +
                               ", Total Price: " + order.getTotalPrice());
        }
    }
}
