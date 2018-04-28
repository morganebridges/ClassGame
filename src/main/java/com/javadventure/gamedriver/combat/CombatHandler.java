package main.java.com.javadventure.gamedriver.combat;

import main.java.com.javadventure.gamedriver.CommandHandler;
import main.java.com.javadventure.gamedriver.input.ScannerIOSource;
import main.java.com.javadventure.map.GameMap;
import main.java.com.javadventure.map.MovementHandler;
import main.java.com.javadventure.map.rooms.MovementDirections;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.monsters.Monster;
import main.java.com.javadventure.player.Player;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CombatHandler {
    CommandHandler cmdHandler;
    public void startCombat(Scanner in, List<Monster> monsterList, Map<String, Monster> monsterRoomMap, Player player, Room room, GameMap map){
        this.cmdHandler = new CommandHandler(ScannerIOSource.INSTANCE);
        CombatThread thread = new CombatThread(monsterList, monsterRoomMap, player, room);
        thread.start();
        while(thread.combatContinue){
            String nextCommand = in.next();
            //We are trying to run away, lets check to see if we can go that direction
            if(MovementHandler.isMovementCommand(nextCommand)){
               Room attemptedTarget = MovementHandler.handleMovement(nextCommand, room, map);
               if(attemptedTarget.equals(room)){
                   ScannerIOSource.INSTANCE.sendUserOutput(("You can't run that direction!"));
               }else{
                   ScannerIOSource.INSTANCE.sendUserOutput(("You flee to the " + MovementDirections.getByCommand(nextCommand)));
                   map.setCurrentRoom(attemptedTarget);
                   thread.flee();
               }
            }else{
                cmdHandler.handleCommand(player, map, nextCommand, in);
            }
            try {
                //Wait before checking the scanner for a new input
                Thread.sleep(120L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private class CombatThread extends Thread {
        List<Monster> monsterList;
        Player player;
        Monster currentMonster;
        Map<String, Monster> monsterRoomMap;
        Room currentRoom;
        CombatThread(List<Monster> monsterList, Map<String, Monster> monsterRoomMap, Player player, Room room){
            this.monsterRoomMap = monsterRoomMap;
            this.monsterList = monsterList;
            this.player = player;
            this.currentRoom = room;
        }
        boolean combatContinue = true;
        Duration roundTime = Duration.ofSeconds(1);
        @Override
        public void run(){
            while(combatContinue){
                try {
                    if(currentMonster == null){
                        currentMonster = monsterList.remove(0);
                    }
                    ScannerIOSource.INSTANCE.sendUserOutput(("You hit " + currentMonster.getName() + " for " + player.getAttack() + " damage!"));
                    ScannerIOSource.INSTANCE.sendUserOutput((currentMonster.healthBar()));
                    currentMonster.attack(player, currentRoom);
                    if(currentMonster.getCurrentHp() >= 0){
                        ScannerIOSource.INSTANCE.sendUserOutput(( currentMonster.getName() + " hit you" + " for " + currentMonster.getAttack() + " damage!"));
                        player.attack(currentMonster.getAttack());
                    }else{
                        ScannerIOSource.INSTANCE.sendUserOutput(("You have defeated " + currentMonster.getName()));
                        monsterRoomMap.remove(currentMonster.getName());
                        currentMonster = null;
                    }
                    ScannerIOSource.INSTANCE.sendUserOutput((player.toString()));
                    sleep(roundTime.toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //No monsters left to fight
                if(currentMonster == null && monsterList.size() == 0){
                    combatContinue = false;
                }
                if(player.getCurrentHp() <= 0){
                    combatContinue = false;
                    ScannerIOSource.INSTANCE.sendUserOutput(("You have been defeated"));
                    System.exit(0);
                }
            }
        }
        private void processRound(){
            for(Monster monster : monsterList){

            }
        }
        public void flee(){
            combatContinue = false;
        }
        public boolean doesCombatContinue(){
            return combatContinue;
        }
    }
}
