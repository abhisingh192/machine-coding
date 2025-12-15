package org.conference_management.service;

import lombok.Data;
import org.conference_management.entity.Booking;

import java.util.HashMap;
import java.util.Map;

@Data
public class BookingService {

    private final Map<Long, Booking> bookings = new HashMap<>();
    private final RoomService roomService;
    private static int bookingCounter = 1;

    public BookingService(RoomService roomService) {
        this.roomService = roomService;
    }



    public void createBooking(String buildingId, long floorId, String roomId, String slot) {

        if (!roomService.getBuildingRepo().containsKey(buildingId)) {
            System.out.println("Building with ID " + buildingId + " not found.");
            return;
        }

        if (!roomService.getBuildingFloorMap().containsKey(buildingId + "_" + floorId)) {
            System.out.println("Floor " + floorId + " not found in Building " + buildingId);
            return;
        }

        if (!roomService.getBuildingFloorRoomMap().containsKey(buildingId + "_" + floorId + "_" + roomId)) {
            System.out.println("Room " + roomId + " not found on Floor " + floorId + " in Building " + buildingId);
            return;
        }
        // maximum of 12 hours

        Booking booking = new Booking();
        booking.setBookingId(bookingCounter++);
        booking.setBuildingId(buildingId);
        booking.setFloorId(floorId);
        booking.setRoomId(roomId);
        booking.setSlot(slot);
        // check if the slot is already booked
        String buildingFloorRoomKey = buildingId + "_" + floorId + "_" + roomId;

        for (String value : roomService.getBuildingFloorRoomMap().get(buildingFloorRoomKey).getBookedSlots()) {
            String bookedSlotStart = value.split("-")[0];
            String bookedSlotEnd = value.split("-")[1];

            String bookingStart = slot.split("-")[0];
            String bookingEnd = slot.split("-")[1];

            if ( !(bookingEnd.compareTo(bookedSlotStart) <= 0 || bookingStart.compareTo(bookedSlotEnd) >= 0) ) {
                System.out.println("Slot " + slot + " is already booked for Room " + roomId + " on Floor " + floorId + " in Building " + buildingId);
                return;
            } else {
                //System.out.println("No conflict with booked slot " + value);
                roomService.getBuildingFloorRoomMap()
                        .get(buildingFloorRoomKey)
                        .getBookedSlots().add(slot);
            }
        }

        bookings.put((long) booking.getBookingId(), booking);
        System.out.println("Booking successful. Booking ID: " + booking.getBookingId());
    }

    public void cancelBooking(long bookingId) {
        if (bookings.containsKey(bookingId)) {
            Booking booking = bookings.get(bookingId);
            String buildingFloorRoomKey = booking.getBuildingId() + "_" + booking.getFloorId() + "_" + booking.getRoomId();
            roomService.getBuildingFloorRoomMap()
                    .get(buildingFloorRoomKey)
                    .getBookedSlots().remove(booking.getSlot());
            bookings.remove(bookingId);
            System.out.println("Booking with ID " + bookingId + " has been cancelled.");
        } else {
            System.out.println("Booking with ID " + bookingId + " not found.");
        }
    }

    public void listBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Booking booking : bookings.values()) {
            System.out.println("Booking ID: " + booking.getBookingId() +
                    ", Building ID: " + booking.getBuildingId() +
                    ", Floor ID: " + booking.getFloorId() +
                    ", Room ID: " + booking.getRoomId() +
                    ", Slot: " + booking.getSlot());
        }
    }

}
