package org.example.service;

import lombok.Data;
import org.example.entity.Bank;

import java.util.HashMap;
import java.util.Map;

@Data
public class BankService {

    private final Map<String, Bank> bankMap = new HashMap<>();

    public void addBank(Bank bank) {
        // Implementation for adding a bank
        if (bankMap.containsKey(bank.getBankName())){
            System.out.println("Bank with name " + bank.getBankName() + " already exists.");
        }
        bankMap.put(bank.getBankName(), bank);
    }


}
