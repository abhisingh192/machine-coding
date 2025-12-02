package service;

import strategy.PaymentStrategy;

public class PaymentService {
    
    private final PaymentStrategy paymentStrategy;
    
    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void makePayment(String clientId, double amount, String paymentMethod) {
        // Implementation for making a payment
        System.out.println("Processing payment of $" + amount + " for client ID: " + clientId);
        
        // get payment strategy
        
        
        
    }
}
