package textAdventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Player {

	//Fields for player
	private String name;
	private Inventory inventory = new Inventory();
	private static Room currentRoom;
	private Locale locale = new Locale("en", "US");
	private boolean isViewingInventory = false;
	private Map<String, Boolean> quests;
	
	//private Locale locale = new Locale("es", "SP");
	//Constructor
	public Player(String name) {
		//Sets properties of the player
		this.name = name;
		this.quests = new HashMap<>();
		this.quests.put("Totem Quest", false);
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
	
	public boolean hasItem(String itemName) {
		for (Item item : inventory.getAllInventory()) {
			if(item.getName().toUpperCase().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean completedQuest(String questName) {
		if(this.quests.containsKey(questName)) {
			return this.quests.get(questName);
		}		
		
		return false;
	}
	
	public void setQuestComplete(String questName) {
		this.quests.put(questName, true);
	}
	
}
