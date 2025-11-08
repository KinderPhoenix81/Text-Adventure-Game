package textAdventure;

public class Item {

	//Fields for an item
	private String name;
	private String description;
	
	//String containing the two word parser for doing something
	//Ex: Use Health Potion, Place Totem, Equip Torch, etc.
	private String action;
	
	//Constructor
	public Item(String name, String desc, String action) {
		this.name = name;
		description = desc;
		this.action = action;
	}
	
	//Getters for information
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return description;
	}
	
	public String getAction() {
		return action;
	}
}
