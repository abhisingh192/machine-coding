package org.example.router;

import org.example.entity.Bank;
import org.example.entity.PaymentMethod;

import java.util.*;

public class PaymentRouter {
    // For each paymode, store list of bank distribution configs
    private Map<String, List<BankRouteConfig>> distribution = new HashMap<>();
    private Random random = new Random();

    public void addOrUpdateBankDistribution(String paymode, Bank bank, int percentage) {
        List<BankRouteConfig> configs = distribution.computeIfAbsent(paymode, k -> new ArrayList<>());

        // Check if bank already exists in the config
        for (BankRouteConfig config : configs) {
            if (config.getBank().getBankName().equals(bank.getBankName())) {
                config.setPercentage(percentage);
                return;
            }
        }

        configs.add(new BankRouteConfig(bank, percentage));
    }

    public List<BankRouteConfig> getDistributionForMode(PaymentMethod mode) {
        return distribution.getOrDefault(mode.getName(), Collections.emptyList());
    }

    public Map<String, List<BankRouteConfig>> getAllDistribution() {
        return distribution;
    }

    public Bank route(PaymentMethod mode) {

        List<BankRouteConfig> configs = distribution.get(mode.getName());
        if (configs == null || configs.isEmpty()) {
            return null;
        }

        int total = 0;
        for (BankRouteConfig config : configs) {
            total += config.getPercentage();
        }

        if (total <= 0) {
            return null;
        }

        int r = random.nextInt(total) + 1; // 1..total
        int cumulative = 0;

        for (BankRouteConfig config : configs) {
            cumulative += config.getPercentage();
            if (r <= cumulative) {
                return config.getBank();
            }
        }

        return configs.get(0).getBank();
    }
}
