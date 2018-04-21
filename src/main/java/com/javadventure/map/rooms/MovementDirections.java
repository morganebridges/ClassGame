package main.java.com.javadventure.map.rooms;

public enum MovementDirections {
    WEST("w"),
    EAST("e"),
    NORTH("n"),
    SOUTH("s");

    public final String command;
    MovementDirections(String command){
        this.command = command;
    }
    public String getCommand(){
        return command;
    }
    public static String getByCommand(String command){
        switch(command){
            case "w":
                return "West";
            case "e":
                return "East";
            case "s":
                return "South";
            case "n":
                return "North";

        }
        return "";
    }
}
