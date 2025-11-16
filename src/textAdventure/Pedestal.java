package textAdventure;

public class Pedestal extends BaseInteractable {

	//Fields for a pedestal
	private Item displayedItem;
	private Boolean pedestalHasItem;
	
	//Constructor for the pedestal
	public Pedestal(String name, String desc, String lore, Item displayedItem) {
		//Uses parent constructor
		super(name, desc, lore);
		
		//Places an item on the pedestal upon start
		this.displayedItem = displayedItem;
		pedestalHasItem = true;
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
	
}
