package repo;

import entity.Bank;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BankRepo {
    
    private Map<String, Bank> bankDataStore = new HashMap<>();
}
