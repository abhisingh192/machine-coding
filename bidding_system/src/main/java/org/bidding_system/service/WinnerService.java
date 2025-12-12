package org.bidding_system.service;

import lombok.Data;

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
        
        if (orderBy.equalsIgnoreCase("ASC")) {
            List<Map.Entry<Long, Long>> firstFive = new ArrayList<>();

            int count = 0;
            for (Map.Entry<Long, Long> entry : linkedHashMap.entrySet()) {
                if (count == 5) break;
                firstFive.add(entry);
                count++;
            }
        }
    }
}


