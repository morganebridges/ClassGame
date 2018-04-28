package main.java.com.javadventure.player;

import com.sun.istack.internal.Nullable;
import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.Items.Weapon;
import main.java.com.javadventure.gamedriver.GameObject;
import main.java.com.javadventure.map.GameMap;

import java.util.HashMap;
import java.util.Map;

public class Player extends GameObject {
	private int xp = 0;
	private int attack = 1;
	private int defense = 1;
	private int maxHp = 10;
	private int currentHp = 10;
	private int potions = 10;
	private int level = 1;
	String name;
	String description = "A player";
	Weapon currentWeapon;

	private static final String stars = "\n**************";
	Map<String, Item> itemMap = new HashMap<>();

	//TODO fix description string;
	public Player(String name){
		this.name = name;
	}

	public Player(int xp, int attack, int defense, int maxHp, int currentHp, int potions, int level, String name) {
		this.xp = xp;
		this.attack = attack;
		this.defense = defense;
		this.maxHp = maxHp;
		this.currentHp = currentHp;
		this.potions = potions;
		this.level = level;
		this.name = name;

	}

	public void wield(String name){
		for(Map.Entry<String, Item> item :itemMap.entrySet()){
			if(item.getValue().isWieldable()){
				Weapon weapon = (Weapon) item.getValue();
				if(weapon.getLookList().contains(name)){
					if(this.currentWeapon == null){
						this.currentWeapon = weapon;
						removeItem(weapon.getName());

					}else{
						removeItem(weapon.getName());
						addItem(currentWeapon);
						currentWeapon = weapon;

					}
					System.out.println("You wield " + weapon.getName());
				}else{
					System.out.println("I couldn't find the weapon you were looking for");
				}

			}
		}
	}
	public void defeatMonster(int xp){
		this.xp += xp;
		if(this.xp / 10 >= level){
			levelUp();
		}
	}
	public void levelUp(){
		maxHp += 2;
		currentHp +=2;
		attack++;
		level ++;
		System.out.println("Congratulations, you have reached level " + level + "!");
	}
	public void heal(){
		if(potions > 0 && currentHp < maxHp){
			System.out.println("You heal yourself with a potion");
			potions--;
			if(currentHp + 3 <= maxHp){
				currentHp += 3;
			}else{
				currentHp = maxHp;
			}
		}else{
			System.out.println("You are already at max health!");
		}
		
	}
	public void attack(int attack){
		currentHp -= attack;
		if(currentHp <= 0){
			throw new RuntimeException("GameDriver Over, you have been defeated");
		}
	}
	public @Nullable Weapon getCurrentWeapon(){
		return currentWeapon;
	}

	public void addItem(Item item){
		if(itemMap.containsKey(item.getName())){
			itemMap.get(item.getName()).increaseBy(item.getCount());

		}else{
			itemMap.put(item.getName(),  item);
		}
	}
	public void removeItem(String itemName){
		Item item;
		if((item = itemMap.get(itemName)) != null){
			//If we have more than one of an item, decrement, otherwise, remove the item
			if(item.getCount()  > 0){
				item.decrementCount();
			}else{
				itemMap.remove(itemName);
			}
		}
	}

	public String getInventoryString(){
		String inv = stars;
		if(itemMap.size() != 0) {
			for (Map.Entry<String, Item> itemEntry : itemMap.entrySet()) {
				Item item = itemEntry.getValue();
				inv += "\n" + itemEntry.getValue().getName() + (item.getCount() > 1 ? "(" + item.getCount() + ")" : "");
			}
			if(currentWeapon != null){
				inv += "\n" + currentWeapon.getName() + "(wielded)";
			}
		}else{
			inv += "\nYou currently have no items in your inventory";
		}
		inv += stars;
		return inv;
	}
	public String getScoreString(){
		String score = stars;
		score += "\nLevel: " + level;
		score += "\nXP: " + xp;
		score += "\nAttack power:" + (attack + (currentWeapon != null ? currentWeapon.getDamage(): 0));
		return score;
	}
	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}

	public int getPotions() {
		return potions;
	}

	public void setPotions(int potions) {
		this.potions = potions;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void drinkPotion(){
		if(potions > 0){
			if((currentHp + 5) < maxHp){
				currentHp = maxHp;
			}else{
				currentHp += 5;
			}
			potions--;
			System.out.println("You drink a potion");
		}else{
			System.out.println("You have no potions to drink");
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		return name + "|| HP: " + currentHp + " / " + maxHp + "||" + "Level: " + level + "||" + "Potions: " + potions;
	}
	public String toSaveFormatting(){
		String saveString = name + ","+ level + "," + currentHp +"," + maxHp + "," + potions + "," + xp + "," + description + "," + attack + "," + defense + "<>";
		String itemString = "";
		for(Map.Entry<String, Item> itemEntry : itemMap.entrySet()){
			itemString += itemEntry.getValue().toSaveString();
		}

		saveString += itemString;
		return  saveString;
	}

}
