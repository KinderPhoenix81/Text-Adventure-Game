package textAdventure;

import java.util.ArrayList;

public class Room {

	//Fields for a room
	private String name;
	
	//Used for the text prompt field in GameManagement, describes the environment
	private String description;
	
	//Used for the action prompt field in GameManagement, describes potential actions
	private ArrayList<String> roomActions;
	
	//Lists for potential items and interactables in a given room
	private ArrayList<BaseInteractable> interactableList;
	private ArrayList<Item> itemList;
	
	//Empty Constructor
	public Room() {
		
	}
	
	//Constructor
	public Room(String name, String roomDesc, ArrayList<String> roomActions, ArrayList<BaseInteractable> interactableList, ArrayList<Item> itemList) {
		this.name = name;
		this.description = roomDesc;
		this.roomActions = roomActions;
		this.interactableList = interactableList;
		this.itemList = itemList;
	}

	//Method to create the starting room
	public static Room createStartingRoom() {
		//Properties for the current room
		ArrayList<Item> roomItems = new ArrayList<Item>();
		ArrayList<BaseInteractable> roomInteractables = new ArrayList<BaseInteractable>();
		ArrayList<String> roomActions = new ArrayList<String>();
		
		//Create a door interactable
		Door cryptDoor = new Door("Crypt Door", "Crypt Door Desc...", "Crypt Door Lore");
		roomInteractables.add(cryptDoor);
		
		//Create an action for the crypt entrance and moving to other gardens
		String entranceAction = "Open Crypt Door";
		String moveNorth = "Move North";
		String moveEast = "Move East";
		String moveSouth = "Move South";
		String moveWest = "Move West";
		
		//Add these actions to the list of actions for this room
		roomActions.add(entranceAction);
		roomActions.add(moveNorth);
		roomActions.add(moveEast);
		roomActions.add(moveSouth);
		roomActions.add(moveWest);
		
		//Create a room for the crypt entrance
		Room room = new Room("Crypt Entrance- Outside", "Crypt Entrance Description", roomActions, roomInteractables, roomItems);
	
		//Return this room object
		return room;
	}
	
	
	//Getters for fields
	public String getName() {
		return name;
	}
	
	public String getRoomDesc() {
		return description;
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
	
	public void setRoomActions(ArrayList<String> roomActions) {
		this.roomActions = roomActions;
	}
	
	public void setInteractableList(ArrayList<BaseInteractable> interactableList) {
		this.interactableList = interactableList;
	}
	
	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}
	
}
