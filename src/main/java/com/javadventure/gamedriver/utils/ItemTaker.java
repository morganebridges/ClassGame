package main.java.com.javadventure.gamedriver.utils;

import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemTaker {
    public static Item takeItem(String command, Room room){

        Item correctItem = null;
        int matchedItems = 0;
        if(room.getRoomItems() != null){
            for(Map.Entry<String, Item> itemEntry : room.getRoomItems().entrySet()){
                List<String> lookList = itemEntry.getValue().getLookList();
                if(matchesForItem(command, lookList)){
                    matchedItems++;
                    correctItem = itemEntry.getValue();

                }
            }
            if(correctItem != null && matchedItems == 1){
                return correctItem;
            }
            if(matchedItems > 1){
                return null;
            }

        }
        return null;
    }
    public static void takeAll(Room room, Player player){
        for(Map.Entry<String, Item> item : room.getRoomItems().entrySet()){
            System.out.println("You take " + item.getValue().getName());
            player.addItem(item.getValue());
        }
    }
    private static boolean matchesForItem(String command, List<String> lookList){
        String[] commandTokens = command.split(" ");

        //Iterate over our tokens, looking for the "at" keyword
        boolean foundAt = false;
        List<String> possibleCommands = new ArrayList<>();
        String fullCommand  = "";

        for(String token : commandTokens){
            //Build up a list of the full command given after the "at" as well as the individual tokens. Try to give the user
            //the best chance of giving the proper syntax for the item
            possibleCommands.add(token);
            if(fullCommand.length() > 0){
                fullCommand += " " + token;
            }else{
                fullCommand += token;
            }

        }
        possibleCommands.add(fullCommand);
        for(String posCmd : possibleCommands){
            if(lookList.contains(posCmd)){
                return true;
            }
        }
        return false;
    }
}
