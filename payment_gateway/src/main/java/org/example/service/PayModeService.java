package org.example.service;


import lombok.Data;
import org.example.entity.Client;
import org.example.entity.PaymentMethod;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class PayModeService {
    // this is the global map that holds all supported payment methods, client specific pay modes will be in client class
    private final Map<Long, PaymentMethod> payModeMap;
    private final ClientService clientService;
    
    public PayModeService(Map<Long, PaymentMethod> payModeMap, ClientService clientService) {
        this.payModeMap = payModeMap;
        this.clientService = clientService;
    }

    public void listSupportedPaymodes(String clientId) {
        if (Objects.isNull(clientId)) {
            System.out.println("Supported payment methods for PG:");
            for (Map.Entry<Long, PaymentMethod> entry : payModeMap.entrySet()) {
                // process key and value
                System.out.println("- " + entry.getValue().getName());
            }
        } else {
            System.out.println("Supported payment methods for client " + clientId + ":");
            Client client = clientService.getClientMap().get(clientId);
            if (Objects.isNull(client)) {
                System.out.println("Client with ID " + clientId + " does not exist.");
            } else {
                for (Map.Entry<Long, PaymentMethod> entry : client.getPaymentMethods().entrySet()) {
                    // process key and value
                    System.out.println("- " + entry.getValue().getName());
                }
            }

        }

    }

    public void addSupportForPaymode(String clientId, String paymodeName) {
        long randomValue = ThreadLocalRandom.current().nextLong(1, 101);
        if (Objects.isNull(clientId)) {
            payModeMap.put(randomValue, new PaymentMethod(randomValue, paymodeName));
        } else {
            System.out.println("Adding payment method " + paymodeName + " for client " + clientId + ":");
            Client client = clientService.getClientMap().get(clientId);
            if (Objects.isNull(client)) {
                System.out.println("Client with ID " + clientId + " does not exist.");
            } else {
                client.getPaymentMethods().put(randomValue, new PaymentMethod(randomValue, paymodeName) );
                }
            }
            
        }

    public void removeSupportForPaymode(String clientId, String paymodeName) {
        if (Objects.isNull(clientId)) {
            payModeMap.values().removeIf(paymentMethod -> paymentMethod.getName().equalsIgnoreCase(paymodeName));
        } else {
            System.out.println("Removing payment method " + paymodeName + " for client " + clientId + ":");
            Client client = clientService.getClientMap().get(clientId);
            if (Objects.isNull(client)) {
                System.out.println("Client with ID " + clientId + " does not exist.");
            } else {
                client.getPaymentMethods().values().removeIf(paymentMethod -> paymentMethod.getName().equalsIgnoreCase(paymodeName));
            }
        }
    }
        
}
