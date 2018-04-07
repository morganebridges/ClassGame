package main.CLIGame;

import java.util.Scanner;

public class Game {
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("What is your name, player?");
		String name = in.next();
		Player player = new Player(name);
		Monster monster = null;
		while(true){
			System.out.println("*** *** *** *** *** *** *** ***");
			if(monster != null){
				System.out.println("You are in combat with a " + monster.name + "! {A}ttack or {H}eal");
				System.out.println("Enemy Status:" + monster.healthBar());
			}else{
				System.out.println("What would you like to do {H}eal, {E}xplore");
			}
			System.out.println("\n" + player.toString());
			String input = in.next();
			if(input.equals("A")|| input.equals("a") && monster != null){
				System.out.println("You attack");
				if(monster.attack(player)){
					monster = null;
				}
			}else if(input.equals("H") || input.equals("h")){
				player.heal();
			}else if(input.equals("E")){
				System.out.println("You explore the room");
				if(Math.random() * 100 > 50){
					System.out.println("You find a monster!");
					monster = new Monster("Goblin");
				}
			}
		}
		
	}
	
}
