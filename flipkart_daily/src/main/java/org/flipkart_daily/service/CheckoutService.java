package org.flipkart_daily.service;

import org.flipkart_daily.entity.User;

public class CheckoutService {
    
    private final UserService userService;
    private final CartService cartService;
    
    public CheckoutService(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }
    
    public void checkout(String userName) {
        
        if (userService.getUsers().containsKey(userName)) {
            User user = userService.getUsers().get(userName);
            double walletBalance = user.getWalletBalance();
            
            if (cartService.getCarts().isEmpty()) {
                System.out.println("No order to checkout");
            }
            if(walletBalance>=cartService.getCarts().get(userName).getTotalAmount()) {
                walletBalance-=cartService.getCarts().get(userName).getTotalAmount();
                user.setWalletBalance(walletBalance);
                System.out.println("succesful checkout, new wallet amount "+walletBalance);
            } else {
                System.out.printf("not enough wallet balance to checkout");
            }
            
        } else {
            System.out.println("User not found.");
        }
    }
}
