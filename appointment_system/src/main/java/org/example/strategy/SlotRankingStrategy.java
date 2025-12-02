package org.example.strategy;

import org.example.entity.TimeSlot;

import java.util.List;

public interface SlotRankingStrategy {

    List<TimeSlot> rankSlots(List<TimeSlot> slots);
}
