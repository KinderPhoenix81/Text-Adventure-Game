package textAdventure;

import java.util.ArrayList;
import java.util.Locale;

public class Player {

	//Fields for player
	private String name;
	private Inventory inventory = new Inventory();
	private int health;
	private static Room currentRoom;
	private Locale locale = new Locale("en", "US");
	private boolean isViewingInventory = false;
	
	//private Locale locale = new Locale("es", "SP");
	//Constructor
	public Player(String name) {
		//Sets properties of the player
		this.name = name;
		health = 100;
	}
	
	//Getters for the player
	public String getName() {
		return name;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public int getHealth() {
		return health;
	}
	
	public static Room getCurrentRoom() {
		return currentRoom;
	}
	
	public Locale getLocale()
	{
		return locale;
	}
	
	public boolean getIsViewingInventory() {
		return isViewingInventory;
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
	
	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}
	
	public void setLocale (Locale setLocale)
	{
		locale = setLocale;
	}
	
	public void setIsViewingInventory(boolean isViewing ) {
		isViewingInventory = isViewing;
	}
	
	public boolean hasItem(String itemName) {
		for (Item item : inventory.getAllInventory()) {
			if(item.getName().toUpperCase().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
}
