package textAdventure;

public class Door extends BaseInteractable {

	//Fields for a door object specifically
	private Boolean openedStatus;
	
	//Constructor
	public Door(int id, String name, String desc, String lore) {
		//Uses the constructor from the parent class
		super(id, name, desc, lore);
		
		//Sets the openedStatus
		openedStatus = false;
	}
	
	/*
	 * Overloaded door constructor
	 * 
	 * 1.3: Overloaded Constructor
	 */
	public Door(int id, String name, String desc, String lore, Boolean openedStatus) {
		//Uses the constructor from parent class
		super(id, name, desc, lore);
		this.openedStatus = openedStatus;
	}
	
	//Getters
	public Boolean getOpenedStatus() {
		return openedStatus;
	}

	@Override
	public int compareTo(Interactable o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getID(), o.getID());
	}
}
