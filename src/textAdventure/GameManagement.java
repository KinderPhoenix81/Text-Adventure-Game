package textAdventure;

import java.util.ArrayList;
import java.util.List;

public class GameManagement {
	//Class to manage the game functions
	
	/**
	 * Fields of the GameManagementClass
	 * 
	 */
	private static ArrayList<Room> roomList;
	private static ArrayList<Item> itemList;
	private static ArrayList<BaseInteractable> interactableList;
	private Player player;
	
	//Provides information about the current environment
	private String environmentPrompt;
	
	//Provides information about actions that can be taken in the environment
	private ArrayList<String> actionPrompt;
	
	//Provides base information about basic actions
	private ArrayList<String> playerActionsPrompt;
	
	//Initial fields for the start menu / title screen
	private static String titleScreenDescription;
	private static ArrayList<String> titleScreenActions;
	
	/**
	 * Constructor
	 * 
	 */
	public GameManagement() {
		//Load game content
		createGameContent();
		
		//Load starting screen content
		createStartingScreenContent();
		
		//Create the player & set current room to beginning
		player = new Player("Player Name Here");
		player.setCurrentRoom(roomList.get(0));
	}
	
	
	/**
	 * Main methods of the game management class
	 * 
	 */
	
	//Update the current environment text based on the player's current room
	public void refreshEnvironmentPrompt(Room room) {
		//Get the room description and assign it to environment description
		environmentPrompt = room.getRoomDesc();
	}
	
	//Update the current set of actions based on the player's current room
	public void refreshActionPrompt(Room room) {
		ArrayList<String> roomActions = new ArrayList<String>();
		
		//For each action in the current room, get it and add it to the list
		for(String action : room.getRoomActions()) {
			roomActions.add(action);
		}
		
		//Set the value of the room actions
		actionPrompt = roomActions;
	}
	
	//Update the current player actions based on their inventory
	public void refreshPlayerActionPrompt(Player player) {
		ArrayList<String> playerActions = new ArrayList<String>();
		
		//For each item in the player's inventory, get the item's action
		for(Item item : player.getInventory()) {
			playerActions.add(item.getAction());
		}
		
		//Set the value of playerActions
		playerActionsPrompt = playerActions;
	}
	
		//Displays the current text prompt loaded into the field
		public void displayText() {
			System.out.println(environmentPrompt);
			System.out.println(actionPrompt);
			System.out.println(playerActionsPrompt);
		}
	
	//Create the content for the game
	public static void createGameContent() {
	//Loading the objects from the database into the gameManagement lists
	Database.createGameDatabase(itemList, interactableList, roomList);
	}

	//Create the title screen content for the game
	public static void createStartingScreenContent() {
		//Assign the title screen info to some strings, then set those values
		String titleScrDesc = "Title Screen Desc...";
		titleScreenDescription = titleScrDesc;
		
		//Create the list of actions for the title screen
		ArrayList<String> titleScrActions = new ArrayList<String>();
		titleScrActions.add("New Game");
		titleScrActions.add("Info");
		titleScrActions.add("Quit");
		titleScreenActions = titleScrActions;
	}
		
	/**
	 * Getters and setters for the class
	 * 
	 */
	
	//Getter and setter for the text prompt, describing base details of the surrounding area
	public String getEnvironmentPrompt() {
		return environmentPrompt;
	}
	
	public void setEnvironmentPrompt(String prompt) {
		environmentPrompt = prompt;
	}
	
	//Getter and setter for the title screen text (logo, game name, ASCII art, etc.)
	public String getTitleScreenDesc() {
		return titleScreenDescription;
	}
	
	public void setTitleScreenDesc(String desc) {
		titleScreenDescription = desc;
	}
	
	//Getter and setter for the title screen actions
	public ArrayList<String> getTitleScreenActions() {
		return titleScreenActions;
	}
	
	public void setTitleScreenActions(ArrayList<String> titleScreenActions) {
		GameManagement.titleScreenActions = titleScreenActions;
	}
	
	//Getter and setter for room, item, and interactable lists
	public ArrayList<Room> getRoomList() {
		return GameManagement.roomList;
	}
	
	public void setRoomList(ArrayList<Room> roomList) {
		GameManagement.roomList = roomList;
	}
	
	public ArrayList<Item> getItemList() {
		return GameManagement.itemList;
	}
	
	public void setItemList(ArrayList<Item> itemList) {
		GameManagement.itemList = itemList;
	}
	
	public ArrayList<BaseInteractable> getInteractables() {
		return GameManagement.interactableList;
	}
	
	public void setInteractableList(ArrayList<BaseInteractable> interactableList) {
		GameManagement.interactableList = interactableList;
	}
}
