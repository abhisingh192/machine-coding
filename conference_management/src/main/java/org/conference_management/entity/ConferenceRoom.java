package org.conference_management.entity;

import lombok.Data;

import java.util.*;

@Data
public class ConferenceRoom {
    private String buildingId;
    private long floorId;
    private String roomId;
    private Set<String> bookedSlots = new HashSet<>();
}
