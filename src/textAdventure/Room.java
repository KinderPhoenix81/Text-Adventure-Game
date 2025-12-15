package textAdventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class for Room the player can be in
 */
public class Room implements Comparable<Room>{

	/**
	 * Fields for room
	 * 
	 * name: Room name
	 * id: Room database id
	 */
	//Fields for a room
	private String name;
	private int id;
	
	/**
	 * description: Description of the room
	 */
	//Used for the text prompt field in GameManagement, describes the environment
	private String description;
	
	/**
	 * Exits a room has
	 */
	//used for allowing you to move from place to place
	private final Map<Direction, Room> exits;
	
	/**
	 * Special conditions that make the room act differently
	 */
	//used to determine if the player has met a special condition when entering a room
	private String specialConditionVerb;
	private String specialConditionNoun;
	
	/**
	 * Special action that can be performed
	 * when the special condition is fulfilled
	 */
	//holds the special action tied to the special condition
	private String specialAction;
	
	/**
	 * List of actions a room can perform
	 */
	//Used for the action prompt field in GameManagement, describes potential actions
	private ArrayList<String> roomActions;
	
	/**
	 * Items and interactables in a room
	 */
	//Lists for potential items and interactables in a given room
	private ArrayList<BaseInteractable> interactableList;
	private ArrayList<Item> itemList;
	
	/**
	 * Empty constructor for room
	 */
	//Empty Constructor
	public Room() {
		this.exits = new HashMap<>();
		this.roomActions = new ArrayList<>();
		this.interactableList  = new ArrayList<>();
		this.itemList = new ArrayList<>();
	}
	
	/**
	 * Constructor for Room
	 * 
	 * @param name Room name
	 * @param roomDesc Room description
	 * @param id Room database ID
	 * @param roomActions Room actions
	 * @param interactableList Room interactables
	 * @param itemList Room items
	 */
	//Constructor
	public Room(String name, String roomDesc, int id, ArrayList<String> roomActions, ArrayList<BaseInteractable> interactableList, ArrayList<Item> itemList) {
		this.name = name;
		this.description = roomDesc;
		this.id = id;
		this.roomActions = roomActions;
		this.interactableList = interactableList;
		this.itemList = itemList;
		this.exits = new HashMap<>();
	}
	
	/**
	 * Constructor for a room without objects
	 * 
	 * @param name Room name
	 * @param roomDesc Room description
	 * @param id Room database ID
	 */
	//Constructor without needing actions, interactables, or items
	public Room(String name, String roomDesc, int id) {
		this.name = name;
		this.description = roomDesc;
		this.id = id;
		this.roomActions = null;
		this.interactableList = null;
		this.itemList = null;
		this.exits = new HashMap<>();
	}	
	
	/**
	 * Getter for name
	 * 
	 * @return room name
	 */
	//Getters for fields
	public String getName() {
		return name;
	}
	
	/**
	 * Getter for room description
	 * 
	 * @return room description
	 */
	public String getRoomDesc() {
		return description;
	}
	
	/**
	 * Getter for room ID
	 * 
	 * @return room database ID
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Getter for room actions
	 * 
	 * @return list of room actions
	 */
	public ArrayList<String> getRoomActions() {
		return roomActions;
	}
	
	/**
	 * Getter for interactables
	 * 
	 * @return list of interactables
	 */
	public ArrayList<BaseInteractable> getInteractableList() {
		return interactableList;
	}
	
	/**
	 * Getter for items
	 * 
	 * @return list of items
	 */
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	/**
	 * Setter for name
	 * 
	 * @param name Value to set
	 */
	//Setters for fields
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter for description
	 * 
	 * @param desc Value to set
	 */
	public void setDesc(String desc) {
		description = desc;
	}
	
	/**
	 * Setter for ID
	 * 
	 * @param id Value to set
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * Setter for actions
	 * 
	 * @param roomActions Value to set
	 */
	public void setRoomActions(ArrayList<String> roomActions) {
		this.roomActions = roomActions;
	}
	
	/**
	 * Setter for interactables
	 * 
	 * @param interactableList Value to set
	 */
	public void setInteractableList(ArrayList<BaseInteractable> interactableList) {
		this.interactableList = interactableList;
	}
	
	/**
	 * Setter for items
	 * 
	 * @param itemList Value to set
	 */
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	/**
	 * Setter for special condition verb
	 * 
	 * @param verb Value to set
	 */
	public void setSpecialConditionVerb(String verb) {
		specialConditionVerb = verb;
	}
	
	/**
	 * Setter for special condition noun
	 * 
	 * @param noun Value to set
	 */
	public void setSpecialConditionNoun(String noun) {
		specialConditionNoun = noun;
	}
	
	/**
	 * Getter for special condition verb
	 * 
	 * @return Verb part of a special condition
	 */
	public String getSpecialConditionVerb() {
		return this.specialConditionVerb;
	}	
	
	/**
	 * Getter for special condition noun
	 * 
	 * @return Noun part of a special condition
	 */
	public String getSpecialConditionNoun() {
		return this.specialConditionNoun;
	}	
	
	/**
	 * Setter for special action
	 * 
	 * @param action Value to set
	 */
	public void setSpecialAction(String action) {
		
		specialAction = action;
	}
	
	/**
	 * Getter for special action
	 * 
	 * @return Special action to perform
	 */
	public String getSpecialAction() {
		return this.specialAction;
	}	
	
	/**
	 * Set the exits for a room
	 * 
	 * @param direction Direction the exit is in
	 * @param neighbor Neighboring room
	 */
	//logic for room exits
	public void setExit(Direction direction, Room neighbor) {
		exits.put(direction, neighbor);
	}
	
	/**
	 * Get exits from a room
	 * 
	 * @param direction Direction the exit is in
	 * @return Exits for a room
	 */
	public Room getExit(Direction direction) {
		return exits.get(direction);
	}
	
	/**
	 * Get all exits
	 * 
	 * @return map of exits
	 */
	public Map<Direction, Room> getExits() {
		return exits;
	}

	/**
	 * Comparator for comparison
	 * 
	 * @param o Room to compare
	 */
	@Override
	public int compareTo(Room o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getID(), o.getID());
	}
	
	/**
	 * Remove an item from room
	 * 
	 * @param item Item to remove
	 */
	public void removeItem(Item item) {
		this.itemList.remove(item);
	}
	
	/**
	 * Add an action to a room
	 * 
	 * @param action Action to add
	 */
	public void addRoomAction(String action) {
		if (!this.roomActions.contains(action)) {
			this.roomActions.add(action);
		}
	}
	
	/**
	 * Remove an action from a room
	 * 
	 * @param roomAction Action to remove
	 */
	public void removeRoomAction(String roomAction) {
	    String upperRoomAction = roomAction.toUpperCase();
	    
	    Iterator<String> iterator = this.roomActions.iterator();

	    while (iterator.hasNext()) {
	        String storedRoomAction = iterator.next();
	        String upperStoredRoomAction = storedRoomAction.toUpperCase();

	        if (upperStoredRoomAction.equals(upperRoomAction)) {
	            iterator.remove(); 
	            return;
	        }
	    }
	}		
}
