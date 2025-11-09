package textAdventure;

import java.util.ArrayList;

public class GameManagement {
	//Class to manage the game functions
	//Fields of this class, aka objects the game needs
	private ArrayList<Room> roomList;
	private ArrayList<Item> itemList;
	private ArrayList<BaseInteractable> interactableList;
	private Player player;
	
	//Provides information about the current environment
	private String textPrompt;
	
	//Provides information about actions that can be taken in the environment
	private String actionPrompt;
	
	//Provides base information about basic actions
	private String playerActionsPrompt;
	
	//Getter for the text prompt
	public String getTextPrompt() {
		return textPrompt;
	}
	
	//Setter for the text prompt
	public void setTextPrompt(String prompt) {
		textPrompt = prompt;
	}
	
	//Getter for the action prompt
	public String getActionPrompt() {
		return actionPrompt;
	}
	
	//Setter for action prompt
	public void refreshActionPrompt(Room room) {
		StringBuilder prompt = new StringBuilder();
		
		//For each action in the current room, get the actions and append it
		for(String action : room.getRoomActions()) {
			prompt.append(action);
			prompt.append("\n");
		}
		
		//Set the value of the room actions
		actionPrompt = prompt.toString();
	}
	
	//Setter for baseActionsPrompt
	public void refreshPlayerActionPrompt(Player player) {
		StringBuilder prompt = new StringBuilder();
		
		//For each item in the player's inventory, get the item's action and append it
		for(Item item : player.getInventory()) {
			prompt.append(item.getAction());
			prompt.append("\n");
		}
		
		//Set the value of playerActions
		playerActionsPrompt = prompt.toString();
	}
	
	//Displays the current text prompt loaded into the field
	public void displayText() {
		System.out.println(textPrompt);
		System.out.println(actionPrompt);
		System.out.println(playerActionsPrompt);
	}
}
