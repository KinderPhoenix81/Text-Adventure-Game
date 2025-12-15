package textAdventure;

/*
 * 1.2: Use of Interface
 */

/**
 * Interface for creating Interactable objects
 */
public interface Interactable {

	/**
	 * Methods that all Interactables will share
	 * 
	 * getName(): getter for interactable name
	 * getDesc(): getter for interactable description
	 * getLore(): getter for interactable lore
	 * getID(): getter for interactable ID
	 * compareTo(): comparator for interactables
	 */
	//default behaviors of any interactable
	public String getName();
	public String getDesc();
	public String getLore();
	public int getID();
	int compareTo(Interactable other);
	
}
