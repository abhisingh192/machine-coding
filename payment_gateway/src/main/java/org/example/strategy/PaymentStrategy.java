package org.example.strategy;

import java.util.concurrent.ThreadLocalRandom;

public interface PaymentStrategy {
    
    boolean pay(double amount);

    default boolean getTransactionStatus() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
