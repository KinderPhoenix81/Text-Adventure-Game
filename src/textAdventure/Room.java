package textAdventure;

import java.util.ArrayList;

public class Room implements Comparable<Room>{

	//Fields for a room
	private String name;
	private int id;
	
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
	public Room(String name, String roomDesc, int id, ArrayList<String> roomActions, ArrayList<BaseInteractable> interactableList, ArrayList<Item> itemList) {
		this.name = name;
		this.description = roomDesc;
		this.id = id;
		this.roomActions = roomActions;
		this.interactableList = interactableList;
		this.itemList = itemList;
	}
	
	//Constructor without needing actions, interactables, or items
	public Room(String name, String roomDesc, int id) {
		this.name = name;
		this.description = roomDesc;
		this.id = id;
		this.roomActions = null;
		this.interactableList = null;
		this.itemList = null;
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

	@Override
	public int compareTo(Room o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getID(), o.getID());
	}
	
}
