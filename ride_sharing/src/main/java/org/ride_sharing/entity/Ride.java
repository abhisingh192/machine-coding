package org.ride_sharing.entity;

import lombok.Data;

@Data
public class Ride {
    private int rideId;
    private String rideGiver;
    private String rideTaker;
    private int seats;
    private String model;
    private String vehicleId;
    private String origin;
    private String destination;
    private String rideStatus;
}
