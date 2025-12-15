package textAdventure;

import java.util.Comparator;

/*
 * 2.2: Encapsulation Example
 */
/**
 * Class for Items the player can collect
 */
public class Item implements Comparable<Item>{

	/**
	 * Fields an item can have
	 * 
	 * name: name of the item
	 * id: database id of the item
	 * description: description of the item
	 * lore: lore of the item
	 */
	//Fields for an item
	private String name;
	private int id;
	private String description;
	private String lore;
	
	/**
	 * The action an item is associated with
	 * 
	 * Using the action word allows the player to use
	 * the item
	 */
	//String containing the two word parser for doing something
	//Ex: Use Health Potion, Place Totem, Equip Torch, etc.
	private String action;
	
	/*
	 * Comparators
	 * 
	 * 3.4: Use of Comparator
	 */
	/**
	 * Comparators for comparing items by name or by id
	 */
	public static final Comparator<Item> BY_NAME = Comparator.comparing(Item::getName);
	public static final Comparator<Item> BY_ID = Comparator.comparing(Item::getID);
	
	/**
	 * Constructor for Item
	 * 
	 * @param name Item name
	 * @param id Item database ID
	 * @param desc Item description
	 * @param lore Item lore
	 * @param action Item action
	 */
	//Constructor
	public Item(String name, int id, String desc, String lore, String action) {
		this.name = name;
		this.id = id;
		description = desc;
		this.lore = lore;
		this.action = action;
	}
	
	/**
	 * Getter for Name
	 * 
	 * @return Item name
	 */
	//Getters for information
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for ID
	 * 
	 * @return Item database ID
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Getter for Description
	 * 
	 * @return Item description
	 */
	public String getDesc() {
		return description;
	}
	
	/**
	 * Getter for Lore
	 * 
	 * @return Item lore
	 */
	public String getLore() {
		return lore;
	}
	
	/**
	 * Getter for Action
	 * 
	 * @return Item action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Item comparator
	 * 
	 * @param o Item to compare
	 */
	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.id, o.id);
	}
}
