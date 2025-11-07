package textAdventure;

import java.util.ArrayList;

public class Room {

	//Fields for a room
	private String name;
	private ArrayList<ActionFrame> actionList;
	private ArrayList<BaseInteractable> interactableList;
	private ArrayList<Item> itemList;
	
	//Constructor
	public Room(String name, ArrayList<ActionFrame> actionList, ArrayList<BaseInteractable> interactableList, ArrayList<Item> itemList) {
		this.name = name;
		this.actionList = actionList;
		this.interactableList = interactableList;
		this.itemList = itemList;
	}
	
	//Getters for fields
	public String getName() {
		return name;
	}
	
	public ArrayList<ActionFrame> getActionList() {
		return actionList;
	}
	
	public ArrayList<BaseInteractable> getInteractableList() {
		return interactableList;
	}
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
}
