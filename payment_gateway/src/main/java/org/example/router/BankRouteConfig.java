package org.example.router;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entity.Bank;

@Data
@AllArgsConstructor
public class BankRouteConfig {

    private Bank bank;
    private int percentage;
}
