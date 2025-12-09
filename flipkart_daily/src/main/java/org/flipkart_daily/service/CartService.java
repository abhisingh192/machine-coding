package org.flipkart_daily.service;

import org.flipkart_daily.entity.Cart;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private final UserService userService;
    private final Map<String, Cart> carts = new HashMap<>();

    public CartService(UserService userService) {
        this.userService = userService;
    }


    public void addToCart(String name, String brand, String category, int quantity) {
        // Implementation for adding product to cart
        if (userService.getUsers().containsKey(name)) {
            // Add product to the user's cart
            Cart cart = new Cart();
            cart.setUserName(name);

        } else {
            System.out.println("User not found.");
        }
    }

    public void removeFromCart(long userId, long productId) {
        // Implementation for removing product from cart
    }

    public void getCart(long userId) {
        // Implementation for viewing cart contents
    }
}
