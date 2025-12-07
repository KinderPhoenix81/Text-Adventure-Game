package textAdventure;

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
	
	//Setter for pedestal item
	public void setPedestalItem(Item item) {
		displayedItem = item;
	}

	//Comparison method
	@Override
	public int compareTo(Interactable o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getID(), o.getID());
	}
	
}
