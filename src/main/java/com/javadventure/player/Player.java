package main.java.com.javadventure.player;

import main.java.com.javadventure.gamedriver.GameObject;

public class Player extends GameObject {
	private int xp = 0;
	private int attack = 1;
	private int maxHp = 10;
	private int currentHp = 10;
	private int potions = 10;
	private int level = 1;
	String name;
	public Player(String name){
		this.name = name;
	}

	public Player(int xp, int attack, int maxHp, int currentHp, int potions, int level, String name) {
		this.xp = xp;
		this.attack = attack;
		this.maxHp = maxHp;
		this.currentHp = currentHp;
		this.potions = potions;
		this.level = level;
		this.name = name;
	}

	public void defeatMonster(){
		xp++;
		if(xp % 5 == 0){
			levelUp();
		}
	}
	public void levelUp(){
		maxHp += 2;
		currentHp +=2;
		attack++;
		level ++;
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

	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		return name + "|| HP: " + currentHp + " / " + maxHp + "||" + "Level: " + level + "||" + "Potions: " + potions;
	}

}
