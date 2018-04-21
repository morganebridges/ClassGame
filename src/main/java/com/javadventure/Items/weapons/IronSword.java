package main.java.com.javadventure.Items.weapons;

import main.java.com.javadventure.Items.Weapon;

public class IronSword extends Weapon {
    public IronSword(){
        super("Iron Sword");
        this.damage = 2;
        this.value = 10;
        this.count = 1;
        this.description = "A somewhat rusty Iron sword.";
        this.lookList.add("sword");
        this.lookList.add("blade");
        this.lookList.add("knife");
    }
}
