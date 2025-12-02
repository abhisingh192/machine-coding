package org.example.service;

import org.example.entity.Bank;
import org.example.router.BankRouteConfig;
import org.example.router.PaymentRouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DistributionService {

    private final PaymentRouter router;
    private final BankService bankService;

    public DistributionService(PaymentRouter router) {
        this.router = router;
        this.bankService = new BankService();
    }

    public void showDistribution() {
        System.out.println("Showing distribution of payments across banks.");
        Map<String, List<BankRouteConfig>> all = router.getAllDistribution();
        for (Map.Entry<String, List<BankRouteConfig>> entry : all.entrySet()) {
            String mode = entry.getKey();
            System.out.print(mode + " : ");
            List<BankRouteConfig> configs = entry.getValue();
            List<String> desc = new ArrayList<>();
            for (BankRouteConfig config : configs) {
                desc.add(config.getBank().getBankName() + "=" + config.getPercentage() + "%");
            }
            System.out.println(String.join(", ", desc));
        }
    }

    public void configureDistribution(String paymode, String bankName, int percentage) {
        System.out.println("Configuring distribution for paymode " + paymode + " to bank " + bankName + " with percentage " + percentage);
        Bank bank = bankService.getBankMap().get(bankName);

        if (bank == null) {
            System.out.println("Bank not found: " + bankName);
            return;
        }
        router.addOrUpdateBankDistribution(paymode, bank, percentage);
        System.out.println("Configured router: " + paymode + " -> " + bankName + " = " + percentage + "%");

    }

}
