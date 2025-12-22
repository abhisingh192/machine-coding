package org.clear_fit.entity;

import lombok.Data;

@Data
public class Booking {
    private int bookingId;
    private int centreId;
    private int sessionId;
    private int userId;

}
