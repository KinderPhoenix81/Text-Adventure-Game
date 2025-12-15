package textAdventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Room implements Comparable<Room>{

	//Fields for a room
	private String name;
	private int id;
	
	//Used for the text prompt field in GameManagement, describes the environment
	private String description;
	
	//used for allowing you to move from place to place
	private final Map<Direction, Room> exits;
	
	//used to determine if the player has met a special condition when entering a room
	private String specialConditionVerb;
	private String specialConditionNoun;
	
	//holds the special action tied to the special condition
	private String specialAction;
	
	//Used for the action prompt field in GameManagement, describes potential actions
	private ArrayList<String> roomActions;
	
	//Lists for potential items and interactables in a given room
	private ArrayList<BaseInteractable> interactableList;
	private ArrayList<Item> itemList;
	
	//Empty Constructor
	public Room() {
		this.exits = new HashMap<>();
		this.roomActions = new ArrayList<>();
		this.interactableList  = new ArrayList<>();
		this.itemList = new ArrayList<>();
	}
	
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
	
	//Getters for fields
	public String getName() {
		return name;
	}
	
	public String getRoomDesc() {
		return description;
	}
	
	public int getID() {
		return id;
	}
	
	public ArrayList<String> getRoomActions() {
		return roomActions;
	}
	
	public ArrayList<BaseInteractable> getInteractableList() {
		return interactableList;
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	//Setters for fields
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDesc(String desc) {
		description = desc;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setRoomActions(ArrayList<String> roomActions) {
		this.roomActions = roomActions;
	}
	
	public void setInteractableList(ArrayList<BaseInteractable> interactableList) {
		this.interactableList = interactableList;
	}
	
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
	public void setSpecialConditionVerb(String verb) {
		specialConditionVerb = verb;
	}
	
	public void setSpecialConditionNoun(String noun) {
		specialConditionNoun = noun;
	}
	
	public String getSpecialConditionVerb() {
		return this.specialConditionVerb;
	}	
	
	public String getSpecialConditionNoun() {
		return this.specialConditionNoun;
	}	
	
	public void setSpecialAction(String action) {
		
		specialAction = action;
	}
	
	public String getSpecialAction() {
		return this.specialAction;
	}	
	
	//logic for room exits
	public void setExit(Direction direction, Room neighbor) {
		exits.put(direction, neighbor);
	}
	
	public Room getExit(Direction direction) {
		return exits.get(direction);
	}
	
	public Map<Direction, Room> getExits() {
		return exits;
	}

	@Override
	public int compareTo(Room o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getID(), o.getID());
	}
	
	public void removeItem(Item item) {
		this.itemList.remove(item);
	}
	
	public void addRoomAction(String action) {
		if (!this.roomActions.contains(action)) {
			this.roomActions.add(action);
		}
	}
	
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
