package textAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * Class for managing the game
 */
public class GameManagement {
	//Class to manage the game functions
	
	/**
	 * Fields of the GameManagementClass
	 * 
	 * roomList: Array List of rooms
	 * itemList: Array List of items
	 * interactableList: Array List of interactables
	 * player: tracking the player
	 */
	private static ArrayList<Room> roomList;
	private static ArrayList<Item> itemList;
	private static ArrayList<BaseInteractable> interactableList;
	private static Player player;
	
	/**
	 * The description that plays the first time 
	 * the player enters a room
	 */
	//Provides information about the current environment, compiling all information about a room
	private String environmentPrompt;
	
	/**
	 * The actions that a player can take
	 * in a room
	 */
	//Provides information about actions that can be taken in the environment
	private ArrayList<String> actionPrompt;
	
	/**
	 * The actions a player can take
	 */
	//Provides base information about basic actions
	private ArrayList<String> playerActionsPrompt;
	
	//add totem check booleans
	private boolean isNorthTotemPlaced = false;
	private boolean isEastTotemPlaced = false;
	private boolean isSouthTotemPlaced = false;
	private boolean isWestTotemPlaced = false;
	
	
	/**
	 * The actions a player can take
	 * on the title screen
	 */
	//Initial fields for the start menu / title screen
	private static String titleScreenDescription;
	private static ArrayList<String> titleScreenActions;
	
	/**
	 * Create an InputHandler object for handling user input
	 */
	//create new InputHandler
	private final InputHandler inputHandler;
	
	/**
	 * Constructor for creating a GameManagement object
	 */
	public GameManagement() {
		//Load game content
		roomList = new ArrayList<Room>();
		itemList = new ArrayList<Item>();
		interactableList = new ArrayList<BaseInteractable>();
		
		createGameContent(roomList, itemList, interactableList);
		
		//Load starting screen content
		createStartingScreenContent();
		
		//Create the player & set current room to beginning
		player = new Player("Player Name Here");
		player.setCurrentRoom(roomList.get(0));
		this.inputHandler = new InputHandler(player,this);
		
		//Show the starting room
		displayRoom.accept(Player.getCurrentRoom());
		
	}
	
	
	/**
	 * Set up a room for the player to be in
	 */
	//Set the text of a room, it's lore, description, and all actions
	public Consumer<Room> displayRoom = room -> {
		System.out.println("\n");
		System.out.println("~~~ " + room.getName() + " ~~~\n");
		System.out.println(localizedDesc(room.getRoomDesc()) + "\n");
		room.getRoomActions().forEach(System.out::println);
	};
	
	
	/**
	 * Display the actions that can be performed
	 * in the current room
	 * 
	 * @param room The room the player is currently in
	 */
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
//	public void refreshPlayerActionPrompt(Player player) {
//		ArrayList<String> playerActions = new ArrayList<String>();
//		
//		//For each item in the player's inventory, get the item's action
//		for(Item item : player.getInventory()) {
//			playerActions.add(item.getAction());
//		}
//		
//		//Set the value of playerActions
//		playerActionsPrompt = playerActions;
//	}
	
	/**
	 * Create the database for the game
	 * 
	 * @param rooms Array list of rooms
	 * @param items Array list of items
	 * @param interactables Array list of interactables
	 */
	//Create the content for the game
	public static void createGameContent(ArrayList<Room> rooms, ArrayList<Item> items, ArrayList<BaseInteractable> interactables) {
	//Loading the objects from the database into the gameManagement lists
	Database.createGameDatabase(itemList, interactableList, roomList);
	
	}

	/**
	 * Create the title screen to show when opening the game
	 */
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
	 * Method for localizing the game text
	 * 
	 * @param descKey Key for retrieving the localized text
	 * @return The localized text
	 */
//	//returns text based on language selected
	public String localizedDesc(String descKey)
	{
		Locale locale = player.getLocale();
		ResourceBundle rb = ResourceBundle.getBundle("Description", locale);
		return rb.getString(descKey);
	}
	
	public void checkTotems()
	{
		if(isNorthTotemPlaced == true && isEastTotemPlaced== true && isSouthTotemPlaced == true && isWestTotemPlaced == true)
		{
			unlockCryptDoor();
		}
	}
	
