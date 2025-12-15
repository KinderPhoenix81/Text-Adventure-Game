package textAdventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Class for managing the player
 */
public class Player {

	/**
	 * Fields the player has
	 * 
	 * name: Player name
	 * inventory: Player inventory
	 * currentRoom: Room the player is in
	 * locale: Localization key
	 * isViewingInventory: Whether the player is viewing the inventory
	 * quests: puzzles for the player to complete
	 */
	//Fields for player
	private String name;
	private Inventory inventory = new Inventory();
	private static Room currentRoom;
	private Locale locale = new Locale("en", "US");
	private boolean isViewingInventory = false;
	private Map<String, Boolean> quests;
	private boolean hasLitTorch = false;
	
	/**
	 * Constructor for Player
	 * 
	 * @param name Player name
	 */
	//private Locale locale = new Locale("es", "SP");
	//Constructor
	public Player(String name) {
		//Sets properties of the player
		this.name = name;
		this.quests = new HashMap<>();
		this.quests.put("Totem Quest", false);
	}
	
	/**
	 * Getter for player name
	 * 
	 * @return player name
	 */
	//Getters for the player
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for player inventory
	 * 
	 * @return player inventory
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Getter for current room
	 * 
	 * @return current room
	 */
	public static Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * Getter for locale
	 * 
	 * @return locale
	 */
	public Locale getLocale()
	{
		return locale;
	}
	
	/**
	 * Getter for viewing inventory
	 * 
	 * @return if the player is viewing the inventory
	 */
	public boolean getIsViewingInventory() {
		return isViewingInventory;
	}
	
	/**
	 * Setter for current room
	 * 
	 * @param room Value to set
	 */
	//Setters for player fields
	public void setCurrentRoom(Room room) {
		currentRoom = room;
	}
	
	/**
	 * Setter for locale
	 * 
	 * @param setLocale Value to set
	 */
	public void setLocale (Locale setLocale)
	{
		locale = setLocale;
	}
	
	/**
	 * Setter for viewing inventory
	 * 
	 * @param isViewing Value to set
	 */
	public void setIsViewingInventory(boolean isViewing ) {
		isViewingInventory = isViewing;
	}

	/**
	 * Getter for whether the player has an item
	 * 
	 * @param itemName Name of item
	 * @return true or false
	 */
	public boolean hasItem(String itemName) {
		for (Item item : inventory.getAllInventory()) {
			if(item.getName().toUpperCase().equals(itemName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Getter for whether the player has completed a quest
	 * 
	 * @param questName Name of quest to check
	 * @return true or false
	 */
	public boolean completedQuest(String questName) {
		if(this.quests.containsKey(questName)) {
			return this.quests.get(questName);
		}		
		
		return false;
	}
	
	/**
	 * Setter for quest completion
	 * @param questName Value to set
	 */
	public void setQuestComplete(String questName) {
		this.quests.put(questName, true);
	}
	
	/**
	 * Getter for viewing inventory
	 * 
	 */
	public boolean getHasLitTorch() {
		return hasLitTorch;
	}
	
	/**
	 * Setter for viewing inventory
	 * 
	 * @param isViewing Value to set
	 */
	public void setHasLitTorch(boolean hasLitTorch ) {
		this.hasLitTorch = hasLitTorch;
	}
	
}
