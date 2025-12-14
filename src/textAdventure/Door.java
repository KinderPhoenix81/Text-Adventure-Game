package textAdventure;

/**
 * Class for the door to the crypt
 * 
 * The door class is a subclass of BaseInteractable
 */
public class Door extends BaseInteractable {

	/**
	 * Fields for Door
	 * 
	 * openedStatus: the state of the door being opened or closed
	 */
	//Fields for a door object specifically
	private Boolean openedStatus;
	
	/**
	 * Constructor for Door
	 * 
	 * @param id ID for the interactable from the database
	 * @param name Name for the door
	 * @param desc Description of the object
	 * @param lore Lore for when the player inspects the door
	 */
	//Constructor
	public Door(int id, String name, String desc, String lore) {
		//Uses the constructor from the parent class
		super(id, name, desc, lore);
		
		//Sets the openedStatus
		openedStatus = false;
	}
	
	/**
	 * Secondary overloaded constructor for when the door is open
	 * 
	 * @param id ID for the interactable from the database
	 * @param name Name for the door
	 * @param desc Description of the object
	 * @param lore Lore for when the player inspects the door
	 * @param openedStatus Status of whether the door is opened or closed
	 */
	//Overloaded constructor to specify door status
	public Door(int id, String name, String desc, String lore, Boolean openedStatus) {
		//Uses the constructor from parent class
		super(id, name, desc, lore);
		this.openedStatus = openedStatus;
	}
	
	/**
	 * Getter for the status of the door
	 * 
	 * @return openedStatus whether the door is opened or closed
	 */
	//Getters
	public Boolean getOpenedStatus() {
		return openedStatus;
	}

	/**
	 * Comparator for comparing
	 * 
	 * @return integer to determine whether the comparison passed or failed
	 */
	@Override
	public int compareTo(Interactable o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getID(), o.getID());
	}
}
