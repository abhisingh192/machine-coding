package org.digital_wallet.service;

import lombok.Data;
import lombok.var;
import org.digital_wallet.entity.Transaction;
import org.digital_wallet.entity.TransactionType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class OfferService {

    private final WalletService walletService;
    private final TransferService transferService;

    public OfferService(WalletService walletService, TransferService transferService) {
        this.walletService = walletService;
        this.transferService = transferService;
    }

    public void applyOffer() {

        List<String> top3Users = transferService.getLedger().entrySet()
                .stream()
                .sorted((a, b) -> {

                    // --- 1. Compare number of transactions ---
                    int sizeA = a.getValue().size();
                    int sizeB = b.getValue().size();

                    int cmp = Integer.compare(sizeB, sizeA);  // descending
                    if (cmp != 0) return cmp;

                    // --- 2. Tie-breaker: wallet balance ---
                    double balanceA = walletService.getUserWalletMap().get(a.getKey()).getBalance();
                    double balanceB = walletService.getUserWalletMap().get(b.getKey()).getBalance();

                    return Double.compare(balanceB, balanceA);  // descending
                })
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());


        for (int i=0;i<top3Users.size();i++) {
            var wallet = walletService.getUserWalletMap().get(top3Users.get(i));
            if(i==0) {
                wallet.setBalance(wallet.getBalance() + 10); // First place bonus
                Transaction transaction = new Transaction();
                transaction.setAmount(10);
                transaction.setTransactionType(TransactionType.OFFER2);
                transferService.getLedger().get(top3Users.get(i)).add(transaction);

            } else if(i==1) {
                wallet.setBalance(wallet.getBalance() + 5); // Second place bonus
                Transaction transaction = new Transaction();
                transaction.setAmount(10);
                transaction.setTransactionType(TransactionType.OFFER2);
                transferService.getLedger().get(top3Users.get(i)).add(transaction);

            } else if(i==2) {
                wallet.setBalance(wallet.getBalance() + 2); // Third place bonus
                Transaction transaction = new Transaction();
                transaction.setAmount(10);
                transaction.setTransactionType(TransactionType.OFFER2);
                transferService.getLedger().get(top3Users.get(i)).add(transaction);

            }
        }

    }
}
