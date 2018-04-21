package main.java.com.javadventure.gamedriver.utils;

import main.java.com.javadventure.player.Player;

public class WeaponWielder {
    public static void wield(Player player, String command){
        String[] args =command.split(" ");
        if(args.length == 2){
            player.wield(args[1]);
        }else{
            String weaponName = "";
            if(args[0].equals("wield")){
                for(int i = 1; i < args.length -1; i++){
                    if(i == 1){
                        weaponName += args[i];
                    }else{
                        weaponName += " " + args[i];
                    }
                }
            }
        }
    }
}
