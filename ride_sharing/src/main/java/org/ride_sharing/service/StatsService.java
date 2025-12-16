package org.ride_sharing.service;

import lombok.Data;

@Data
public class StatsService {

    private final UserService userService;

    public StatsService(UserService userService) {
        this.userService = userService;
    }

    public void getRideStats() {
        for (String userName : userService.getUserRideStats().keySet()) {
            System.out.println("User: " + userName + ", Rides Given: " +
                    userService.getUserRideStats().get(userName).getOrDefault("given", 0) +
                    ", Rides Taken: " +
                    userService.getUserRideStats().get(userName).getOrDefault("taken", 0));
        }

    }
}
