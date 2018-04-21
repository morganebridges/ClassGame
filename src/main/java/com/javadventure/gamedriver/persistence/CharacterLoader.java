package main.java.com.javadventure.gamedriver.persistence;

import main.java.com.javadventure.player.Player;

import java.io.*;

/**
 * A class that has the ability to load a character from a flat file.
 *
 * The format of a player on disk is as follows:
 * name|level|curHp|maxHp|potions|xp|description|attack
 *
 */
public class CharacterLoader {

    public static boolean characterExists(String charName){
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader("game.save"));
            String currentLine;
            while((currentLine = inputStream.readLine()) != null){
                if(currentLine.contains(charName)){
                   return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not load Character");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not load Character");
            e.printStackTrace();
        }
        return false;
    }

    public static Player loadOne(String charName) {
        try {
            if(characterExists(charName)){
                BufferedReader inputStream = new BufferedReader(new FileReader("game.save"));
                String currentLine;
                while((currentLine = inputStream.readLine()) != null){
                    if(currentLine.contains(charName)){
                        String[] charProfile = currentLine.split(",");
                        return parseRecord(charProfile);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            System.out.println("Could not load Character");
        } catch (IOException e) {
            System.out.println("Could not load Character");
        }
        throw new RuntimeException("Unable to load character");
    }
    private static Player parseRecord(String[] array){
        try{
            String name = array[0];
            int level = Integer.parseInt(array[1]);
            int curHp = Integer.parseInt(array[2]);
            int maxHp = Integer.parseInt(array[3]);
            int potions = Integer.parseInt(array[4]);
            int xp = Integer.parseInt(array[5]);
            String description = array[6];
            int attack = Integer.parseInt(array[7]);
            int defense = Integer.parseInt(array[8]);
            return new Player(xp, attack, defense, maxHp, curHp, potions, level, name);
        }catch(NumberFormatException e){
            throw new NumberFormatException("Error parsing player record");

        }
    }
    public static void main(String[] args){
        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory : " + workingDir);
        Player player = loadOne("name");
        System.out.println(loadOne("name"));
    }
}
