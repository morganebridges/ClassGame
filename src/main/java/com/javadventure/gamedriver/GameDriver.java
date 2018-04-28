package main.java.com.javadventure.gamedriver;

import main.java.com.javadventure.gamedriver.commandHandlers.LoginHandler;
import main.java.com.javadventure.gamedriver.input.ScannerIOSource;
import main.java.com.javadventure.gamedriver.input.InputSanitizer;
import main.java.com.javadventure.map.GameMap;
import main.java.com.javadventure.map.MapBuilder;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameDriver {
	//isTerminated is a "Sentinel" value that we check to see if the player has decided to quit the game.
	public static boolean isTerminated = false;

	//A data structure for containing the active players in the game
	private static Map<String, Player> activePlayersMap = new HashMap<>();
	private static Player activePlayer;

	//An input stream to get input from the keyboard
	private static Scanner in = new Scanner(System.in);

	//A utility for sanitizing text input
	private static InputSanitizer san = new InputSanitizer();

	//The game map
	private static GameMap map = MapBuilder.buildMap();

	//An object to encapsulate the handling of user commands
	private static CommandHandler cmdHandler = new CommandHandler(ScannerIOSource.INSTANCE);

	//Current room
	private static Room currentRoom = map.getRoomByName("loginRoom");

	/**
	 * This is the main method of our application, it is the runnable application.
	 * @param args
	 */
	public static void main(String[] args){
		//Pass the work to the login handler
		loginLoop();

	}
	private static void mainLoop(){
		while(!isTerminated){
			String nextCommand = san(ScannerIOSource.INSTANCE.receiveUserInput());
			if(nextCommand.equals("q") || nextCommand.equals("quit")){
				ScannerIOSource.INSTANCE.sendUserOutput("Thank you for playing, " + activePlayer.getName());
				activePlayersMap.remove(activePlayer.getName());
				activePlayer = null;
				isTerminated = true;
			}else{
				cmdHandler.handleCommand(activePlayer, map, nextCommand, in);
			}
		}
	}
	private static void loginLoop(){
		ScannerIOSource.INSTANCE.sendUserOutput(printSeperator());
		ScannerIOSource.INSTANCE.sendUserOutput("Welcome to JavAdventure!");
		Player player;

		//We need to run the login loop until the player has logged in
		while((player = LoginHandler.login(in, map)) == null);

		activePlayersMap.put(player.getName(), player);
		activePlayer = player;
		map.setCurrentRoom(map.getRoomByName("loginRoom"));
		ScannerIOSource.INSTANCE.sendUserOutput(map.getCurrentRoom().toDisplay());
		mainLoop();
	}

	public static String printSeperator(){
		return "} }}  }}}__--^^^--___ -  -  - ___--^^^--__{{{   {{ {";
	}
	public static String san(String in){
		return san.san(in);
	}
	//private static Player
		

	
}
