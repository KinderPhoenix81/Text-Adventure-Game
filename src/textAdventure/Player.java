package textAdventure;

import java.util.ArrayList;
import java.util.Locale;

public class Player {

	//Fields for player
	private String name;
	private Inventory inventory = new Inventory();
	private static Room currentRoom;
	private Locale locale = new Locale("en", "US");
	private boolean isViewingInventory = false;
	
	//private Locale locale = new Locale("es", "SP");
	//Constructor
	public Player(String name) {
		//Sets properties of the player
		this.name = name;
	}
	
	//Getters for the player
	public String getName() {
		return name;
	}
	
	public Inventory getInventory() {
		return inventory;
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
	
}
