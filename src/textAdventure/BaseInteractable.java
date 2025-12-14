package textAdventure;

/**
 * Class for managing interactable objects
 * 
 * This class acts as the base for other interactable types
 * BaseInteractable is a subclass of Interactable
 * BaseInteractable uses a Comparable for comparing BaseInteractables
 */
public abstract class BaseInteractable implements Interactable, Comparable<BaseInteractable> {

	//Fields for any interactable
	/**
	 * Fields of the BaseInteractable Class
	 * 
	 * id: Database id of the interactable object
	 * name: Name of the interactable object
	 * desc: Description of the interactable object
	 * lore: Lore of the interactable object
	 */
	private int id;
	private String name;
	private String description;
	private String lore;
	
	/** 
	 * Constructor for the BaseInteractable class
	 * 
	 * @param id Database id of the interactable object 
	 * @param name Name of the interactable object
	 * @param desc Description of the interactable object
	 * @param lore Lore of the interactable object
	 */
	public BaseInteractable(int id, String name, String desc, String lore) {
		this.id = id;
		this.name = name;
		this.description = desc;
		this.lore = lore;
	}
	
	//Override the behavior of the methods in the interface
	/**
	 * Get the name of the interactable object
	 * 
	 * @return The name of the interactable object
	 */
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * Get the description of the interactable object
	 * 
	 * @return The description of the interactable object
	 */
	@Override
	public String getDesc() {
		return description;
	}
	
	/**
	 * Get the lore of the interactable object
	 * 
	 * @return The lore of the interactable object
	 */
	@Override
	public String getLore() {
		return lore;
	}

	/**
	 * Get the id of the interactable object
	 * 
	 * @return The id of the interactable object
	 */
	@Override
	public int getID() {
		return id;
	}
	
	/**
	 * the compareTo function of a comparator interface
	 * 
	 * @param other Other interactable to compare the two objects
	 * @return boolean on whether the two objects are the same
	 */
	@Override
	public int compareTo(BaseInteractable other) {
	return Integer.compare(this.id, other.id);
	}
	
}
