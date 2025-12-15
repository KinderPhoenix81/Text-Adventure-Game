package textAdventure;

import java.util.function.Predicate;

/**
 * Pedestal class for the Garden Puzzle
 * 
 * Pedestal is a subclass of BaseInteractable
 */
public class Pedestal extends BaseInteractable {

	/**
	 * Pedestal fields
	 * 
	 * displayedItem: Item on a pedestal
	 * pedestalHasItem: Whether an item is on the pedestal
	 * correctItemID: Correct item for the puzzle
	 */
	//Fields for a pedestal
	private Item displayedItem;
	private Boolean pedestalHasItem;
	private int correctItemID;
	
	/**
	 * Constructor for Pedestal
	 * 
	 * @param id Pedestal database ID
	 * @param name Pedestal name
	 * @param desc Pedestal description
	 * @param lore Pedestal lore
	 * @param displayedItem Pedestal displayed item
	 */
	//Constructor for the pedestal
	public Pedestal(int id, String name, String desc, String lore, Item displayedItem) {
		//Uses parent constructor
		super(id, name, desc, lore);
		
		//Places an item on the pedestal upon start
		this.displayedItem = displayedItem;
		pedestalHasItem = true;
	}
	
	/**
	 * Constructor for pedestal without an item
	 * 
	 * @param id Pedestal ID
	 * @param name Pedestal name
	 * @param desc Pedestal description
	 * @param lore Pedestal lore
	 */
	//Overloaded constructor without the item
	public Pedestal(int id, String name, String desc, String lore) {
		//Uses parent
		super(id, name, desc, lore);
	}
	
	/**
	 * Check if the pedestal has the correct item on it
	 */
	//Predicate for checking correct item
	Predicate<Pedestal> hasCorrectItem = pedestal -> {
		//Check if pedestal has an item to start
		if(!pedestal.getPedestalHasItem()) {
			return false;
		}
		
		//Check to see if the pedestal has the right item
		return pedestal.getDisplayedItem().getID() == pedestal.getPedestalCorrectItemID();
	};
	
	/**
	 * Getter for DisplayedItem
	 * 
	 * @return Item on the pedestal
	 */
	//Getters for a pedestal
	public Item getDisplayedItem() {
		return displayedItem;
	}
	
	/**
	 * Getter for PedestalHasItem
	 * 
	 * @return Whether the pedestal has an item
	 */
	public Boolean getPedestalHasItem() {
		//Logic
		//If there is an item on the pedestal
		if(displayedItem != null) {
			return true;
			
			//If there is no item on the pedestal
		} else {
			return false;
		}
	}
	
	/**
	 * Getter for correctItemID
	 * 
	 * @return ID of the correct item
	 */
	//Getter for pedestal correct item id
	public int getPedestalCorrectItemID() {
		return correctItemID;
	}
	
	/**
	 * Setter for correct item ID
	 * 
	 * @param id Value to set
	 */
	//Setter for pedestal correct item id
	public void setPedestalCorrectItemID(int id) {
		correctItemID = id;
	}
	
	/**
	 * Setter for item on pedestal
	 * 
	 * @param item Value to set
	 */
	//Setter for pedestal item
	public void setPedestalItem(Item item) {
		displayedItem = item;
	}

	/**
	 * Comparator for comparison
	 * 
	 * @param o Interactable to compare
	 */
	/*
	 * Comparison Method
	 * 
	 * 1.9: Use of @Override
	 */
	@Override
	public int compareTo(Interactable o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getID(), o.getID());
	}
	
}
