package textAdventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for tracking the player's inventory
 */
public class Inventory {
	/**
	 * List of items in the inventory
	 */
	//set a list of items to be the inventory
	private static List<Item> items;
	
	/**
	 * Constructor for inventory
	 */
	public Inventory() {
		Inventory.items = new ArrayList<>();
	}
	
	/**
	 * Add an item to the inventory
	 * 
	 * @param item Item to add to inventory
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * Remove an item from inventory
	 * 
	 * @param item Item to remove from inventory
	 */
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	/**
	 * Getter for list of items in inventory
	 * 
	 * @return list of items in inventory
	 */
	public List<Item> getAllInventory(){
		return items;
	}
	
	/**
	 * Method to display the player's inventory
	 * 
	 * @return string of everything in the player's inventory
	 */
	public String displayAllInventory() {
		if (items.isEmpty()) {
			return "You are not carrying anything.";
		}
		
		String inventoryDisplayString = "INVENTORY~~~~~~~~~~~";
		for (Item item : items) {
			inventoryDisplayString = "\nExamine " + item.getName();
		}
		inventoryDisplayString += "\nBack";
		
		return inventoryDisplayString;
	}

}
