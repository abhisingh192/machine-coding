package org.bidding_system.service;

import org.bidding_system.entity.Event;

import java.util.List;

public class BiddingService {

    private final UserService userService;

    private final EventService eventService;

    private final EventRegistrationService eventRegistrationService;

    public BiddingService(UserService userService, EventService eventService, EventRegistrationService eventRegistrationService) {
        this.userService = userService;
        this.eventService = eventService;
        this.eventRegistrationService = eventRegistrationService;
    }



    public void submitBid(Long userId, Long eventId, List<Long> bidAmounts) {
        // Implementation for submitting a bid

        if (userId == null || !userService.getUserRepository().containsKey(userId)) {
            System.out.println("User does not exist.");
            return;
        }

        if (eventId == null || !eventService.getEventRepository().containsKey(eventId)) {
            System.out.println("Event does not exist.");
            return;
        }

        Event event = eventService.getEventRepository().get(eventId);

        if (eventRegistrationService.getRegistrationMap().containsKey(eventId) && !eventRegistrationService.getRegistrationMap().get(eventId).contains(userId)) {
            System.out.println("User is not registered for the event.");
            return;
        }
        if (bidAmounts == null || bidAmounts.isEmpty()) {
            System.out.println("Bid amounts cannot be empty.");
            return;
        }

        if (bidAmounts.size()>5) {
            System.out.println("Cannot submit more than 5 bid amounts.");
            return;
        }

        int maxBid = bidAmounts.stream().max(Long::compare).orElse(0L).intValue();
        int minBid = bidAmounts.stream().min(Long::compare).orElse(0L).intValue();

        if(minBid==0){
            System.out.println("Bid amounts must be greater than zero.");
            return;
        }

        boolean hasDuplicates = bidAmounts.stream().distinct().count() != bidAmounts.size();

        if (hasDuplicates) {
            System.out.println("Bid amounts must be unique.");
            return;
        }




    }
}
