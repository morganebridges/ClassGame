package main.java.com.javadventure.map;

import main.java.com.javadventure.Items.Gold;
import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.Items.weapons.IronSword;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.monsters.Monster;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapBuilder {
    public static GameMap buildMap(){
        //Instantiate a new copy of the GameMap object
        GameMap map = new GameMap();

        //This item dictionary will help us to load items from disk, it will keep a String -> Item reference
        Map<String, Item> itemDictionary = new HashMap<>();

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
                .drops(Arrays.asList(new Gold(20), new IronSword()))
                .build();
        //Build our second monster
        Monster goblin2 = new Monster.Builder()
                .agro(false)
                .attack(1)
                .description("A furry goblin, he looks happy enough...")
                .hp(5)
                .name("Rob the Hobgoblin")
                .addLookWord("bob")
                .addLookWord("goblin")
                .addLookWord("monster")
                .drops(Arrays.asList(new Gold(20), new IronSword()))
                .build();

        //Add Bob to our room
        eastLoginMonsters.put(goblin.getName(), goblin);
        eastLoginMonsters.put(goblin2.getName(), goblin2);
        eastLoginBuilder.monsters(eastLoginMonsters);

        //Add a map to represent our room items
        Map<String, Item> eastLoginItemMap = new HashMap<>();

        //How about an item for our room?
        IronSword sword = new IronSword();
        Gold gold = new Gold(1);

        //Add sword to item dictionary
        itemDictionary.put(sword.getName(), sword);
        itemDictionary.put(gold.getName(), gold);

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

        map.setItemDictionary(itemDictionary);



        return map;

    }
}
