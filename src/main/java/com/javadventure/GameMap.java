package main.java.com.javadventure;

import main.java.com.javadventure.rooms.Room;

import java.util.Map;

/**
 * This class is intended as the place where you utilize the MapBuilder to construct the map of the area for your adventure.
 */
public class GameMap {
    Map<String, Room> roomMap;




    public void addRoom(Room room){
        roomMap.put(room.getName(), room);
    }
}
