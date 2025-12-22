package org.clear_fit.service;

import lombok.Data;
import org.clear_fit.entity.Booking;

@Data
public class WorkoutViewerService {

    private final AdminService adminService;
    private final BookingService bookingService;



    public WorkoutViewerService(AdminService adminService, BookingService bookingService) {
        this.adminService = adminService;
        this.bookingService = bookingService;
    }


    public void viewWorkouts() {
        adminService.viewAllWorkouts();
    }

    public void viewUserSlots(int userId) {

        if (!bookingService.getBookings().containsKey(userId)) {
            System.out.println("No bookings found for User ID " + userId);
            return;
        }

        System.out.println("Bookings for User ID " + userId + ":");
        for (Booking booking : bookingService.getBookings().get(userId)) {
            System.out.println(booking);
        }
    }
}
