package main.java.com.javadventure.gamedriver.commandHandlers;

import main.java.com.javadventure.gamedriver.GameObject;

public interface CommandHandler {
    String executeCommand(GameObject object);
}
