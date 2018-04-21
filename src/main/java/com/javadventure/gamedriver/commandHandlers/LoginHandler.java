package main.java.com.javadventure.gamedriver.commandHandlers;

import main.java.com.javadventure.gamedriver.GameDriver;
import main.java.com.javadventure.gamedriver.persistence.CharacterLoader;
import main.java.com.javadventure.gamedriver.persistence.CharacterSaver;
import main.java.com.javadventure.map.GameMap;
import main.java.com.javadventure.player.Player;

import java.util.Scanner;

public class LoginHandler {
    public static Player login(Scanner in, GameMap map){
        System.out.println("Are you a {n}ew player or would you like to {l}oad a character");
        String command = GameDriver.san(in.next());

        if(command.equals("l")){
            System.out.println("What is your name?");
            try{
                return CharacterLoader.loadOne(in.next().trim(), map);
            }catch(Exception e) {
                System.out.println("I was not able to load character, did you type your name correctly?");
            }
        }else if(command.equals("n")){
            System.out.println("What is your name?");
            String name = in.next().trim();
            if(!CharacterLoader.characterExists(name)){
                Player player = new Player(name);
                CharacterSaver.createCharacter(player);
                return player;
            }else{
                System.out.println("This player already exists, try logging in instead");
            }

        }else if(command.equals("q") || command.equals("quit")){
            System.exit(0);
        }
        return null;
    }
}
