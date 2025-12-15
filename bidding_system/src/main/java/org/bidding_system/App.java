package org.bidding_system;

import org.bidding_system.service.*;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserService userService = new UserService();
        EventService eventService = new EventService();
        EventRegistrationService eventRegistrationService = new EventRegistrationService(userService, eventService);

        BiddingService biddingService = new BiddingService(userService, eventService, eventRegistrationService);

        WinnerService winnerService = new WinnerService(biddingService, eventService, userService);
        userService.addUser("Akshay", 10000);
        userService.addUser("Chris", 5000);

        eventService.addEvent("BBD", "2025-12-13", "iPhone");
        eventService.addEvent("CCD", "2025-12-14", "samsung");

        eventRegistrationService.registerUserForEvent(1L, 1L);
        eventRegistrationService.registerUserForEvent(2L, 1L);

        eventRegistrationService.registerUserForEvent(1L, 2L);
        eventRegistrationService.registerUserForEvent(2L, 2L);
//
        biddingService.submitBid(1L, 1L, Arrays.asList(100L, 200L, 400L, 500L, 600L));
        biddingService.submitBid(2L, 1L, Arrays.asList(100L, 200L, 400L, 500L));

        biddingService.submitBid(1L, 2L, Arrays.asList(300L, 400L, 500L));
        biddingService.submitBid(2L, 2L, Arrays.asList(200L, 300L, 400L, 500L, 600L));
//
//        winnerService.declareWinnerForEvent(1L);
//        winnerService.declareWinnerForEvent(2L);

        winnerService.listPastWinners("DESC");






    }
}
