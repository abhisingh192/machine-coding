package org.conference_management.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Floor {

    private String buildingId;
    private int floorNumber;

    private Map<String,ConferenceRoom> rooms = new HashMap<>();
}
