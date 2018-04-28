package main.java.com.javadventure.gamedriver.input;

public interface EngineIOSource {
	void sendUserOutput(String message); 
	String receiveUserInput();
}
