package org.flipkart_daily.service;

import lombok.Data;
import org.flipkart_daily.entity.Cart;
import org.flipkart_daily.entity.InventoryItem;
import org.flipkart_daily.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CartService {

    private final UserService userService;
    private final Map<String, Cart> carts = new HashMap<>();
    private final InventoryService inventoryService;
    private final ProductService productService;

    public CartService(UserService userService, InventoryService inventoryService, ProductService productService) {
        this.userService = userService;
        this.inventoryService = inventoryService;
        this.productService = productService;
    }


    public void addToCart(String name, String brand, String category, int quantity) {
        // Implementation for adding product to cart
        String key = brand + "-" + category;

        if (userService.getUsers().containsKey(name)) {
            // Add product to the user's cart
            Cart cart = new Cart();
            cart.setUserName(name);
            InventoryItem item = new InventoryItem();
            item.setBrand(brand);
            item.setCategory(category);
            item.setQuantity(quantity);
            item.setId(productService.getProducts().get(key).getId());
            cart.getItems().add(item);
            cart.setTotalAmount(cart.getTotalAmount()+productService.getProducts().get(key).getPrice());
            carts.put(name, cart);
        } else {
            System.out.println("User not found.");
        }
    }

    public void removeFromCart(String name, String brand, String category) {
        // Implementation for removing product from cart
        String key = brand + "-" + category;


        if (userService.getUsers().containsKey(name)) {
            // Add product to the user's cart
            Cart userCart = carts.get(name);
            List<InventoryItem> items = userCart.getItems();
            
            InventoryItem item = (InventoryItem) userCart.getItems().stream()
                    .filter(x -> x.getId()!=productService.getProducts().get(key).getId())
                    .map(x->x);
            
            items = items.stream()
                    .filter(x -> x.getId()!=item.getId()).toList();
            userCart.setItems(items);
            userCart.setTotalAmount(userCart.getTotalAmount() - (productService.getProducts().get(key).getPrice()*item.getQuantity()));

        } else {
            System.out.println("User not found.");
        }
    }

    public void getCart(String userName) {
        // Implementation for viewing cart contents
        
        if (userService.getUsers().containsKey(userName)) {
            Cart userCart = carts.get(userName);
            for (InventoryItem item : userCart.getItems()) {
                System.out.println(item.getBrand() + "->" + item.getCategory() + "->" + item.getQuantity());
            }
            System.out.println("Total card value: "+userCart.getTotalAmount());


        } else {
            System.out.println("User not found.");
        }
    }
}
