package main.java.com.javadventure.map;

import main.java.com.javadventure.map.rooms.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is intended as the place where you utilize the MapBuilder to construct the map of the area for your adventure.
 */
public class GameMap {
    private Map<String, Room> roomMap = new HashMap<>();
    private Room currentRoom;

    GameMap addRoom(Room room){
        roomMap.put(room.getName(), room);
        return this;
    }

    public Room getRoomByName(String name){
        return roomMap.get(name);
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }
    public Room getCurrentRoom(){
        return currentRoom;
    }
}
