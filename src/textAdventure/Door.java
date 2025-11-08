package textAdventure;

public class Door extends BaseInteractable {

	//Fields for a door object specifically
	private Boolean openedStatus;
	
	//Constructor
	public Door(String name, String desc, String lore) {
		//Uses the constructor from the parent class
		super(name, desc, lore);
		
		//Sets the openedStatus
		openedStatus = false;
	}
	
	//Getter
	public Boolean getOpenedStatus() {
		return openedStatus;
	}
}
