package org.clear_fit.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Session {
    private int sessionId;
    private SlotType slotType;
    private WorkoutType workoutType;
    private int centreId;
    private CentreName centreName;

    private int capacity;
    private SlotTime slotTime;

    private List<Booking> bookings = new ArrayList<>();
}
