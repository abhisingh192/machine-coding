package org.example.service;


import org.example.entity.Bank;
import org.example.entity.Client;
import org.example.router.PaymentRouter;
import org.example.strategy.PaymentStrategy;

public class PaymentService {
    
    private final PaymentStrategy paymentStrategy;

    private final PayModeService payModeService;

    private final PaymentRouter router = new PaymentRouter();


    
    public PaymentService(PaymentStrategy paymentStrategy, PayModeService payModeService) {
        this.paymentStrategy = paymentStrategy;
        this.payModeService = payModeService;}
    
    public void makePayment(String clientId, double amount, Long paymentMethod) {
        // Implementation for making a payment
        System.out.println("Processing payment of $" + amount + " for client ID: " + clientId);

        boolean isPaymentMethodValid = payModeService.getPayModeMap().containsKey(paymentMethod);

        if(!isPaymentMethodValid) {
            System.out.println("Payment method with ID " + paymentMethod + " is not supported.");
            return;
        }


        // Validate client
        Client client = payModeService.getClientService().getClientMap().get(clientId);
        if (client == null) {
            System.out.println("Client with ID " + clientId + " does not exist.");
            return;
        }

        // Validate PG supports paymode
        if (!payModeService.getPayModeMap().containsKey(paymentMethod)) {
            System.out.println("Payment method with ID " + paymentMethod + " is not supported by the Payment Gateway.");
            return;
        }

        // Validate client supports paymode
        if (!client.getPaymentMethods().containsKey(paymentMethod)) {
            System.out.println("Payment method with ID " + paymentMethod + " is not supported by client ID " + clientId + ".");
            return;
        }

        // Route to bank
        Bank bank = router.route(payModeService.getPayModeMap().get(paymentMethod));
        if (bank == null) {
            System.out.println("No bank available to process payment for payment method ID " + paymentMethod + ".");
            return;
        }

        // Delegate to bank
        paymentStrategy.pay(100);
        
    }
}
