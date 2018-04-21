package main.java.com.javadventure.Items;

import main.java.com.javadventure.Items.Item;

public abstract class Weapon extends Item {
    protected int damage;
    public Weapon(String name){
        super(name);
        this.wearable = false;
        this.wieldable = true;
    }
}
