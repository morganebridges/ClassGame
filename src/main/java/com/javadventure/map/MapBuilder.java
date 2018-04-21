package main.java.com.javadventure.map;

import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.Items.weapons.IronSword;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.monsters.Monster;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {
    public static GameMap buildMap(){
        //Instantiate a new copy of the GameMap object
        GameMap map = new GameMap();

        //Here is where we construct some of our room objects. A handy "Builder" has been provided to make building the monsters easier
        Room loginRoom = new Room.Builder()
                .name("loginRoom")
                .description("The login room. This room is very non-descript. It has white walls, much like a canvass, upon which you can paint your wildest dreams")
                .eastExit("eastLogin1")
                .label("Login room")
                .build();

        //Create the builder for our room
        Room.Builder eastLoginBuilder = new Room.Builder()
                .name("eastLogin1")
                .description("The room directly east of the login room")
                .westExit("loginRoom")
                .label("Room east of login");

        //Create a collection to put our monsters in
        Map<String, Monster> eastLoginMonsters = new HashMap<>();

        //Build our monster
        Monster goblin = new Monster.Builder()
                .agro(false)
                .attack(1)
                .description("A furry goblin, he looks happy enough...")
                .hp(5)
                .name("Bob the Hobgoblin")
                .addLookWord("bob")
                .addLookWord("goblin")
                .addLookWord("monster")
                .build();

        //Add bob to our room
        eastLoginMonsters.put(goblin.getName(), goblin);
        eastLoginBuilder.monsters(eastLoginMonsters);
        //Add a map to represent our room items
        Map<String, Item> eastLoginItemMap = new HashMap<>();
        //How about an item for our room?
        IronSword sword = new IronSword();

        eastLoginItemMap.put(sword.getName(), sword);

        //Add the items to the room
        eastLoginBuilder.roomItems(eastLoginItemMap);

        //Build the room object
        Room eastLogin = eastLoginBuilder.build();

        //Add the rooms to our map
        map
            .addRoom(loginRoom)
            .addRoom(eastLogin)
            ;



        return map;

    }
}
