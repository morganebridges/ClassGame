package main.java.com.javadventure.monsters;

import main.java.com.javadventure.Items.Item;
import main.java.com.javadventure.gamedriver.GameObject;
import main.java.com.javadventure.map.rooms.Room;
import main.java.com.javadventure.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Monster extends GameObject {
	protected int maxHp = 5;
	protected int currentHp = 5;
	protected int attack = 1;
	protected boolean agro = false;
	protected int xpValue = 2;
	protected int gold = 2;

	protected String description = "";
	protected String name;
	protected List<String> lookList = new ArrayList<>();
	protected List<Item> drops = new ArrayList<>();

	public Monster(String name){
		this.name = name;
	}

	public boolean attack(Player player, Room room){
		currentHp -= player.getAttack();
		if(currentHp <= 0){
			//Add all of the monsters items
			player.defeatMonster(xpValue);
			for (Item drop : drops) {
				room.addRoomItem(drop);
			}
			return true;
		}else{
			return false;
		}
	}
	public Monster(String name, int maxHp, int attack, String description, boolean isAgro, List<Item> drops){
		this.drops = drops;
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

	public void addLookWord(String lookWord){
		lookList.add(lookWord);
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLookList(){
		return lookList;
	}
	public List<Item> getDrops(){return drops;}

	public static class Builder {
		int maxHp;
		String name;
		int attack;
		String description;
		boolean agro;
		List<String> lookList = new ArrayList<>();
		List<Item> drops;

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
		public Builder addLookWord(String lookWord){
			lookList.add(lookWord);
			return this;
		}
		public Builder drops(List<Item> drops){
			this.drops = drops;
			return this;
		}
		public Monster build(){
			Monster monster = new Monster(name, maxHp, attack, description, agro, drops);
			monster.getLookList().addAll(lookList);
			return monster;
		}

	}

}
