package main.java.com.javadventure.help;

public class Help {

    public static String callHelp(){
        String helpString = "";
        String stars = "\n***************************************";

        helpString += stars;
        helpString += "\n--- Commands ---";
        helpString += "\nLook : 'look' or 'l'";
        helpString += "\nLook at an item: 'look at <item name>'...ex: 'look at sword'";
        helpString += "\nAttack : 'fight' or 'f'";
        helpString += "\nSearch an item / area : 'search ' + <item name> ...ex:'search tree' ";
        helpString += "\nTake an item: 'take' + <item name> ...ex: 'take sword'";
        helpString += "\nQuit: 'quit' or 'q'";
        helpString += "\nMovement: 'n' 's' 'e' 'w' for room exits to the north, south, east, and west";
        helpString += stars + "\n\n";
        return helpString;
    }
}
