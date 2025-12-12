package org.bidding_system.service;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class EventRegistrationService {

    private final UserService userService;
    private final EventService eventService;

    private final Map<Long, Set<Long>> registrationMap = new HashMap<>(); // event id -> set of user ids

    public EventRegistrationService(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    public void registerUserForEvent(Long userId, Long eventId) {

        if (userId == null || !userService.getUserRepository().containsKey(userId)) {
            System.out.println("User does not exist.");
            return;
        }

        if (eventId == null || !eventService.getEventRepository().containsKey(eventId)) {
            System.out.println("Event does not exist.");
            return;
        }

        registrationMap
                .computeIfAbsent(eventId, id -> new HashSet<>())
                .add(userId);

        String userName = userService.getUserRepository().get(userId).getName();
        System.out.println("User " + userName + " registered for event " + eventService.getEventRepository().get(eventId).getName());
    }

}
