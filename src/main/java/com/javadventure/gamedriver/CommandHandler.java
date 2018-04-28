package main.java.com.javadventure.gamedriver;

import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.gamedriver.combat.CombatHandler;
import main.java.com.javadventure.gamedriver.input.EngineIOSource;
import main.java.com.javadventure.gamedriver.input.ScannerIOSource;
import main.java.com.javadventure.gamedriver.persistence.CharacterSaver;
import main.java.com.javadventure.gamedriver.input.InputIdentifier;
import main.java.com.javadventure.gamedriver.input.InputSanitizer;
import main.java.com.javadventure.gamedriver.utils.ItemTaker;
import main.java.com.javadventure.gamedriver.utils.RoomLooker;
import main.java.com.javadventure.gamedriver.utils.WeaponWielder;
import main.java.com.javadventure.help.Help;
import main.java.com.javadventure.map.GameMap;
import main.java.com.javadventure.map.MovementHandler;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.monsters.Monster;
import main.java.com.javadventure.player.Player;

import java.util.*;

public class CommandHandler {
    private static InputSanitizer san = new InputSanitizer();
    private static InputIdentifier inIder = new InputIdentifier();
    private EngineIOSource ioSource;

    public CommandHandler(EngineIOSource ioSource){
        this.ioSource = ioSource;
    }

    public void handleCommand(Player player, GameMap map, String nextCommand, Scanner in){
        Room currentRoom = map.getCurrentRoom();
        System.out.println(player.toString());


        if(inIder.isLookCommand(nextCommand)){
            if(inIder.isLookAtCommand(nextCommand)){
                ioSource.sendUserOutput(RoomLooker.lookAtThing(nextCommand, currentRoom));
            }else if(currentRoom != null){
                ioSource.sendUserOutput(currentRoom.toDisplay());
            }
        }else if(inIder.isFightCommand(nextCommand)){
            //If we don't have any room monsters, don't start combat.
            if(currentRoom.getRoomMonsters() == null){
                ScannerIOSource.INSTANCE.sendUserOutput("There are no monsters in this room.");
                return;
            }
            CombatHandler handler = new CombatHandler();
            handler.startCombat(in, getMonsterList(currentRoom.getRoomMonsters()), currentRoom.getRoomMonsters(), player, currentRoom, map);
        }else if(nextCommand.contains("search")){

        }else if(inIder.isTakeCommand(nextCommand)){
            if(nextCommand.equals("take all")){
                ItemTaker.takeAll(currentRoom, player);
            }
            Item item = ItemTaker.takeItem(nextCommand, currentRoom);
            if(item != null){
                player.addItem(item);
                currentRoom.getRoomItems().remove(item.getName());
                System.out.println("You have added " + item.getName() + " to your inventory");
            }
        }else if(inIder.isHelpCommand(nextCommand)){
            ioSource.sendUserOutput(Help.callHelp());
        }else if(inIder.isWieldCommand(nextCommand)){
            WeaponWielder.wield(player, nextCommand);
        }else if(inIder.isSaveCommand(nextCommand)){
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
        }else if(nextCommand.equals("drink") ||  nextCommand.equals("dr")){
            player.drinkPotion();
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
