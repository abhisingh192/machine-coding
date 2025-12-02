package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/** Supported payment methods */
@Data
@AllArgsConstructor
public class PaymentMethod {
    
    private long id;
    private String name;
    
}
