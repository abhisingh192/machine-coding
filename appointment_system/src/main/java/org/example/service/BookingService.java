package org.example.service;

import org.example.entity.*;
import org.example.strategy.SlotRankingStrategy;
import org.example.util.TimeValidator;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

    // Maps of entities
    private Map<Long, Doctor> doctorsById = new HashMap<>();
    private Map<String, Doctor> doctorsByName = new HashMap<>();

    private Map<Long, Patient> patientsById = new HashMap<>();
    private Map<String, Patient> patientsByName = new HashMap<>();

    private Map<Long, TimeSlot> slotsById = new HashMap<>();
    // Key: doctorId#startTime -> slot
    private Map<String, TimeSlot> slotsByDoctorAndStart = new HashMap<>();

    private Map<Long, Appointment> appointments = new HashMap<>();

    // Id sequences for entities
    private long doctorIdSequence = 1;
    private long patientIdSequence = 1;
    private long slotIdSequence = 1;
    private long appointmentIdSequence = 1000;

    private SlotRankingStrategy rankingStrategy;

    public BookingService(SlotRankingStrategy rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
    }

    public void registerDoctor(String name, Speciality speciality) {
        long id = doctorIdSequence++;

        Doctor doctor = new Doctor(id, name, speciality);
        doctorsById.put(id, doctor);
        doctorsByName.put(name, doctor);

        System.out.println("Welcome Dr. " + name + "!!");
    }

    public void markDoctorAvailability(String name, List<TimeRange> ranges) {
        Doctor doctor = doctorsByName.get(name);
        if (doctor == null) {
            System.out.println("Doctor with name " + name + " not found.");
            return;
        }

        // validate the time range is 30 mins
        for(TimeRange range : ranges) {
            boolean isTimeRangeValid = TimeValidator.isValidTimeRange(range.getStart().toString(), range.getEnd().toString());
            if (!isTimeRangeValid) {
                System.out.println("Invalid time range: " + range.getStart() + " - " + range.getEnd());
                return;
            }
            long minutes = Duration.between(range.getStart(), range.getEnd()).toMinutes();
            if (minutes!=30) {
                System.out.println("o: Sorry Dr. " + name + " slots are 30 mins only");
                return;
            }
        }

        // if validation passes, create slots
        for (TimeRange range : ranges) {
            long slotId = slotIdSequence++;
            TimeSlot slot = new TimeSlot(slotId, doctor, range.getStart(), range.getEnd(), SlotStatus.AVAILABLE);
            slotsById.put(slotId, slot);
            String key = doctor.getId() + "#" + range.getStart().toString();
            slotsByDoctorAndStart.put(key, slot);
        }

        System.out.println("o: Done Doc!");
    }

    public void showAvailableBySpeciality(Speciality speciality) {
        List<TimeSlot> availableSlots = slotsById.values().stream()
                .filter(slot -> slot.getDoctor().getSpeciality() == speciality &&
                        slot.getStatus() == SlotStatus.AVAILABLE)
//                .sorted(Comparator
//                        .comparing(TimeSlot::getStartTime)
//                        .thenComparing(s -> s.getDoctor().getName()))
                .toList();



        List<TimeSlot> rankedSlots = rankingStrategy.rankSlots(availableSlots);

        if (rankedSlots.isEmpty()) {
            System.out.println("No available slots for speciality: " + speciality);
            return;
        }

        for (TimeSlot slot : availableSlots) {
            String doctorName = slot.getDoctor().getName();
            LocalTime start = slot.getStartTime();
            LocalTime end = slot.getEndTime();
            System.out.println("o: Dr." + doctorName + ": (" + formatTime(start) + "-" + formatTime(end) + ")");
        }
    }

    // Helper to build composite key for doctor and time
    private String keyForSlot(long doctorId, LocalTime start) {
        return doctorId + "#" + start.toString();
    }

    // Helper to format time in H:mm (e.g., 9:30)
    private String formatTime(LocalTime time) {
        // Remove leading zero from hour if present
        int hour = time.getHour();
        int minute = time.getMinute();
        return hour + ":" + String.format("%02d", minute);
    }

    public void cancelAppointment(long appointmentId) {
        Appointment appointment = appointments.get(appointmentId);
        if (appointment == null) {
            System.out.println("o: Invalid booking id");
            return;
        }

        // Mark the slot as AVAILABLE again
        TimeSlot slot = appointment.getSlot();
        slot.setStatus(SlotStatus.AVAILABLE);

        // Remove the appointment
        appointments.remove(appointmentId);

        System.out.println("o: Booking Cancelled");
    }

    public void bookAppointment(String patientName, String doctorName, LocalTime startTime) {
        Doctor doctor = doctorsByName.get(doctorName);
        if (doctor == null) {
            System.out.println("o: Doctor not found: " + doctorName);

        }

        // Find or register patient
        Patient patient = patientsByName.get(patientName);
        if (patient == null) {
            long patientId = patientIdSequence++;
            patient = new Patient(patientId, patientName);
            patientsById.put(patientId, patient);
            patientsByName.put(patientName, patient);
        }

        // Find the slot for this doctor and this time
        String key = keyForSlot(doctor.getId(), startTime);
        TimeSlot slot = slotsByDoctorAndStart.get(key);

        if (slot == null) {
            System.out.println("o: No such slot for Dr." + doctorName + " at " + formatTime(startTime));
        }

        if (slot == null) {
            System.out.println("o: No such slot for Dr." + doctorName + " at " + formatTime(startTime));
        }

        // Book the appointment
        slot.setStatus(SlotStatus.BOOKED);
        long appointmentId = appointmentIdSequence++;
        Appointment appointment = new Appointment(appointmentId, slot, patient);
        appointments.put(appointmentId, appointment);

        System.out.println("o: Booking confirmed with id " + appointmentId);
    }

}
