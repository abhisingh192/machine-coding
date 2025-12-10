package org.flipkart_daily;

import org.flipkart_daily.entity.Checkout;
import org.flipkart_daily.entity.Product;
import org.flipkart_daily.service.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ProductService productService = new ProductService();
        InventoryService inventoryService = new InventoryService();
        UserService userService = new UserService();
        CartService cartService = new CartService(userService, inventoryService, productService);
        CheckoutService checkoutService = new CheckoutService(userService, cartService);
        productService.addOrUpdateProduct("Amul", "Milk", 100);
        productService.addOrUpdateProduct("Amul", "Curd", 50);
        productService.addOrUpdateProduct("Nestle", "Milk", 60);
        productService.addOrUpdateProduct("Nestle", "Curd", 90);

        inventoryService.addOrUpdateInventory("Amul", "Milk", 10);
        inventoryService.addOrUpdateInventory("Amul", "Milk", 10);
        inventoryService.addOrUpdateInventory("Amul", "Curd", 5);
        inventoryService.addOrUpdateInventory("Nestle", "Milk", 15);
        inventoryService.addOrUpdateInventory("Nestle", "Curd", 10);

        inventoryService.viewInventory();
        System.out.println();

        userService.addUser("Jhonny", "Flipkart Bellandur", 600);
        userService.addUser("Naruto", "BTM layout", 500);
        userService.addUser("Goku", "vijay nagar", 3000);

        cartService.addToCart("Jhonny", "Nestle", "Milk", 5);
        inventoryService.addOrUpdateInventory("Nestle", "Milk", 5);
        cartService.addToCart("Goku", "Nestle", "Milk", 10);

        cartService.getCart("Goku");
        cartService.getCart("Jhonny");
        cartService.getCart("Naruto");

        checkoutService.checkout("Jhonny");
        checkoutService.checkout("Goku");
        checkoutService.checkout("Naruto");

    }
}
