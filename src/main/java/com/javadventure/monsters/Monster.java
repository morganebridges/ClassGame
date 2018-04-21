package main.java.com.javadventure.monsters;

import main.java.com.javadventure.gamedriver.GameObject;
import main.java.com.javadventure.player.Player;

public class Monster extends GameObject {
	protected int maxHp = 5;
	protected int currentHp = 5;
	protected int attack = 1;
	protected boolean agro = false;

	protected String description = "";
	protected String name;

	
	public Monster(String name){
		this.name = name;
	}

	public boolean attack(Player player){
		currentHp -= player.getCurrentHp();
		if(currentHp <= 0){
			System.out.println("You defeat the monster");
			player.defeatMonster();
			return true;
		}else{
			System.out.println("You hit the monster for " + player.getAttack() + " damage");
			return false;
		}
	}
	public Monster(String name, int maxHp, int attack, String description, boolean isAgro){
		this.agro = isAgro;
		this.name = name;
		this.maxHp = maxHp;
		this.attack = attack;
		this.description = description;

	}
	public String healthBar(){
		String bar = "[";
		for(int i = 0 ; i < maxHp; i++){
			if(currentHp == i){
				bar += "|";
			}else{
				bar += "-";
			}
		}
		bar += "]";
		return bar;
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

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public boolean isAgro() {
		return agro;
	}

	public void setAgro(boolean agro) {
		this.agro = agro;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static class Builder {
		int maxHp;
		String name;
		int attack;
		String description;
		boolean agro;

		public Builder name(String name){
			this.name = name;
			return this;
		}
		public Builder hp(int maxHp){
			this.maxHp = maxHp;
			return this;
		}
		public Builder attack(int attack){
			this.attack = attack;
			return this;
		}
		public Builder description(String description){
			this.description = description;
			return this;
		}

		public Builder agro(boolean isAgro){
			this.agro = isAgro;
			return this;
		}
		public Monster build(){
			return new Monster(name, maxHp, attack, description, agro);
		}

	}

}
