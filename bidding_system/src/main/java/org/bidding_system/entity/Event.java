package org.bidding_system.entity;

import lombok.Data;

@Data
public class Event {
    private long id;
    private String name;
    private String date;
    private String prize;
}
