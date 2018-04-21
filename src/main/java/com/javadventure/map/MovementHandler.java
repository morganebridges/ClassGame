package main.java.com.javadventure.map;

import main.java.com.javadventure.map.rooms.MovementDirections;
import main.java.com.javadventure.map.rooms.Room;

import java.util.Map;

public class MovementHandler {
    public static boolean isMovementCommand(String command){
        return (command.equals("n") || command.equals("s") || command.equals("e") || command.equals("w"));
    }

    /**
     * This method handles movement for a player in a game.
     * @param direction - The direction the player would like to move
     * @param currentRoom - The room the player is currently in
     * @param map - The game map representing the game the player is in.
     * @return
     */
    public static Room handleMovement(String direction, Room currentRoom, GameMap map){
        if(direction.equals(MovementDirections.NORTH.getCommand())){
            if(currentRoom.getNorthExit() == null){
                printNoExit(direction);
            }else{
                return map.getRoomByName(currentRoom.getNorthExit());
            }
        } else if(direction.equals(MovementDirections.SOUTH.getCommand())){
            if(currentRoom.getSouthExit() == null){
                printNoExit(direction);
            }else{
                return map.getRoomByName(currentRoom.getSouthExit());
            }
        } else if(direction.equals(MovementDirections.EAST.getCommand())){
            if(currentRoom.getEastExit() == null){
                printNoExit(direction);
            }else{
                return map.getRoomByName(currentRoom.getEastExit());
            }
        } else if(direction.equals(MovementDirections.WEST.getCommand())){
            if(currentRoom.getWestExit() == null){
                printNoExit(direction);
            }else{
                return map.getRoomByName(currentRoom.getWestExit());
            }
        }
        return currentRoom;
    }
    private static void printNoExit(String direction){
        System.out.println("There is no exit to the " + direction + ", perhaps another course of action?");
    }
}
