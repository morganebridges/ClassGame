package main.java.com.javadventure.gamedriver.input;

import java.util.Scanner;

public enum ScannerIOSource implements EngineIOSource {
	INSTANCE;
	
	private Scanner in = new Scanner(System.in);
	
	@Override
	public void sendUserOutput(String message) {
		System.out.println(message);
	}

	@Override
	public String receiveUserInput() {
		String nextLine = in.nextLine();
		return nextLine;
	}
	
}
