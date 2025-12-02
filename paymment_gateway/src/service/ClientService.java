package service;

import entity.Client;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ClientService {
    
    private Map<String, Client> clientMap;
    
    public ClientService() {
        this.clientMap = new HashMap<>();
    }
    
    public void addClient(Client client) {
        // Implementation for adding a client
        if (clientMap.containsKey(client.getClientId())){
            System.out.println("Client with ID " + client.getClientId() + " already exists.");
        }
        clientMap.put(client.getClientId(), client);
        
    }
    
    public void removeClient(String clientId) {
        // Implementation for removing a client
        if (!clientMap.containsKey(clientId)){
            System.out.println("Client with ID " + clientId + " does not exist.");
        }
        clientMap.remove(clientId);
    }
    
    public boolean hasClient(String clientId) {
        // Implementation to check if a client exists
        if (clientMap.containsKey(clientId)) {
            return true;
        }
        return false;
    }
}
