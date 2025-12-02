package repo;

import entity.Client;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ClientRepo {
    private Map<String, Client> clientDataStore = new HashMap<>();
}
