package main.CLIGame;

public class Player {
	int xp = 0;
	int attack = 1;
	int maxHp = 10;
	int currentHp = 10;
	int potions = 10;
	public int level = 1;
	String name;
	public Player(String name){
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
			throw new RuntimeException("Game Over, you have been defeated");
		}
	}
	public String toString(){
		return name + "|| HP: " + currentHp + " / " + maxHp + "||" + "Level: " + level + "||" + "Potions: " + potions;
	}
}
