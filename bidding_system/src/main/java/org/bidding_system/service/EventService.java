package org.bidding_system.service;

import lombok.Data;
import org.bidding_system.entity.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Data
public class EventService {

    private final Map<Long, Event> eventRepository = new LinkedHashMap<>();
    private final Set<String> eventsNames = new HashSet<>(); // check for duplicate event names
    private static long eventIdCounter = 1;


    public void addEvent(String name, String date, String prize) {
        if (eventsNames.contains(name)) {
            System.out.println("Event with name " + name + " already exists.");
            return;
        }
        boolean eventExistsToday = eventRepository.values().stream().anyMatch(x-> x.getDate().equalsIgnoreCase(date));
        if (eventExistsToday) {
            System.out.println("only one event allowed per day");
            return;
        }
        Event event = new Event();
        event.setId(eventIdCounter++);
        event.setName(name);
        event.setDate(date);
        event.setPrize(prize);
        eventRepository.put(event.getId(), event);
        eventsNames.add(name);

        System.out.println("Event " + name + " added on " + date + " with prize " + prize + ".");
    }

}
