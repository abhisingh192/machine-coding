package org.conference_management.entity;

import lombok.Data;

@Data
public class Booking {

    private int bookingId;
    private String buildingId;
    private long floorId;
    private String roomId;
    private String slot;
}
