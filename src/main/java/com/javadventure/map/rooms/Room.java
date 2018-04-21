package main.java.com.javadventure.map.rooms;

import com.sun.istack.internal.Nullable;
import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.monsters.Monster;

import java.util.HashMap;
import java.util.Map;

public class Room {
    String name;
    String description;
    String label;
    @Nullable private String northExit;
    @Nullable private String southExit;
    @Nullable private String eastExit;
    @Nullable private String westExit;


    //A list of all of the items in the room (visible normally)
    private Map<String, Item> roomItems = new HashMap<>();
    private Map<String, Item> hiddenItems = new HashMap<>();
    private Map<String, Monster> roomMonsters = new HashMap<>();

    public Room(String name, String description, String label, String northExit, String southExit, String eastExit, String westExit, Map<String, Item> roomItems, Map<String, Monster> roomMonsters) {
        this.name = name;
        this.label = label;
        this.description = description;
        this.northExit = northExit;
        this.southExit = southExit;
        this.eastExit = eastExit;
        this.westExit = westExit;
        this.roomItems = roomItems;
        this.roomMonsters = roomMonsters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNorthExit() {
        return northExit;
    }

    public void setNorthExit(String northExit) {
        this.northExit = northExit;
    }

    public String getSouthExit() {
        return southExit;
    }

    public void setSouthExit(String southExit) {
        this.southExit = southExit;
    }

    public String getEastExit() {
        return eastExit;
    }

    public void setEastExit(String eastExit) {
        this.eastExit = eastExit;
    }

    public String getWestExit() {
        return westExit;
    }

    public void setWestExist(String westExit) {
        this.westExit = westExit;
    }

    public Map<String, Item> getRoomItems() {
        return roomItems;
    }

    public void setRoomItems(Map<String, Item> roomItems) {
        this.roomItems = roomItems;
    }

    public void setHiddenItems(Map<String, Item> hiddenItems){
        this.hiddenItems = hiddenItems;
    }
    public void addHiddenItem(Item item){
        this.hiddenItems.put(item.getName(), item);
    }
    public void addRoomItem(Item item){
        if(roomItems.get(item.getName()) != null){
            roomItems.get(item.getName()).increaseBy(item.getCount());
        }else{
            this.roomItems.put(item.getName(), item);
        }
    }

    public Map<String, Monster> getRoomMonsters() {
        return roomMonsters;
    }



    public void setRoomMonsters(Map<String, Monster> roomMonsters) {
        this.roomMonsters = roomMonsters;
    }
    public String toDisplay(){
        String line = "\n_____________________________________________\n";

        String disp = "" + "[ < " + label + " > ]";
        disp +=line;
        disp += "\n" + this.description;
        disp += "\nExits:";
        if(eastExit != null){
            disp += " -e- ";
        }
        if(northExit != null){
            disp += "-n-";
        }
        if(southExit != null){
            disp += "-s-";
        }
        if(westExit != null){
            disp += "-w-";
        }
        if(roomItems != null && roomItems.size() > 0){
            for(Map.Entry<String, Item> itemEntry : roomItems.entrySet()){
                Item item = itemEntry.getValue();
                //Add the displaying of our item to the output string
                disp +=  "\n" + item.getName();
                if(item.getCount() > 1){
                    disp += "(" + item.getCount() + ")";
                }
            }
        }


        //Now we need to loop over all of the monsters, so we can display them in the room.
        if(roomMonsters != null && roomMonsters.size() > 0){
            disp += "\n**************";
            for(Map.Entry<String, Monster> monsterEntry : roomMonsters.entrySet()){
                Monster monster = monsterEntry.getValue();
                disp +=  "\n" + monster.getName();
            }
        }

        return disp;
    }
    public static class Builder {
        public Builder(){}
        String name;
        String description;
        private String northExit;
        private String southExit;
        private String eastExit;
        private String westExist;
        private String label;
        Map<String, Item> roomItems = new HashMap<>();
        Map<String, Item> hiddenItems = new HashMap<>();
        Map<String, Monster> roomMonsters;

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder label(String label){
            this.label = label;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder northExit(String northExit){
            this.northExit = northExit;
            return this;
        }
        public Builder southExit(String southExit){
            this.southExit = southExit;
            return this;
        }
        public Builder eastExit(String eastExit){
            this.eastExit = eastExit;
            return this;
        }
        public Builder westExit(String westExit){
            this.westExist = westExit;
            return this;
        }
        public Builder roomItems(Map<String, Item> roomItems){
            this.roomItems = roomItems;
            return this;
        }

        public Builder hiddenItems(Map<String, Item> hiddenItems){
            this.hiddenItems = hiddenItems;
            return this;
        }

        public Builder monsters(Map<String, Monster> roomMonsters){
            this.roomMonsters = roomMonsters;
            return this;
        }
        public Builder noItems(){
            this.roomItems = new HashMap<>();
            return this;

        }
        public Builder noMonsters(){
            this.roomMonsters = new HashMap<>();
            return this;
        }

        @Override
        public boolean equals(Object obj){
            return (obj instanceof Room && ((Room)obj).getName().equals(this.name));
        }

        public Room build(){
            return new Room(name, description, label, northExit, southExit, eastExit, westExist, roomItems, roomMonsters);
        }
}

}

