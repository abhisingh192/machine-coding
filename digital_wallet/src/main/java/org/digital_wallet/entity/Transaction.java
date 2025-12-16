package org.digital_wallet.entity;

import lombok.Data;

@Data
public class Transaction {
    
    private int transactionId;
    private String senderReceiverName;
    private TransactionType transactionType;
    private double amount;
    
}
