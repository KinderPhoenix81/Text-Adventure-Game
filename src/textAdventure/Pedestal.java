package textAdventure;

import java.util.function.Predicate;

public class Pedestal extends BaseInteractable {

	//Fields for a pedestal
	private Item displayedItem;
	private Boolean pedestalHasItem;
	private int correctItemID;
	
	//Constructor for the pedestal
	public Pedestal(int id, String name, String desc, String lore, Item displayedItem) {
		//Uses parent constructor
		super(id, name, desc, lore);
		
		//Places an item on the pedestal upon start
		this.displayedItem = displayedItem;
		pedestalHasItem = true;
	}
	
	//Overloaded constructor without the item
	public Pedestal(int id, String name, String desc, String lore) {
		//Uses parent
		super(id, name, desc, lore);
	}
	
	//Predicate for checking correct item
	Predicate<Pedestal> hasCorrectItem = pedestal -> {
		//Check if pedestal has an item to start
		if(!pedestal.getPedestalHasItem()) {
			return false;
		}
		
		//Check to see if the pedestal has the right item
		return pedestal.getDisplayedItem().getID() == pedestal.getPedestalCorrectItemID();
	};
	
	//Getters for a pedestal
	public Item getDisplayedItem() {
		return displayedItem;
	}
	
	public Boolean getPedestalHasItem() {
		//Logic
		//If there is no item on the pedestal
		if(displayedItem.equals(null)) {
			pedestalHasItem = false;
			return pedestalHasItem;
			
			//If there is an item on the pedestal
		} else {
			pedestalHasItem = true;
			return pedestalHasItem;
		}
	}
	
	//Getter for pedestal correct item id
	public int getPedestalCorrectItemID() {
		return correctItemID;
	}
	
	//Setter for pedestal correct item id
	public void setPedestalCorrectItemID(int id) {
		correctItemID = id;
	}
	
	//Setter for pedestal item
	public void setPedestalItem(Item item) {
		displayedItem = item;
	}

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
