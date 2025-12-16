package org.digital_wallet.service;

import lombok.Data;
import lombok.var;

@Data
public class StatementAndOverviewService {

    private final TransferService transferService;
    private final WalletService walletService;

    public StatementAndOverviewService(TransferService transferService, WalletService walletService) {
        this.transferService = transferService;
        this.walletService = walletService;
    }
    public void getStatement(String userName) {
        System.out.println("Statement for user: " + userName);
        if (!transferService.getLedger().containsKey(userName)) {
            System.out.println("No transactions found for user: " + userName);
            return;
        }
        for (var transaction : transferService.getLedger().get(userName)) {
            System.out.println(transaction.getSenderReceiverName() +
                    " " + transaction.getTransactionType() + " " + transaction.getAmount());
        }
    }

    public void getOverview() {

        System.out.println("Wallet Overview:");
        for (var entry : walletService.getUserWalletMap().entrySet()) {
            System.out.println("User: " + entry.getKey() + ", Balance: " + entry.getValue().getBalance());
        }
    }
}
