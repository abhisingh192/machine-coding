package org.bidding_system.service;

import lombok.Data;
import org.bidding_system.entity.Event;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class WinnerService {
    
    private final BiddingService biddingService;
    private final EventService eventService;
    private final UserService userService;
    
    public WinnerService(BiddingService biddingService, EventService eventService, UserService userService) {
        this.biddingService = biddingService;
        this.eventService = eventService;
        this.userService = userService;
    }
    
    public void declareWinnerForEvent(long eventId) {
        
        if (!eventService.getEventRepository().containsKey(eventId)) {
            System.out.println("event with eventID "+eventId+ " does not exist");
            return;
        }
        
        LinkedHashMap<Long, Long> lowestBidForUser = biddingService.getEventIdToUserBidMap().get(eventId);

        Map.Entry<Long, Long> best = null;

        for (Map.Entry<Long, Long> e : lowestBidForUser.entrySet()) {
            if (best == null || e.getValue() < best.getValue()) {
                best = e;
            }
        }
        String userName = userService.getUserRepository().get(best.getKey()).getName();
        String prize = eventService.getEventRepository().get(eventId).getPrize();
        System.out.println(userName + " wins " + prize + " with lowest bid of " +best.getValue() );
    }
    
    public void listPastWinners(String orderBy) {

        int size = eventService.getEventRepository().size();
        int countOfPastWinners = Math.min(5, size);

        if (orderBy.equalsIgnoreCase("ASC")) {

            int count = 0;
            for (Map.Entry<Long, Event> entry : eventService.getEventRepository().entrySet()) {
                if (count == countOfPastWinners) break;
                declareWinnerForEvent(entry.getKey());
                count++;
            }
        } else {
            List<Map.Entry<Long, Event>> eventList = new ArrayList<>(eventService.getEventRepository().entrySet());
            for (int i = size - 1; i >= size - countOfPastWinners; i--) {
                declareWinnerForEvent(eventList.get(i).getKey());
            }
        }
    }
}


