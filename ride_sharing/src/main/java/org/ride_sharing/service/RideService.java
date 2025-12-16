package org.ride_sharing.service;

import org.ride_sharing.entity.Ride;
import org.ride_sharing.entity.Vehicle;

import java.util.*;

public class RideService {
    
    private final VehicleService vehicleService;
    private final UserService userService;
    
    private static int rideIdCounter = 1;
    private final Map<Integer, Ride> rideRepo = new HashMap<>(); //rideId -> ride
    private final Set<String> usedVehicleIds = new HashSet<>();
    public RideService(VehicleService vehicleService, UserService userService) {
        this.vehicleService = vehicleService;
        this.userService = userService;
    }
    
    public void offerRide(String rideGiver, String origin, String destination, int numOfSeats, String vehicleModel, String vehicleId) {
        if (!userService.getUserRepo().containsKey(rideGiver)) {
            System.out.println("user does not exist");
            return;
        }
        
        if (!vehicleService.getVehicleRepo().containsKey(vehicleId)) {
            System.out.println("vehicle does not exist");
            return;
        }
        
        if (!vehicleService.getVehicleRepo().get(vehicleId).getOwner().equalsIgnoreCase(rideGiver)) {
            System.out.println("vehicle does not belong to the owner");
            return;
        }

        if (usedVehicleIds.contains(vehicleId)) {
            System.out.println("vehicle is already in use for another ride");
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
        ride.setRideStatus("AVAILABLE");
        
        rideRepo.put(ride.getRideId(), ride);
        usedVehicleIds.add(vehicleId);
        userService.getUserRideStats()
                .computeIfAbsent(rideGiver, k -> new HashMap<>())   // create inner map if missing
                .put("offered",
                        userService.getUserRideStats()
                                .get(rideGiver)
                                .getOrDefault("offered", 0) + 1);         // increment safely

        System.out.println("ride created with rideId: " + ride.getRideId());
    }

    public void findRide(String rideTaker, String origin, String destination, int numOfSeats, String preference) {

        if(!userService.getUserRepo().containsKey(rideTaker)) {
            System.out.println("user does not exist");
            return;
        }
        if (numOfSeats>2 || numOfSeats<0) {
            System.out.println("invalid number of seats, must be 1 or 2");
            return;
        }

        // searching logic
        if (preference.equalsIgnoreCase("most vacant")) {
            Optional<Ride> bestRide = rideRepo.values().stream()
                    .filter(ride -> ride.getOrigin().equalsIgnoreCase(origin)
                            && ride.getDestination().equalsIgnoreCase(destination)
                            && ride.getSeats() >= numOfSeats)
                    .max(Comparator.comparingInt(Ride::getSeats));

            if (bestRide.isPresent()) {
                Ride ride = bestRide.get();
                ride.setRideStatus("BOOKED");
                ride.setRideTaker(rideTaker);
                userService.getUserRideStats()
                        .computeIfAbsent(rideTaker, k -> new HashMap<>())   // create inner map if missing
                        .put("taken",
                                userService.getUserRideStats()
                                        .get(rideTaker)
                                        .getOrDefault("taken", 0) + 1);         // increment safely

                System.out.println("RideId: " + ride.getRideId()
                        + ", RideGiver: " + ride.getRideGiver()
                        + ", Seats Available: " + ride.getSeats());
            } else {
                System.out.println("No rides available");
            }


        } else {
            Optional<Ride> firstMatch = rideRepo.values().stream()
                    .filter(ride -> ride.getOrigin().equalsIgnoreCase(origin)
                            && ride.getDestination().equalsIgnoreCase(destination)
                            && ride.getSeats() >= numOfSeats
                            && ride.getModel().equalsIgnoreCase(preference))
                    .findFirst();

            if (firstMatch.isPresent()) {
                Ride ride = firstMatch.get();
                ride.setRideStatus("BOOKED");
                ride.setRideTaker(rideTaker);
                userService.getUserRideStats()
                        .computeIfAbsent(rideTaker, k -> new HashMap<>())   // create inner map if missing
                        .put("taken",
                                userService.getUserRideStats()
                                        .get(rideTaker)
                                        .getOrDefault("taken", 0) + 1);         // increment safely

                System.out.println("RideId: " + ride.getRideId()
                        + ", RideGiver: " + ride.getRideGiver()
                        + ", Seats Available: " + ride.getSeats());
            } else {
                System.out.println("No rides available");
            }
        }
        
    }

    public void endRide(int rideId) {
        if (!rideRepo.containsKey(rideId)) {
            System.out.println("ride does not exist");
            return;
        }

        Ride ride = rideRepo.get(rideId);
        ride.setRideStatus("COMPLETED");
    }
}
