package main.java.com.javadventure.gamedriver;

import main.java.com.javadventure.gamedriver.utils.InputSanitizer;
import main.java.com.javadventure.monsters.Monster;
import main.java.com.javadventure.player.Player;

import java.util.Map;
import java.util.Scanner;

public class GameDriver {
	//isTerminated is a "Sentinal" value that we check to see if the player has decided to quit the game.
	public static boolean isTerminated = false;

	//A data structure for containing the active players in the game
	private static Map<String, Player> activePlayersMap;

	//An input stream to get input from the keyboard
	private static Scanner in = new Scanner(System.in);

	//A dictionary of valid commands
	private static CommandDictionary comDict = new CommandDictionary();

	//A utility for sanitizing text input
	private static InputSanitizer san = new InputSanitizer();
	public static void main(String[] args){
		System.out.println("What is your name, player?");
		String name = in.next();

		Player player = new Player(name);
		Monster monster = null;

	}
	private void mainLoop(){
			while(!isTerminated){

			}
	}
	private void loginLoop(){
		printSeperator();
		System.out.println("Welcome to JavAdventure!");
		while(activePlayersMap.size() == 0){
			System.out.println("Are you a {n}ew player or would you like to {l}oad a character");
			String command = san(in.next());

			if(command.equals("l")){
				System.out.println("What is your name?");
				try{
					Player player = CharacterLoader.loadOne(san(in.next()));
					activePlayersMap.put(player.getName(), player);
					continue;
				}catch(Exception e) {
					System.out.println("Was not able to load character, did you type your name correctly?");
				}
			}else if(command.equals("n")){
				
			}
		}
	}

	private static void printSeperator(){
		System.out.println("} }}  }}}__--^^^--___ -  -  - ___--^^^--__{{{   {{ {");
	}
	private static String san(String in){
		return san.san(in);
	}
		

	
}
