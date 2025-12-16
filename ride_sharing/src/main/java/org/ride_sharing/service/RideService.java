package org.ride_sharing.service;

import org.ride_sharing.entity.Ride;
import org.ride_sharing.entity.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class RideService {
    
    private final VehicleService vehicleService;
    private final UserService userService;
    
    private static int rideIdCounter = 1;
    private final Map<Integer, Ride> rideRepo = new HashMap<>(); //rideId -> ride
    
    public RideService(VehicleService vehicleService, UserService userService) {
        this.vehicleService = vehicleService;
        this.userService = userService;
    }
    
    public void offerRide(String rideGiver, String origin, String destination, int numOfSeats, String vehicleModel, String vehicleId) {
        if (!userService.getUserRepo().containsKey(rideGiver)) {
            System.out.println("user does not exist");
            return;
        }
        
        if (vehicleService.getVehicleRepo().containsKey(vehicleId)) {
            System.out.println("vehicle does not exist");
            return;
        }
        
        if (!vehicleService.getVehicleRepo().get(vehicleId).getOwner().equalsIgnoreCase(rideGiver)) {
            System.out.println("vehicle does not belong to the owner");
            return;
        }
        
        if (numOfSeats>2 || numOfSeats<0) {
            System.out.println("invalid number of seats, must be 1 or 2");
            return;
        }
        
        Ride ride = new Ride();
        ride.setRideGiver(rideGiver);
        ride.setRideId(rideIdCounter++);
        ride.setSeats(numOfSeats);
        ride.setOrigin(origin);
        ride.setDestination(destination);
        ride.setModel(vehicleModel);
        ride.setVehicleId(vehicleId);
        
        rideRepo.put(ride.getRideId(), ride);
        
        
    }

    public void findRide(String rideTaker, String origin, String destination, int numOfSeats, String preference) {
        
    }
}
