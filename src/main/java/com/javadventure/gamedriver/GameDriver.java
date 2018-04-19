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
	private static Player player1;

	//An input stream to get input from the keyboard
	private static Scanner in = new Scanner(System.in);

	//A dictionary of valid commands
	private static CommandDictionary comDict = new CommandDictionary();

	//A utility for sanitizing text input
	private static InputSanitizer san = new InputSanitizer();

	//The game map
	private static GameMap map = MapBuilder.buildMap();

	//Current room
	private static Room currentRoom = map.getRoomByName("loginRoom");
	public static void main(String[] args){
		//Pass the work to the login handler
		loginLoop();

	}
	private static void mainLoop(){
			while(!isTerminated){
				System.out.println(player1.toString());
				String nextCommand = san(in.next());

				if(nextCommand.equals("q") || nextCommand.equals("quit")){
					System.out.println("Thank you for playing, " + player1.getName());
					activePlayersMap.remove(player1.getName());
					player1 = null;
					loginLoop();
				}else if(nextCommand.equals("look") || nextCommand.equals("l")){
					if(currentRoom != null){
						System.out.println(currentRoom.toDisplay());
					}
				}else if(nextCommand.contains("fight")){

				}else if(nextCommand.contains("search")){

				}else if(nextCommand.contains("help") || nextCommand.equals("h")){
					if(nextCommand.equals("help")){
						System.out.println(Help.callHelp());
					}
				}else if(nextCommand.equals("save")){
					CharacterSaver.saveCharacter(player1);
				}else if(MovementHandler.isMovementCommand(nextCommand)){
						currentRoom = MovementHandler.handleMovement(nextCommand, currentRoom, map);
						System.out.println(currentRoom.toDisplay());
				}else{
					System.out.println("I'm not sure I understood you...");
				}
			}
	}
	private static void loginLoop(){
		printSeperator();
		System.out.println("Welcome to JavAdventure!");
		Player player;
		while((player = LoginHandler.login(in)) == null);
		activePlayersMap.put(player.getName(), player);
		player1 = player;
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
