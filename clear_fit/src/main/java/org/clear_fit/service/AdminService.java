package org.clear_fit.service;
import lombok.Data;
import org.clear_fit.entity.SlotType;
import org.clear_fit.entity.WorkoutType;
import org.clear_fit.entity.Centre;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.clear_fit.entity.Centre;
import org.clear_fit.entity.CentreName;
import org.clear_fit.entity.Session;
import org.clear_fit.entity.SlotTime;

import java.util.HashMap;
import java.util.Map;

@Data
public class AdminService {

    private final Map<Integer, Centre> centreRepo = new HashMap<>();
    private final Map<Integer, CentreName> centreNameRepo = new HashMap<>();

    private final Map<Integer, Session> sessionRepo = new HashMap<>();
    private static int sessionIdCounter = 1;

    public AdminService() {
        centreNameRepo.put(1, CentreName.BELLANDUR);
        centreNameRepo.put(2, CentreName.KORAMANGALA);

        centreRepo.put(1, new Centre(1, CentreName.BELLANDUR));
        centreRepo.put(2, new Centre(2, CentreName.KORAMANGALA));
    }
    public void addSlot(int id, int capacity, SlotTime slotTime, SlotType slotType, WorkoutType workoutType) {
        if (!centreNameRepo.containsKey(id)) {
            System.out.println("Centre with ID " + id + " does not exist.");
            return;
        }
        Session session = new Session();
        session.setSessionId(sessionIdCounter++);
        session.setSlotType(slotType);
        session.setWorkoutType(workoutType);
        session.setCentreId(id);
        session.setSlotTime(slotTime);
        session.setCapacity(capacity);
        session.setCentreName(centreNameRepo.get(id));

        sessionRepo.put(session.getSessionId(), session);

    }

    public void updateSlot(int centreId, int sessionId, int capacity) {

        if (!centreRepo.containsKey(centreId)) {
            System.out.println("Centre with ID " + centreId + " does not exist.");
            return;
        }

        if (!sessionRepo.containsKey(sessionId)) {
            System.out.println("Session with ID " + sessionId + " does not exist.");
            return;
        }

        if (sessionRepo.get(sessionId).getCentreId() != centreId) {
            System.out.println("Session with ID " + sessionId + " does not belong to Centre with ID " + centreId + ".");
            return;
        }

        Session session = sessionRepo.get(sessionId);
        session.setCapacity(capacity);

    }

    public void viewAllWorkouts() {
        for (Session session : sessionRepo.values()) {
            System.out.println("Session ID: " + session.getSessionId() +
                               ", Centre ID: " + session.getCentreId() +
                                ", Centre Name: " + session.getCentreName() +
                               ", Slot Type: " + session.getSlotType() +
                               ", Workout Type: " + session.getWorkoutType() +
                               ", Capacity: " + session.getCapacity() +
                               ", Slot Time: " + session.getSlotTime());
        }
    }
}
