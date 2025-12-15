package org.digital_wallet.service;

import lombok.Data;
import org.digital_wallet.entity.Transaction;
import org.digital_wallet.entity.TransactionType;
import org.digital_wallet.entity.Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class TransferService {
    private final WalletService walletService;
    private final Map<String, List<Transaction>> ledger = new HashMap<>();
    private static int transactionIdCounter = 1;
    
    public TransferService(WalletService walletService) {
        this.walletService = walletService;
    }
    
    public void transferMoney(String fromUser, String toUser, double amount) {
        
        Wallet senderWallet = walletService.getUserWalletMap().get(fromUser);
        Wallet receiverWallet = walletService.getUserWalletMap().get(toUser);
        
        if(Objects.isNull(senderWallet)) {
            System.out.println("Invalid sender account");
            return;
        }
        
        if(Objects.isNull(receiverWallet)) {
            System.out.println("Invalid receiver account");
        }
        
        if (amount > senderWallet.getBalance()) {
            System.out.println("insufficient account balance");
            return;
        }
        
        senderWallet.setBalance(senderWallet.getBalance()-amount);
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEBIT);
        transaction.setTransactionId(transactionIdCounter++);
        ledger.computeIfAbsent(fromUser, k -> new ArrayList<>())
                .add(transaction);        
        receiverWallet.setBalance(senderWallet.getBalance()+amount);
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionType(TransactionType.CREDIT);
        transaction1.setTransactionId(transactionIdCounter++);
        ledger.computeIfAbsent(toUser, k -> new ArrayList<>())
                .add(transaction1);

        if (senderWallet.getBalance() == receiverWallet.getBalance()) {
            // trigger offer, add 10 rs to each
            senderWallet.setBalance(senderWallet.getBalance()+10);
            receiverWallet.setBalance(receiverWallet.getBalance()+10);
            
            Transaction t2 = new Transaction();
            t2.setTransactionId(transactionIdCounter++);
            t2.setTransactionType(TransactionType.OFFER);
            t2.setAmount(10);
            ledger.computeIfAbsent(fromUser, k -> new ArrayList<>())
                    .add(t2);
            
            Transaction t3 = new Transaction();
            t3.setTransactionId(transactionIdCounter++);
            t3.setTransactionType(TransactionType.OFFER);
            t3.setAmount(10);
            ledger.computeIfAbsent(toUser, k -> new ArrayList<>())
                    .add(t3);

        }
    }
}
