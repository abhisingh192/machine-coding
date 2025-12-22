package org.clear_fit.service;

import lombok.Data;
import org.clear_fit.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class BookingService {

    private final AdminService adminService;
    private final UserService userService;
    private static int bookingCounter = 1;

    private final Map<Integer, List<Booking>> bookings = new HashMap<>();

    public BookingService(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    public void bookSlot(int userId, int centreId, int sessionId) {

        if (!userService.getUserRepo().containsKey(userId)) {
            System.out.println("User with ID " + userId + " does not exist.");
            return;
        }

        if (!adminService.getCentreRepo().containsKey(centreId)) {
            System.out.println("Centre with ID " + centreId + " does not exist.");
            return;
        }

        if (!adminService.getSessionRepo().containsKey(sessionId)) {
            System.out.println("Session with ID " + sessionId + " does not exist.");
            return;
        }

        Session session = adminService.getSessionRepo().get(sessionId);
        User user = userService.getUserRepo().get(userId);

        if (!session.getBookings().isEmpty() && session.getBookings().size()== session.getCapacity()) {
            System.out.println("Session with ID " + sessionId + " is fully booked.");
            return;
        }

        if (!session.getBookings().isEmpty() && user.getUserBookings().contains(centreId + "-" + sessionId)) {
            System.out.println("User with ID " + userId + " has already booked this session.");
            return;
        }

        if (session.getCentreId() != centreId) {
            System.out.println("Session with ID " + sessionId + " does not belong to Centre with ID " + centreId + ".");
            return;
        }

        Booking booking = new Booking();
        booking.setBookingId(bookingCounter++);
        booking.setCentreId(centreId);
        booking.setSessionId(sessionId);
        booking.setUserId(userId);
        session.getBookings().add(booking);
        bookings.computeIfAbsent(userId, k -> new ArrayList<>()).add(booking);
        user.getUserBookings().add(centreId + "-" + sessionId);

        System.out.println("Booking successful for User ID " + userId + " in Session ID " + sessionId + " at Centre ID " + centreId + ". Booking id : " + booking.getBookingId());
    }


}
