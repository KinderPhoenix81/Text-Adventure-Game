package textAdventure;

import java.util.ArrayList;

public class Room {

	//Fields for a room
	private String name;
	
	//Used for the text prompt field in GameManagement, describes the environment
	private String roomDescription;
	
	//Used for the action prompt field in GameManagement, describes potential actions
	private String roomActions;
	
	//Lists for potential items and interactables in a given room
	private ArrayList<BaseInteractable> interactableList;
	private ArrayList<Item> itemList;
	
	//Constructor
	public Room(String name, String roomDesc, String roomActions, ArrayList<BaseInteractable> interactableList, ArrayList<Item> itemList) {
		this.name = name;
		this.roomDescription = roomDesc;
		this.roomActions = roomActions;
		this.interactableList = interactableList;
		this.itemList = itemList;
	}
	
	//Getters for fields
	public String getName() {
		return name;
	}
	
	public String getRoomDesc() {
		return roomDescription;
	}
	
	public String getRoomActions() {
		return roomActions;
	}
	
	public ArrayList<BaseInteractable> getInteractableList() {
		return interactableList;
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
}
