package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Appointment {

    // Unique id of the appointment
    private long id;
    // Slot associated with this appointment
    private TimeSlot slot;
    // Patient who booked this appointment
    private Patient patient;
}
