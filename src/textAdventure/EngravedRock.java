package textAdventure;

/**
 * Class for managing the engraved rocks
 * Used in the garden puzzle
 * 
 * EngravedRock is a subclass of BaseInteractable
 */
public class EngravedRock extends BaseInteractable{
	
	/**
	 * Constructor for EngravedRock
	 * 
	 * @param id ID for database interactable
	 * @param name Name for interactable
	 * @param desc Description of the object
	 * @param lore Lore for when player inspects
	 */
	//Constructor
	public EngravedRock(int id, String name, String desc, String lore) {
		//Uses parent constructor
		super(id, name, desc, lore);
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
