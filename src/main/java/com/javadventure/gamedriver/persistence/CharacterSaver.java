package main.java.com.javadventure.gamedriver.persistence;

import main.java.com.javadventure.player.Player;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CharacterSaver {


    public static void saveCharacter(Player player){
        if(CharacterLoader.characterExists(player.getName())){
            replaceCharacter(player);
        }else{
            createCharacter(player);
        }
    }
    public static boolean createCharacter(Player player){
        Path gamePath = Paths.get("game.save");
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(gamePath, StandardCharsets.UTF_8));
            fileContent.add(player.toSaveFormatting());
            Files.write(gamePath, fileContent, StandardCharsets.UTF_8);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not save Character");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean replaceCharacter(Player player){
        Path gamePath = Paths.get("game.save");
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(gamePath, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).contains(player.getName())) {
                    fileContent.set(i, player.toSaveFormatting());
                    break;
                }
            }
            Files.write(gamePath, fileContent, StandardCharsets.UTF_8);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not save Character");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
