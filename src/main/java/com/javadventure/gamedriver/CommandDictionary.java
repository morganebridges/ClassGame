package main.java.com.javadventure.gamedriver;

import java.util.Map;

public class CommandDictionary {
    //private Map<String, CommandHandler>
    Map<String, String> commandList;
    public boolean isValid(String command){
        return commandList.containsKey(command);
    }
}
