package textAdventure;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	//set a list of items to be the inventory
	private static List<Item> items;
	
	public Inventory() {
		Inventory.items = new ArrayList<>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public static List<Item> getAllInventory(){
		return items;
	}
	
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
