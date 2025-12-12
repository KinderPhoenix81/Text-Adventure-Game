package textAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class GameManagement {
	//Class to manage the game functions
	
	/**
	 * Fields of the GameManagementClass
	 * 
	 */
	private static ArrayList<Room> roomList;
	private static ArrayList<Item> itemList;
	private static ArrayList<BaseInteractable> interactableList;
	private static Player player;
	
	//Provides information about the current environment, compiling all information about a room
	private String environmentPrompt;
	
	//Provides information about actions that can be taken in the environment
	private ArrayList<String> actionPrompt;
	
	//Provides base information about basic actions
	private ArrayList<String> playerActionsPrompt;
	
	//Initial fields for the start menu / title screen
	private static String titleScreenDescription;
	private static ArrayList<String> titleScreenActions;
	
	//create new InputHandler
	private final InputHandler inputHandler;
	
	/**
	 * Constructor
	 * 
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
		this.inputHandler = new InputHandler(player);
		
		//Show the starting room
		displayRoom.accept(Player.getCurrentRoom());
	}
	
	
	/**
	 * Main methods of the game management class
	 * 
	 */
	//Set the text of a room, it's lore, description, and all actions
	public static Consumer<Room> displayRoom = room -> {
		System.out.println("\n");
		System.out.println("~~~ " + room.getName() + " ~~~\n");
		System.out.println(localizedDesc(room.getRoomDesc()) + "\n");
		room.getRoomActions().forEach(System.out::println);
	};
	
	
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
	
	//Create the content for the game
	public static void createGameContent(ArrayList<Room> rooms, ArrayList<Item> items, ArrayList<BaseInteractable> interactables) {
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
//<<<<<<< Updated upstream
//=======
	
	//Unary operator for parsing commands
	UnaryOperator<String> properInputFormat = input -> input.toUpperCase();
	
	//handles player commands
	public boolean handleCommand(String input) {
		String[] parts = input.toLowerCase().split(" ", 2);
		String commandWord = parts[0];
		String argument = parts[1];
		
		switch (commandWord) {
			case "go":
				movePlayer(argument);
				break;
			case "examine":
				examineInteractable(argument);
				break;
		}
		
		return true;
	}
	
	//handles player movement
	public void movePlayer(String directionString) {
		Direction direction;
		try {
			direction = Direction.valueOf(properInputFormat.apply(directionString));
		} catch (IllegalArgumentException e) {
			System.out.println("That is not a valid direction. Valid Directions include: NORTH, EAST, SOUTH, WEST.");
			return;
		}
		
		Room currentRoom = player.getCurrentRoom();
		Room nextRoom = currentRoom.getExit(direction);
		
		if (nextRoom != null) {
			player.setCurrentRoom(nextRoom);
			displayRoom.accept(nextRoom);
		} else {
			System.out.println("You can not travel that direction from here.");
		}
		
		
	}
	//examine interactable objects, displays descriptions
	public void examineInteractable(String examineObject)
	{
		String doorName = null;
		if(examineObject.contains("door"))
		{
			doorName = examineObject;
			examineObject = "door";
		}
		
		switch(examineObject)
		{
		 case "pedestal":
			 Pedestal pedestal = Player.getCurrentRoom().getInteractableList().stream()
			 	.filter(i-> i instanceof Pedestal)
			 	.map(i-> (Pedestal) i)
			 	.findFirst()
			 	.orElse(null);
			 
			 if (pedestal != null)
			 {
				 System.out.println(localizedDesc(pedestal.getDesc()));
				 
			 }
			 break;
		 case "engraved rock":
			 EngravedRock engravedRock = Player.getCurrentRoom().getInteractableList().stream()
			 .filter(i-> i instanceof EngravedRock)
			 .map(i-> (EngravedRock) i)
			 .findFirst()
			 .orElse(null);
			 
			 if (engravedRock != null)
			 {
				 System.out.println(localizedDesc(engravedRock.getDesc()));
			 }
			 break;
		 case "door":
			 List<Door> doors = Player.getCurrentRoom().getInteractableList().stream()
			 .filter(i-> i instanceof Door)
			 .map(i -> (Door) i)
			 .toList();
			 
			 //make a final version of door name so this stuff stops yelling at me
			 final String finalDoorName = doorName;
			 
			 Door selectedDoor = doors.stream()
					 .filter(i->i.getName().equalsIgnoreCase(finalDoorName))
					 .findFirst()
					 .orElse(null);
			 
			 if (selectedDoor != null)
			 {
				 System.out.println(localizedDesc(selectedDoor.getDesc()));
			 }
		}
		
	}
	
	//returns text based on language selected
	public static String localizedDesc(String descKey)
	{
		Locale locale = player.getLocale();
		ResourceBundle rb = ResourceBundle.getBundle("Description", locale);
		return rb.getString(descKey);
	}
	
	
//>>>>>>> Stashed changes
		
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
	
	//Getter and setter for room list
	public static ArrayList<Room> getRoomList() {
		return GameManagement.roomList;
	}
	
	public void setRoomList(ArrayList<Room> roomList) {
		GameManagement.roomList = roomList;
	}
	
	//Getter and setter for the item list
	public static ArrayList<Item> getItemList() {
		return GameManagement.itemList;
	}
	
	public void setItemList(ArrayList<Item> itemList) {
		GameManagement.itemList = itemList;
	}
	
	//Getter and setter for interactable list
	public static ArrayList<BaseInteractable> getInteractables() {
		return GameManagement.interactableList;
	}
	
	public void setInteractableList(ArrayList<BaseInteractable> interactableList) {
		GameManagement.interactableList = interactableList;
	}
	
	public InputHandler getInputHandler() {
		return this.inputHandler;
	}
}
