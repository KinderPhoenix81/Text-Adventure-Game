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

		/*
		 * Create the north gardens room
		 */
		Room northGardens = new Room();
	
		//Create lists for the actions, items, and interactables in the room
		ArrayList<String> ngRoomActions = new ArrayList<>(List.of("Move South", "Inspect Pedestal", "Inspect Rock Engravings", "Pick up Wheat Totem"));
		
		ArrayList<Item> ngRoomItems = new ArrayList<>();
		Item wheatTotem = new Item("Wheat Totem", "Wheat Totem desc...", "Wheat totem lore...", "Place Totem");
		ngRoomItems.add(wheatTotem);
		
		ArrayList<BaseInteractable> ngRoomInteractables = new ArrayList<>();
		Pedestal northGardensPedestal = new Pedestal("Pedestal", "North Pedestal desc..", "North Pedestal lore...", wheatTotem);
		ngRoomInteractables.add(northGardensPedestal);
		
		//Set the values of the north gardens
		northGardens.setName("North Gardens");
		northGardens.setDesc("North Gardens desc...");
		northGardens.setRoomActions(ngRoomActions);
		northGardens.setItemList(ngRoomItems);
		northGardens.setInteractableList(ngRoomInteractables);
		
		//Add the individual items to the overall game lists
		gameRooms.add(northGardens);
		gameItems.add(wheatTotem);
		gameInteractables.add(northGardensPedestal);
		
		/*
		 * Create the east gardens room
		 */
		Room eastGardens = new Room();
		
		//Lists for actions, items, and interactables in this room
		ArrayList<String> egRoomActions = new ArrayList<>(List.of("Move West", "Inspect Pedestal", "Inspect Rock Engravings", "Pick up Sword Totem"));
		
		ArrayList<Item> egRoomItems = new ArrayList<>();
		Item swordTotem = new Item("Sword Totem", "Sword Totem desc...", "Sword totem lore...", "Place Totem");
		egRoomItems.add(swordTotem);
		
		ArrayList<BaseInteractable> egRoomInteractables = new ArrayList<>();
		Pedestal eastGardensPedestal = new Pedestal("Pedestal", "East Pedestal desc..", "East Pedestal lore...", swordTotem);
		egRoomInteractables.add(eastGardensPedestal);
		
		//Values of east gardens
		eastGardens.setName("East Gardens");
		eastGardens.setDesc("East Gardens desc...");
		eastGardens.setRoomActions(egRoomActions);
		eastGardens.setItemList(egRoomItems);
		eastGardens.setInteractableList(egRoomInteractables);
		
		//Add the individual items to the overall game lists
		gameRooms.add(eastGardens);
		gameItems.add(swordTotem);
		gameInteractables.add(eastGardensPedestal);
		
		/*
		 * Create the south gardens room
		 */
		Room southGardens = new Room();
		
		//Lists for actions, items, and interactables in this room
		ArrayList<String> sgRoomActions = new ArrayList<>(List.of("Move North", "Inspect Pedestal", "Inspect Rock Engravings", "Pick up Fish Totem"));
		
		ArrayList<Item> sgRoomItems = new ArrayList<>();
		Item fishTotem = new Item("Fish Totem", "Fish Totem desc...", "Fish totem lore...", "Place Totem");
		sgRoomItems.add(fishTotem);
		
		ArrayList<BaseInteractable> sgRoomInteractables = new ArrayList<>();
		Pedestal southGardensPedestal = new Pedestal("Pedestal", "South Pedestal desc..", "South Pedestal lore...", fishTotem);
		sgRoomInteractables.add(southGardensPedestal);
		
		//Values of east gardens
		southGardens.setName("South Gardens");
		southGardens.setDesc("South Gardens desc...");
		southGardens.setRoomActions(sgRoomActions);
		southGardens.setItemList(sgRoomItems);
		southGardens.setInteractableList(sgRoomInteractables);
		
		//Add the individual items to the overall game lists
		gameRooms.add(southGardens);
		gameItems.add(fishTotem);
		gameInteractables.add(southGardensPedestal);
		
		/*
		 * Create the west gardens room
		 */
		Room westGardens = new Room();
		
		//Lists for actions, items, and interactables in this room
		ArrayList<String> wgRoomActions = new ArrayList<>(List.of("Move East", "Inspect Pedestal", "Inspect Rock Engravings", "Pick up Lumber Totem"));
		
		ArrayList<Item> wgRoomItems = new ArrayList<>();
		Item lumberTotem = new Item("Lumber Totem", "Lumber Totem desc...", "Lumber totem lore...", "Place Totem");
		wgRoomItems.add(lumberTotem);
		
		ArrayList<BaseInteractable> wgRoomInteractables = new ArrayList<>();
		Pedestal westGardensPedestal = new Pedestal("Pedestal", "West Pedestal desc..", "West Pedestal lore...", lumberTotem);
		wgRoomInteractables.add(westGardensPedestal);
		
		//Values of east gardens
		westGardens.setName("West Gardens");
		westGardens.setDesc("West Gardens desc...");
		westGardens.setRoomActions(wgRoomActions);
		westGardens.setItemList(wgRoomItems);
		westGardens.setInteractableList(wgRoomInteractables);
		
		//Add the individual items to the overall game lists
		gameRooms.add(westGardens);
		gameItems.add(lumberTotem);
		gameInteractables.add(westGardensPedestal);
		
		/*
		 * Create the inner crypt entrance room
		 */
		Room innerCryptEntrance = new Room();
		
		//Lists for actions, items, and interactables in this room
		ArrayList<String> iceRoomActions = new ArrayList<>(List.of("Move North", "Exit Crypt", "Grab Torch"));
		
		ArrayList<Item> iceRoomItems = new ArrayList<>();
		Item torch = new Item("Torch", "Torch desc...", "Torch lore...", "Use Torch");
		iceRoomItems.add(torch);
		
		ArrayList<BaseInteractable> iceRoomInteractables = new ArrayList<>();
		Door cryptEntranceDoor = new Door("Crypt Entrance Door", "Crypt Entrance Door desc...", "Crypt Entrance Door lore...", true);
		iceRoomInteractables.add(cryptEntranceDoor);
		
		//Values of east gardens
		innerCryptEntrance.setName("Crypt Entrance (Inner)");
		innerCryptEntrance.setDesc("Crypt Entrance desc...");
		innerCryptEntrance.setRoomActions(iceRoomActions);
		innerCryptEntrance.setItemList(iceRoomItems);
		innerCryptEntrance.setInteractableList(iceRoomInteractables);
		
		//Add the individual items to the overall game lists
		gameRooms.add(innerCryptEntrance);
		gameItems.add(torch);
		gameInteractables.add(cryptEntranceDoor);
		
		/*
		 * Create the dark rooms of the crypt, A through F
		 */
		//Cell A
		Room darkRoomCellA = new Room();
		
		ArrayList<String> cellAActions = new ArrayList<>(List.of("Move East", "Move South"));
		
		ArrayList<Item> cellAItems = new ArrayList<>();
		Item healthPotion_1 = new Item("Health Potion", "Health Potion desc...", "Health Potion lore...", "Use Health Potion");
		cellAItems.add(healthPotion_1);
		
		darkRoomCellA.setName("Inner Crypt");
		darkRoomCellA.setDesc("Inner Crypt desc...");
		darkRoomCellA.setRoomActions(cellAActions);
		darkRoomCellA.setItemList(cellAItems);
		darkRoomCellA.setInteractableList(null);
		
		gameRooms.add(darkRoomCellA);
		gameItems.add(healthPotion_1);
		
		//Cell B
		Room darkRoomCellB = new Room();
		
		ArrayList<String> cellBActions = new ArrayList<>(List.of("Move North", "Move East", "Move South", "Move West"));
		
		darkRoomCellB.setName("Inner Crypt");
		darkRoomCellB.setDesc("Inner Crypt desc...");
		darkRoomCellB.setRoomActions(cellBActions);
		darkRoomCellB.setItemList(null);
		darkRoomCellB.setInteractableList(null);
		
		gameRooms.add(darkRoomCellB);
		
		//Cell C
		Room darkRoomCellC = new Room();
		
		ArrayList<String> cellCActions = new ArrayList<>(List.of("Move South", "Move West"));
		
		darkRoomCellC.setName("Inner Crypt");
		darkRoomCellC.setDesc("Inner Crypt desc...");
		darkRoomCellC.setRoomActions(cellCActions);
		darkRoomCellC.setItemList(null);
		darkRoomCellC.setInteractableList(null);
		
		gameRooms.add(darkRoomCellC);
		
		//Cell D
		Room darkRoomCellD = new Room();
		
		ArrayList<String> cellDActions = new ArrayList<>(List.of("Move North", "Move East"));
		
		darkRoomCellD.setName("Inner Crypt");
		darkRoomCellD.setDesc("Inner Crypt desc...");
		darkRoomCellD.setRoomActions(cellDActions);
		darkRoomCellD.setItemList(null);
		darkRoomCellD.setInteractableList(null);
	
		gameRooms.add(darkRoomCellD);
		
		//Cell E
		Room darkRoomCellE = new Room();
		
		ArrayList<String> cellEActions = new ArrayList<>(List.of("Move North", "Move East", "Move South", "Move West"));
		
		darkRoomCellE.setName("Inner Crypt");
		darkRoomCellE.setDesc("Inner Crypt desc...");
		darkRoomCellE.setRoomActions(cellEActions);
		darkRoomCellE.setItemList(null);
		darkRoomCellE.setInteractableList(null);
		
		gameRooms.add(darkRoomCellE);
		
		//Cell F
		Room darkRoomCellF = new Room();
		
		ArrayList<String> cellFActions = new ArrayList<>(List.of("Move North", "Move West"));
		
		ArrayList<Item> cellFItems = new ArrayList<>();
		Item healthPotion_2 = new Item("Health Potion", "Health Potion desc...", "Health Potion lore...", "Use Health Potion");
		cellAItems.add(healthPotion_2);
		
		darkRoomCellF.setName("Inner Crypt");
		darkRoomCellF.setDesc("Inner Crypt desc...");
		darkRoomCellF.setRoomActions(cellFActions);
		darkRoomCellF.setItemList(cellFItems);
		darkRoomCellF.setInteractableList(null);
		
		gameRooms.add(darkRoomCellF);
		gameItems.add(healthPotion_2);
		
		//Create crypt hallway
		Room cryptHallway = new Room();
		
		ArrayList<String> cryptHallwayActions = new ArrayList<>(List.of("Move North", "Move South"));
		
		cryptHallway.setName("Crypt Hallway");
		cryptHallway.setDesc("Crypt Hallway desc...");
		cryptHallway.setRoomActions(cryptHallwayActions);
		cryptHallway.setItemList(null);
		cryptHallway.setInteractableList(null);
		
		gameRooms.add(cryptHallway);
		
		//Create crypt split hallway
		Room cryptSplitHallway = new Room();
		
		ArrayList<String> cryptSplitHallwayActions = new ArrayList<>(List.of("Move South", "Open Gilded Door", "Open Ominous Door"));
		
		ArrayList<BaseInteractable> cryptSplitHallwayInteractables = new ArrayList<>();
		Door artifactDoor = new Door("Artifact Door", "Artifact Door desc", "Artifact Door lore");
		Door spiritDoor = new Door("Spirit Door", "Spirit Door desc", "Spirit Door lore");
		cryptSplitHallwayInteractables.add(artifactDoor);
		cryptSplitHallwayInteractables.add(spiritDoor);
		
		cryptSplitHallway.setName("Crypt Split Path");
		cryptSplitHallway.setDesc("Crypt Split Path desc...");
		cryptSplitHallway.setRoomActions(cryptSplitHallwayActions);
		cryptSplitHallway.setItemList(null);
		cryptSplitHallway.setInteractableList(cryptSplitHallwayInteractables);
		
		gameRooms.add(cryptSplitHallway);
		gameInteractables.add(artifactDoor);
		gameInteractables.add(spiritDoor);

		//Create artifact room
		Room artifactRoom = new Room();
		
		ArrayList<String> artifactRoomActions = new ArrayList<>(List.of("Move East", "Take Artifact"));
		
		ArrayList<Item> artifactRoomItems = new ArrayList<>();
		Item goldenArtifact = new Item("Golden Artifact", "Golden Artifact desc...", "Golden Artifact lore...", "???");
		cellAItems.add(goldenArtifact);
		
		artifactRoom.setName("Artifact Room");
		artifactRoom.setDesc("Artifact Room desc...");
		artifactRoom.setRoomActions(artifactRoomActions);
		artifactRoom.setItemList(artifactRoomItems);
		artifactRoom.setInteractableList(null);
		
		gameRooms.add(artifactRoom);
		gameItems.add(goldenArtifact);
		
		//Create spirit room
		Room spiritRoom = new Room();
		
		ArrayList<String> spiritRoomActions = new ArrayList<>(List.of("Move West", "Break Vase"));
		
		ArrayList<Item> spiritRoomItems = new ArrayList<>();
		Item spiritVase = new Item("Vase of Spirits", "Vase of Spirits desc...", "Vase of Spirits lore...", "Shatter");
		cellAItems.add(spiritVase);
		
		spiritRoom.setName("Spirit Room");
		spiritRoom.setDesc("Spirit Room desc...");
		spiritRoom.setRoomActions(spiritRoomActions);
		spiritRoom.setItemList(spiritRoomItems);
		spiritRoom.setInteractableList(null);
		
		gameRooms.add(spiritRoom);
		gameItems.add(spiritVase);
		
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
