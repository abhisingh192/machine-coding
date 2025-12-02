package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class TimeSlot {

    // Unique id for the slot
    private long id;
    // Doctor assigned to this slot
    private Doctor doctor;
    // Start time of the slot
    private LocalTime startTime;
    // End time of the slot
    private LocalTime endTime;
    // Current status of slot
    private SlotStatus status;
}
