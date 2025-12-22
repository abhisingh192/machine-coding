package org.clear_fit;

import org.clear_fit.entity.SlotTime;
import org.clear_fit.entity.SlotType;
import org.clear_fit.entity.WorkoutType;
import org.clear_fit.service.AdminService;
import org.clear_fit.service.BookingService;
import org.clear_fit.service.UserService;
import org.clear_fit.service.WorkoutViewerService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        UserService userService = new UserService();
        AdminService adminService = new AdminService();
        BookingService bookingService = new BookingService(adminService, userService);
        WorkoutViewerService workoutViewerService = new WorkoutViewerService(adminService, bookingService);
        adminService.addSlot(1,3, SlotTime.ONE, SlotType.MORNING, WorkoutType.CARDIO);
        adminService.addSlot(1,3, SlotTime.TWO, SlotType.MORNING, WorkoutType.WEIGHTS);
        adminService.addSlot(1,3, SlotTime.ONE, SlotType.EVENING, WorkoutType.CARDIO);
        adminService.addSlot(2,3, SlotTime.ONE, SlotType.EVENING, WorkoutType. WEIGHTS);

        userService.registerUser("Aashi", "abc@gmail.com", "1234567890", "password123");

        workoutViewerService.viewWorkouts();

        bookingService.bookSlot(1,1,1);

        workoutViewerService.viewUserSlots(1);

    }
}
