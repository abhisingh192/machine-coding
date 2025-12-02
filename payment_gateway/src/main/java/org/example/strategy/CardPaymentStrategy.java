package org.example.strategy;

public class CardPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean pay(double amount) {
        return getTransactionStatus();
    }
}
