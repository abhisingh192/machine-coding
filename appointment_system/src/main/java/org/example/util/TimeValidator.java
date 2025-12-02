package org.example.util;

public class TimeValidator {

    public static boolean isValidTimeRange(String startTime, String endTime) {
        try {
            String[] startParts = startTime.split(":");
            String[] endParts = endTime.split(":");

            if (startParts.length != 2 || endParts.length != 2) {
                return false;
            }

            int startHour = Integer.parseInt(startParts[0]);
            int startMinute = Integer.parseInt(startParts[1]);
            int endHour = Integer.parseInt(endParts[0]);
            int endMinute = Integer.parseInt(endParts[1]);

            if (startHour < 0 || startHour > 23 || endHour < 0 || endHour > 23) {
                return false;
            }
            if (startMinute < 0 || startMinute > 59 || endMinute < 0 || endMinute > 59) {
                return false;
            }

            if (endHour < startHour || (endHour == startHour && endMinute <= startMinute)) {
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
