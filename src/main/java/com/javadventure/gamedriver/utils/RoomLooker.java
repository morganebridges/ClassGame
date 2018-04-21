package main.java.com.javadventure.gamedriver.utils;

import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.map.rooms.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomLooker {
    public static String lookAtThing(String command, Room room){
        String correctDescription = "";
        int matchedItems = 0;
        if(room.getRoomItems() != null){
            for(Map.Entry<String, Item> itemEntry : room.getRoomItems().entrySet()){
                List<String> lookList = itemEntry.getValue().getLookList();
                if(matchesForItem(command, lookList)){
                    matchedItems++;
                    correctDescription = itemEntry.getValue().getDescription();

                }
            }
            if(correctDescription.length() > 1 && matchedItems == 1){
                return correctDescription;
            }
            if(matchedItems > 1){
                return "I'm sorry, which item did you want to look at?";
            }

        }
        return "I'm sorry, I couldn't find what you were looking for";
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
            if(foundAt){
                possibleCommands.add(token);
                if(fullCommand.length() > 0){
                    fullCommand += " " + token;
                }else{
                    fullCommand += token;
                }
            }

            if(token.contains("at")){
                foundAt = true;
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
