package main.java.com.javadventure.Items;

import main.java.com.javadventure.Items.Item;

public abstract class Weapon extends Item {
    protected int damage = 1;
    public Weapon(String name){
        super(name);
        this.wearable = false;
        this.wieldable = true;
    }
    public Weapon(String name, String description, int damage){
        super(name);
        this.wearable = false;
        this.wieldable = true;
        this.description = description;
        this.damage = damage;

    }
    public int getDamage(){
        return damage;
    }

}
