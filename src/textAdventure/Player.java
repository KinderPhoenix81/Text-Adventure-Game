package textAdventure;

import java.util.ArrayList;

public class Player {

	//Fields for player
	private String name;
	private ArrayList<Item> inventory;
	private int health;
	
	//Constructor
	public Player(String name) {
		this.name = name;
		inventory = new ArrayList<Item>();
		health = 100;
	}
	
	//Getters for the player
	public String getName() {
		return name;
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public int getHealth() {
		return health;
	}
	
	//Setters for player fields
	public void setHealth(int newHealthValue) {
		health = newHealthValue;
	}
	
	public void decrementHealth(int damage) {
		health -= damage;
	}
	
	public void recoverHealth(int healing) {
		health += healing;
	}
	
}
