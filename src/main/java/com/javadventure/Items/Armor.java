package main.java.com.javadventure.Items;

import main.java.com.javadventure.Items.Item;

public abstract class Armor extends Item {
    int armorClass;
    public Armor(String name){
        super(name);
        this.wearable = true;
        this.wieldable = false;
    }
}
