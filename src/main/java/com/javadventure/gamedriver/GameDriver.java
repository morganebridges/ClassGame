package main.java.com.javadventure.gamedriver;

import main.java.com.javadventure.gamedriver.commandHandlers.LoginHandler;
import main.java.com.javadventure.gamedriver.persistence.CharacterLoader;
import main.java.com.javadventure.gamedriver.persistence.CharacterSaver;
import main.java.com.javadventure.gamedriver.utils.InputSanitizer;
import main.java.com.javadventure.help.Help;
import main.java.com.javadventure.map.GameMap;
import main.java.com.javadventure.map.MapBuilder;
import main.java.com.javadventure.map.MovementHandler;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.monsters.Monster;
import main.java.com.javadventure.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameDriver {
	//isTerminated is a "Sentinal" value that we check to see if the player has decided to quit the game.
	public static boolean isTerminated = false;

	//A data structure for containing the active players in the game
	private static Map<String, Player> activePlayersMap = new HashMap<>();
	private static Player activePlayer;

	//An input stream to get input from the keyboard
	private static Scanner in = new Scanner(System.in);

	//A dictionary of valid commands
	private static CommandDictionary comDict = new CommandDictionary();

	//A utility for sanitizing text input
	private static InputSanitizer san = new InputSanitizer();

	//The game map
	private static GameMap map = MapBuilder.buildMap();

	//An object to encapsulate the handling of user commands
	private static CommandHandler cmdHandler = new CommandHandler();

	//Current room
	private static Room currentRoom = map.getRoomByName("loginRoom");
	public static void main(String[] args){
		//Pass the work to the login handler
		loginLoop();

	}
	private static void mainLoop(){
		while(!isTerminated){
			in = new Scanner(System.in);
			String nextCommand = san(in.nextLine());
			if(nextCommand.equals("q") || nextCommand.equals("quit")){
				System.out.println("Thank you for playing, " + activePlayer.getName());
				activePlayersMap.remove(activePlayer.getName());
				activePlayer = null;
				isTerminated = true;
			}else{
				cmdHandler.handleCommand(activePlayer, map, nextCommand, in);
			}
		}
	}
	private static void loginLoop(){
		printSeperator();
		System.out.println("Welcome to JavAdventure!");
		Player player;
		while((player = LoginHandler.login(in)) == null);
		activePlayersMap.put(player.getName(), player);
		activePlayer = player;
		map.setCurrentRoom(map.getRoomByName("loginRoom"));
		System.out.println(map.getCurrentRoom().toDisplay());
		mainLoop();
	}

	public static void printSeperator(){
		System.out.println("} }}  }}}__--^^^--___ -  -  - ___--^^^--__{{{   {{ {");
	}
	public  static String san(String in){
		return san.san(in);
	}
	//private static Player
		

	
}
