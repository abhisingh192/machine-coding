package org.conference_management.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Building {


    private String id;
    private int noOfFloors;
    private Map<Integer, Floor> floors;

}
