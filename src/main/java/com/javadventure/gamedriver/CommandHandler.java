package main.java.com.javadventure.gamedriver;

import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.gamedriver.combat.CombatHandler;
import main.java.com.javadventure.gamedriver.persistence.CharacterSaver;
import main.java.com.javadventure.gamedriver.utils.InputSanitizer;
import main.java.com.javadventure.gamedriver.utils.ItemTaker;
import main.java.com.javadventure.gamedriver.utils.RoomLooker;
import main.java.com.javadventure.help.Help;
import main.java.com.javadventure.map.GameMap;
import main.java.com.javadventure.map.MovementHandler;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.monsters.Monster;
import main.java.com.javadventure.player.Player;

import java.util.*;

public class CommandHandler {
    private static InputSanitizer san = new InputSanitizer();

    public void handleCommand(Player player, GameMap map, String nextCommand, Scanner in){
        Room currentRoom = map.getCurrentRoom();
        System.out.println(player.toString());

        if(nextCommand.contains("look") || nextCommand.equals("l")){
            if(nextCommand.contains("at")){
                System.out.println(RoomLooker.lookAtThing(nextCommand, currentRoom));
            }else if(currentRoom != null){
                System.out.println(currentRoom.toDisplay());
            }
        }else if(nextCommand.contains("fight")){
            CombatHandler handler = new CombatHandler();
            handler.startCombat(in, getMonsterList(currentRoom.getRoomMonsters()), currentRoom.getRoomMonsters(), player, currentRoom, map);
        }else if(nextCommand.contains("search")){

        }else if(nextCommand.contains("take")){
            Item item = ItemTaker.takeItem(nextCommand, currentRoom);
            if(item != null){
                player.addItem(item);
                currentRoom.getRoomItems().remove(item.getName());
                System.out.println("You have added " +((item.getCount() > 1) ? "a(n) " + item.getName() : item.getCount() + " " + item.getName() + "s to your inventory"));
            }
        }else if(nextCommand.contains("help") || nextCommand.equals("h")){
            if(nextCommand.equals("help")){
                System.out.println(Help.callHelp());
            }
        }else if(nextCommand.equals("save")){
            CharacterSaver.saveCharacter(player);
            System.out.println("You have saved your character");
        }else if(MovementHandler.isMovementCommand(nextCommand)){
            map.setCurrentRoom(MovementHandler.handleMovement(nextCommand, currentRoom, map));
            currentRoom = map.getCurrentRoom();
            System.out.println(currentRoom.toDisplay());
        }else if(nextCommand.equals("i") || nextCommand.equals("inventory")){
            System.out.println(player.getInventoryString());
        }else if(nextCommand.equals("sc") || nextCommand.equals("score")){
            System.out.println(player.getScoreString());
        }else{
            System.out.println("I'm not sure I understood you...");
        }
    }
    private List<Monster> getMonsterList(Map<String, Monster> monsterMap){
        List<Monster> monsterList = new ArrayList<>();
        for( Monster monster : monsterMap.values()){
            monsterList.add(monster);
        }
        return monsterList;
    }


}
