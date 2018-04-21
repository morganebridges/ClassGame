package main.java.com.javadventure.gamedriver.combat;

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

    public void startCombat(Scanner in, List<Monster> monsterList, Map<String, Monster> monsterRoomMap, Player player, Room room, GameMap map){
        CombatThread thread = new CombatThread(monsterList, monsterRoomMap, player);
        thread.start();
        while(thread.combatContinue){
            String nextCommand = in.next();
            //We are trying to run away, lets check to see if we can go that direction
            if(MovementHandler.isMovementCommand(nextCommand)){
               Room attemptedTarget = MovementHandler.handleMovement(nextCommand, room, map);
               if(attemptedTarget.equals(room)){
                   System.out.println("You can't run that direction!");
               }else{
                   map.setCurrentRoom(attemptedTarget);
                   thread.flee();
               }
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
        CombatThread(List<Monster> monsterList, Map<String, Monster> monsterRoomMap, Player player){
            this.monsterRoomMap = monsterRoomMap;
            this.monsterList = monsterList;
            this.player = player;
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
                    System.out.println("You hit " + currentMonster.getName() + " for " + player.getAttack() + " damage!");
                    System.out.println(currentMonster.healthBar());
                    currentMonster.attack(player);
                    if(currentMonster.getCurrentHp() >= 0){
                        System.out.println( currentMonster.getName() + " hit you" + " for " + currentMonster.getAttack() + " damage!");
                        player.attack(currentMonster.getAttack());
                    }else{
                        System.out.println("You have defeated " + currentMonster.getName());
                        monsterRoomMap.remove(currentMonster.getName());
                        currentMonster = null;
                    }
                    System.out.println(player.toString());
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
                    System.out.println("You have been defeated");
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
