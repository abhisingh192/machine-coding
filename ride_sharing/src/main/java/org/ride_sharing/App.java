package org.ride_sharing;

import org.ride_sharing.service.RideService;
import org.ride_sharing.service.StatsService;
import org.ride_sharing.service.UserService;
import org.ride_sharing.service.VehicleService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserService userService = new UserService();
        VehicleService vehicleService = new VehicleService(userService);
        RideService rideService = new RideService(vehicleService, userService);

        userService.addUser("rohan", "M", 36);
        userService.addUser("shashank", "M", 29);
        userService.addUser("nandini", "F", 29);
        userService.addUser("shipra", "F", 27);
        userService.addUser("gaurav", "M", 27);
        userService.addUser("rahul", "M", 35);

        vehicleService.addVehicle("rohan", "Swift", "KA-01-12345");
        vehicleService.addVehicle("shashank", "baleno", "TS-05-62395");
        vehicleService.addVehicle("shipra", "polo", "KA-05-41491");
        vehicleService.addVehicle("shipra", "activa", "KA-12-12332");
        vehicleService.addVehicle("rahul", "xuv", "KA-05-1234" );

        rideService.offerRide("rohan", "Hyderabad", "Bangalore", 1, "Swift", "KA-01-12345");
        rideService.offerRide("shipra", "Bangalore", "mysore", 1, "activa", "KA-12-12332");
        rideService.offerRide("shipra", "Bangalore", "mysore", 2, "polo", "KA-05-41491");
        rideService.offerRide("shashank", "Hyderabad", "Bangalore", 2, "baleno", "TS-05-62395");
        rideService.offerRide("rahul", "Hyderabad", "Bangalore", 5, "xuv", "KA-05-1234");
        rideService.offerRide("rohan", "Bangalore", "Pune", 1, "Swift", "KA-01-12345");

        rideService.findRide("nandini", "Bangalore", "Mysore", 1, "Most Vacant");
        rideService.findRide("gaurav", "Bangalore", "Mysore", 1, "Activa");
        rideService.findRide("shashank", "Mumbai", "Bangalore", 1, "Most Vacant");
        rideService.findRide("rohan", "Hyderabad", "Bangalore", 1, "baleno");
        rideService.findRide("shashank", "Hyderabad", "Bangalore", 1, "polo");

        rideService.endRide(3);
        rideService.endRide(2);
        rideService.endRide(4);

        StatsService statsService = new StatsService(userService);

        statsService.getRideStats();






    }
}
