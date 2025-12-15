package org.digital_wallet.service;

import lombok.Data;
import org.digital_wallet.entity.Wallet;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class WalletService {


    private final Map<String, Wallet> userWalletMap = new LinkedHashMap<>();
    private static int walletIdCounter = 1;
    public void createWallet(String userName, double initialBalance) {
        // Implementation for creating a wallet
        if(userWalletMap.containsKey(userName)) {
            System.out.println("Wallet already exists for user: " + userName);
            return;
        }
        Wallet wallet = new Wallet();
        wallet.setId(walletIdCounter++);
        wallet.setUserName(userName);
        wallet.setBalance(initialBalance);
        userWalletMap.put(userName, wallet);

        System.out.println("Wallet created for user: " + userName + " with initial balance: " + initialBalance);

    }
}
