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
	private String titleScreenDescription;
	private ArrayList<String> titleScreenActions;
	
	/**
	 * Constructor
	 * 
	 */
	public GameManagement() {
		//Assign creation methods to the lists for the game-- STUBBED OUT, METHOD TO BE COMPLETED
//		createGameContent();
//		
//		//Create the player
//		player = new Player();
		
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
	
	//Create a list of rooms for the game --STUBBED OUT, USE GAME NOTES AND DOCS TO COMPLETE
	public static void createGameContent() {
		//Define some lists to hold rooms, items, and interactables
		ArrayList<Room> gameRooms = new ArrayList<Room>();
		ArrayList<Item> gameItems = new ArrayList<Item>();
		ArrayList<BaseInteractable> gameInteractables = new ArrayList<BaseInteractable>();

		//Create the north gardens room 
		Room northGardens = new Room();
		
		//Set the values of the north gardens
		northGardens.setName("North Gardens");
		northGardens.setDesc("North Gardens desc...");
		
		//Create lists for the actions, items, and interactables in the room
		ArrayList<String> ngRoomActions = new ArrayList<>(List.of("Move South", "Inspect Pedestal", "Inspect Rock Engravings", "Pick up Wheat Totem"));
		
		ArrayList<Item> ngRoomItems = new ArrayList<>();
		Item wheatTotem = new Item("Wheat Totem", "Wheat Totem desc...", "Wheat totem lore...", "Place Totem");
		
		ArrayList<BaseInteractable> ngRoomInteractables = new ArrayList<>();
		Pedestal northGardensPedestal = new Pedestal("Pedestal", "North Pedestal desc..", "North Pedestal lore...", wheatTotem);
		
		//Add the lists to the room
		northGardens.setRoomActions(ngRoomActions);
		northGardens.setItemList(ngRoomItems);
		northGardens.setInteractableList(ngRoomInteractables);
		
		//Add the individual items to the overall game lists
		gameRooms.add(northGardens);
		gameItems.add(wheatTotem);
		gameInteractables.add(northGardensPedestal);
		
		//Create the east gardens room
		
		//Create the south gardens room
		
		//Create the west gardens room
		
		//Create the east gardens room
		
		//Create the crypt entrance (inside) room
		
		//Create dark room (A)
		
		//Create dark room (B)
		
		//Create dark room (C)
		
		//Create dark room (D)
		
		//Create dark room (E)
		
		//Create dark room (F)
		
		//Create crypt hallway
		
		//Create crypt split hallway
		
		//Create artifact room
		
		//Create spirit room
		
		//Assign the lists of objects to the class
		roomList = gameRooms;
		itemList = gameItems;
		interactableList = gameInteractables;
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
		this.titleScreenActions = titleScreenActions;
	}
	
}
