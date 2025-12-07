package textAdventure;

import java.util.Comparator;

public class Item implements Comparable<Item>{

	//Fields for an item
	private String name;
	private int id;
	private String description;
	private String lore;
	
	//String containing the two word parser for doing something
	//Ex: Use Health Potion, Place Totem, Equip Torch, etc.
	private String action;
	
	//Comparators
	public static final Comparator<Item> BY_NAME = Comparator.comparing(Item::getName);
	public static final Comparator<Item> BY_ID = Comparator.comparing(Item::getID);
	
	//Constructor
	public Item(String name, int id, String desc, String lore, String action) {
		this.name = name;
		this.id = id;
		description = desc;
		this.lore = lore;
		this.action = action;
	}
	
	//Getters for information
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public String getDesc() {
		return description;
	}
	
	public String getLore() {
		return lore;
	}
	
	public String getAction() {
		return action;
	}

	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.id, o.id);
	}
}
