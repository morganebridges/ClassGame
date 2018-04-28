package main.java.com.javadventure.gamedriver.input;

import java.util.Arrays;
import java.util.List;

public class InputIdentifier {
	private List<String> lookList = Arrays.asList("look", "l");
	private List<String> fightList = Arrays.asList("fight", "kill", "attack");
	private List<String> takeList = Arrays.asList("take", "get");
	private List<String> helpList = Arrays.asList("help", "h");
	private List<String> wieldList = Arrays.asList("wield");
	private List<String> wearList = Arrays.asList("wear");
	private List<String> saveList = Arrays.asList("save");
	
	public boolean isSaveCommand(String nextCommand){
		return isCommand(nextCommand, saveList);
	}
	
	public boolean isHelpCommand(String nextCommand){
		return isCommand(nextCommand, helpList);
	}

	
	public boolean isFightCommand(String nextCommand){
		return isCommand(nextCommand, fightList);
	}
	
	public boolean isLookCommand(String nextCommand){
		return isCommand(nextCommand, lookList);
	}
	

	public boolean isLookAtCommand(String nextCommand){
		return nextCommand.contains("at");
	}
	
	public boolean isTakeCommand(String nextCommand){
		return isCommand(nextCommand, takeList);
	}
	
	public boolean isWieldCommand(String nextCommand){
		return isCommand(nextCommand, wieldList);
	}
	public boolean isWearCommand(String nextCommand){
		return isCommand(nextCommand, wearList);
	}
	
	private boolean isCommand(String nextCommand, List<String> cmdList){
		return cmdList.contains(nextCommand.split(" ")[0]);
	}
}
