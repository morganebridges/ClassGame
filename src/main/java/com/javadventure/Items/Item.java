package main.java.com.javadventure.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Item {
    protected String name;
    protected String description;
    protected boolean consumable;
    protected boolean wearable;
    protected boolean wieldable;
    protected int count;
    protected int value;

    //A list of words that we will accept from the user that they are looking at this item
    protected List<String> lookList = new ArrayList<>();

    public Item(String name, String description, boolean consumable, boolean wearable, boolean wieldable, int count, int value) {
        lookList.add(name);
        this.name = name;
        this.description = description;
        this.consumable = consumable;
        this.wearable = wearable;
        this.wieldable = wieldable;
        this.count = count;
        this.value = value;
    }
    public Item(String name){
        lookList.add(name);
        this.name = name;
    }


    public boolean isWearable(){
        return wearable;
    }
    public boolean isWieldable(){
        return wieldable;
    }
    public int getCount(){
        return count;
    }
    public int getValue(){
        return value;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getLookList(){
        return lookList;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void increaseBy(int number){
        count += number;
    }
    public void decrementCount(){
        if(count < 0){
            count = count--;
        }
    }
    public String toSaveString(){
        return name + "," + count + "|";
    }

    public void incrementCount(){
        count += 1;
    }



    public static class Builder{
        String name;
        String description;
        boolean consumable;
        boolean wearable;
        boolean wieldable;
        int count;
        int value;
        List<String> lookList = new ArrayList<>();
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder description(String name){
            this.description = description;
            return this;
        }
        public Builder consumable(boolean consumable){
            this.consumable = consumable;
            return this;
        }

        public Builder wearable(boolean wearable){
            this.wearable = wearable;
            return this;
        }
        public Builder wieldable(boolean wieldable){
            this.wieldable = wieldable;
            return this;
        }

        public Builder count(int count){
            this.count = count;
            return this;
        }
        public Builder value(int value){
            this.value = value;
            return this;
        }
        public Builder isWeapon(){
            this.wearable = false;
            this.wieldable = true;
            return this;
        }

        public Builder addLookWord(String lookWord){
            lookList.add(lookWord);
            return this;
        }
        public Item build(){
            Item item = new Item(name, description, consumable, wearable, wieldable, count, value);
            item.getLookList().addAll(lookList);
            return item;
        }



    }
}
