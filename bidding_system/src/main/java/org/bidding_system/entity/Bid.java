package org.bidding_system.entity;

import lombok.Data;

import java.util.List;

@Data
public class Bid {
    private long userId;
    private long eventId;
    private List<Long> bidAmounts;
}
