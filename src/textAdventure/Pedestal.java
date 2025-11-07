package textAdventure;

public class Pedestal extends BaseInteractable {

	//Fields for a pedestal
	private Item displayedItem;
	private Boolean pedestalHasItem;
	
	//Getters for a pedestal
	public Item getDisplayedItem() {
		return displayedItem;
	}
	
	public Boolean getPedestalHasItem() {
		//Logic
		//If there is no item on the pedestal
		if(displayedItem.equals(null)) {
			return false;
			
			//If there is an item on the pedestal
		} else {
			return true;
		}
	}
	
}
