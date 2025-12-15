package org.conference_management.service;

import lombok.Data;
import org.conference_management.entity.Building;
import org.conference_management.entity.ConferenceRoom;
import org.conference_management.entity.Floor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class RoomService {

    private final Map<String, Building> buildingRepo = new HashMap<>();
    private final Map<String, Floor> buildingFloorMap = new HashMap<>();
    private final Map<String, ConferenceRoom> BuildingFloorRoomMap = new HashMap<>();
    private static long buildingIdCounter = 1;

    public void addBuilding() {
        // Implementation to add a building
        String key = "b"+buildingIdCounter++;
        Building building = new Building();
        building.setId(key);
        buildingRepo.put(key, building);
        System.out.println("Building " +key+ " added successfully.");
    }

    public void addFloorsToBuilding(String buildingId, int noOfFloors) {
        // Implementation to add floors to a building
        Building building = buildingRepo.get(buildingId);
        if (building != null) {
            building.setNoOfFloors(noOfFloors);
            for (int i = 1; i <= noOfFloors; i++) {
                // Initialize floors if needed
                Floor floor = new Floor();
                floor.setBuildingId(buildingId);
                floor.setFloorNumber(i);
                building.getFloors().put(i, floor);
                buildingFloorMap.put(buildingId + "_" + i, floor);
            }
            System.out.println("Added " + noOfFloors + " floors to Building " + buildingId);
        } else {
            System.out.println("Building with ID " + buildingId + " not found.");
        }
    }

    public void addRoomsToFloor(String roomId, int floorNumber, String buildingId) {
        // Implementation to add rooms to a floor
        Building building = buildingRepo.get(buildingId);

        if (building != null && floorNumber <= building.getNoOfFloors()) {

            ConferenceRoom room = new ConferenceRoom();
            room.setBuildingId(buildingId);
            room.setRoomId(roomId);
            room.setFloorId(floorNumber);
            buildingFloorMap.get(buildingId + "_" + floorNumber).getRooms().put(roomId, room);
            BuildingFloorRoomMap.put(buildingId + "_" + floorNumber + "_" + roomId, room);
            System.out.println("Added room" + roomId + "  to Floor " +floorNumber + floorNumber + " of Building " + buildingId);
        } else {
            System.out.println("Building with ID " + buildingId + " not found or invalid floor number.");
        }
    }

    public void listRooms(String buildingId, int floorNumber) {
        // Implementation to list rooms on a floor
        Building building = buildingRepo.get(buildingId);

        if (building != null && floorNumber <= building.getNoOfFloors()) {
            Floor floor = buildingFloorMap.get(buildingId + "_" + floorNumber);
            Set<String> roomIds = new HashSet<>(floor.getRooms().keySet());
            System.out.println("Rooms on Floor " + floorNumber + " of Building " + buildingId + ": " + roomIds);
        } else {
            System.out.println("Building with ID " + buildingId + " not found or invalid floor number.");
        }
    }


}
