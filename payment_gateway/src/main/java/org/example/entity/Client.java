package org.example.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Client {
    
    private String clientId;
    private String clientName;
    private String clientEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Map<Long, PaymentMethod> paymentMethods;
}