	public void unlockCryptDoor()
	{
		Room getRoom = null;
		Room setRoomExit = null;
		
		for(Room room : roomList) {
			if(room.getName().equalsIgnoreCase("Outer Garden Center")) {
				getRoom = room;
				
			}}
		for(Room room : roomList) {
			if(room.getName().equalsIgnoreCase("Crypt Entrance")) {
				setRoomExit = room;
				
			}}
		
		getRoom.setExit(Direction.DOWN, setRoomExit);
		getRoom.getRoomActions().add("Go Down");
		
	}
		
	
	/**
	 * Getter for environmentPrompt
	 * 
	 * @return the description of the current environment
	 */
	//Getter and setter for the text prompt, describing base details of the surrounding area
	public String getEnvironmentPrompt() {
		return environmentPrompt;
	}
	
	/**
	 * Setter for environmentPrompt
	 * 
	 * @param prompt Value to set
	 */
	public void setEnvironmentPrompt(String prompt) {
		environmentPrompt = prompt;
	}
	
	/**
	 * Getter for the information on the title screen
	 * 
	 * @return The information for the title screen
	 */ 
	//Getter and setter for the title screen text (logo, game name, ASCII art, etc.)
	public String getTitleScreenDesc() {
		return titleScreenDescription;
	}
	
	/**
	 * Setter for the information on the title screen
	 * 
	 * @param desc Value to set
	 */
	public void setTitleScreenDesc(String desc) {
		titleScreenDescription = desc;
	}
	
	/**
	 * Getter for the actions on the title screen
	 * 
	 * @return the actions on the title screen
	 */
	//Getter and setter for the title screen actions
	public ArrayList<String> getTitleScreenActions() {
		return titleScreenActions;
	}
	/**
	 * Setter for the actions on the title screen
	 * 
	 * @param titleScreenActions Value to set
	 */
	public void setTitleScreenActions(ArrayList<String> titleScreenActions) {
		GameManagement.titleScreenActions = titleScreenActions;
	}
	
	/**
	 * Getter for the room list
	 * 
	 * @return the list of rooms
	 */
	//Getter and setter for room list
	public static ArrayList<Room> getRoomList() {
		return GameManagement.roomList;
	}
	
	/**
	 * Setter for the room list
	 * 
	 * @param roomList Value to set
	 */
	public void setRoomList(ArrayList<Room> roomList) {
		GameManagement.roomList = roomList;
	}
	
	/**
	 * Getter for the item list
	 * 
	 * @return the list of items
	 */
	//Getter and setter for the item list
	public static ArrayList<Item> getItemList() {
		return GameManagement.itemList;
	}
	
	/**
	 * Setter for the item list
	 * 
	 * @param itemList Value to set
	 */
	public void setItemList(ArrayList<Item> itemList) {
		GameManagement.itemList = itemList;
	}
	
	/**
	 * Getter for the interactable list
	 * 
	 * @return the list of interactables
	 */
	//Getter and setter for interactable list
	public static ArrayList<BaseInteractable> getInteractables() {
		return GameManagement.interactableList;
	}
	
	/**
	 * Setter for the interactable list
	 * 
	 * @param interactableList Value to set
	 */
	public void setInteractableList(ArrayList<BaseInteractable> interactableList) {
		GameManagement.interactableList = interactableList;
	}
	
	/**
	 * Getter for the inputHandler
	 * 
	 * @return The inputHandler object
	 */
	public InputHandler getInputHandler() {
		return this.inputHandler;
	}
	
	public boolean getNorthTotemStatus()
	{
		return isNorthTotemPlaced;
	}
	public boolean getEastTotemStatus()
	{
		return isEastTotemPlaced;
	}
	public boolean getSouthTotemStatus()
	{
		return isSouthTotemPlaced;
	}
	public boolean getWestTotemStatus()
	{
		return isWestTotemPlaced;
	}
	
	public void setNorthTotemStatus(boolean value)
	{
		isNorthTotemPlaced = value;
		checkTotems();
	}
	public void setEastTotemStatus(boolean value)
	{
		isEastTotemPlaced = value;
		checkTotems();
	}
	public void setSouthTotemStatus(boolean value)
	{
		isSouthTotemPlaced = value;
		checkTotems();
	}
	public void setWestTotemStatus(boolean value)
	{
		isWestTotemPlaced = value;
		checkTotems();
	}
}
