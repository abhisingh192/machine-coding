package org.ride_sharing.service;

import lombok.Data;
import org.ride_sharing.entity.Vehicle;

import java.util.HashMap;
import java.util.Map;

@Data
public class VehicleService {
    
    private final Map<String, Vehicle> vehicleRepo = new HashMap<>(); // vehicleId -> vehicle
    private final UserService userService;
    
    public VehicleService(UserService userService) {
        this.userService = userService;
    }
    public void addVehicle(String owner, String model, String vehicleId) {
        if (!userService.getUserRepo().containsKey(owner)) {
            System.out.println("user does not exist");
            return;
        }
        
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setModel(model);
        vehicle.setOwner(owner);
        
        vehicleRepo.put(vehicleId, vehicle);
    }
}
