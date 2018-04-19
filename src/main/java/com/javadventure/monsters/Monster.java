package main.java.com.javadventure.monsters;

import main.java.com.javadventure.gamedriver.GameObject;
import main.java.com.javadventure.player.Player;

public class Monster extends GameObject {
	public int maxHp = 5;
	public int currentHp = 5;
	public String name;
	public int attack = 1;

	
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
}
