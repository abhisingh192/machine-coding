package org.example.strategy;

import org.example.entity.TimeSlot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultRankingStrategy implements SlotRankingStrategy {
    @Override
    public List<TimeSlot> rankSlots(List<TimeSlot> slots) {
        return slots.stream()
                .sorted(Comparator.comparing(TimeSlot:: getStartTime))
                .collect(Collectors.toList());
    }




}
