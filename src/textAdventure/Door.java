package textAdventure;

public class Door extends BaseInteractable {

	//Fields for a door object specifically
	private Boolean openedStatus;
	private int id;
	
	//Constructor
	public Door(int id, String name, String desc, String lore) {
		//Uses the constructor from the parent class
		super(name, desc, lore);
		
		this.id = id;
		
		//Sets the openedStatus
		openedStatus = false;
	}
	
	//Overloaded constructor to specfiy door status
	public Door(String name, String desc, String lore, Boolean openedStatus) {
		//Uses the constructor from parent class
		super(name, desc, lore);
		this.openedStatus = openedStatus;
	}
	
	//Getters
	public Boolean getOpenedStatus() {
		return openedStatus;
	}
	
	public int getID() {
		return id;
	}
}
