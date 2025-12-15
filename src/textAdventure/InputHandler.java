package textAdventure;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

/**
 * Class for handling user input
 */
public class InputHandler {
	/**
	 * Fields
	 * 
	 * player: object for the player's status
	 * playerInventory: object for the player's inventory
	 */
	private final Player player;
	private final Inventory playerInventory;
	private GameManagement gameManagement;

	/**
	 * Constructor for InputHandler
	 * 
	 * @param player Object for the player's status
	 */
	public InputHandler(Player player, GameManagement gameManagement) {
		this.player = player;
		playerInventory = Player.getInventory();
		this.gameManagement = gameManagement;
	}

	/**
	 * Create a unary operator for taking input
	 */
	//Unary operator for parsing commands
	UnaryOperator<String> properInputFormat = input -> input.toUpperCase();

	/**
	 * Method that handles and executes player commands
	 * 
	 * @param input The user input
	 * @return true to keep the program going after this method
	 */
	//handles player commands
	public boolean handleCommand(String input) {
		
		
		//trim input and make uppercase to avoid capitalization issues
		String playerInput = input.trim();

		//ensure input is not empty
		if (playerInput.isEmpty()) {
			System.out.println("Please enter a command.");
			return true;
		}

		String[] parts = playerInput.split(" ", 2);
		String commandWord = parts[0];
		String argument = "";
		Commands command = Commands.getCommandFromString(commandWord);

		//make sure there is a noun
		if (parts.length > 1) {
			argument = parts[1].trim();
			
			//create instance of Command that uses a parameter
			switch (command) {				
				case GO:
				case ENTER:
				case LEAVE:
					movePlayer(command.toString(), argument.toUpperCase());
					break;				
				case EXAMINE:
					examineItem(argument.toUpperCase(), player.getIsViewingInventory());
					break;
				case GRAB:
					grabItem(argument.toUpperCase());
					break;
				case USE:
					useItem(argument.toUpperCase());
					break;
				case SAVE:
					saveGame((argument));
					break;
				default:
					System.out.println("I don't recognize that command.");
			}
		} else {
			//Commands that don't use a parameter
			switch (command) {
			case INVENTORY:
				player.setIsViewingInventory(true);
				System.out.println(playerInventory.displayAllInventory());
				break;
			case HELP:
				System.out.print("~~~~~~~~~\nGuide:\n\nMove rooms with 'go (direction)'\nTake items with 'grab (item)'\nInspect objects with 'examine (interactable)'\nSave your inventory items to a text file with 'save (filePath)'\n~~~~~~~~~\nCurrent Location (Scroll Up for Help):");
				GameManagement.displayRoom.accept(Player.getCurrentRoom());
				break;
			case QUIT:
				System.out.println("Game Closed!");
				System.exit(0);
				break;
			case BACK:
				player.setIsViewingInventory(false);
				player.getCurrentRoom().getRoomActions().forEach(System.out::println);
				break;
			default:
				System.out.println("I don't recognize that command.");
			}
		}



		return true;
	}

	/**
	 * Move the player to new area instead of direction
	 * 
	 * @param directionString The direction the player wishes to move in
	 */
	//handles player movement
	public void movePlayer(String command, String directionString) {
		Direction direction;
		if("LEAVE".equalsIgnoreCase(command)) {
			if(directionString.equalsIgnoreCase("CRYPT") && "Crypt Entrance".equals(Player.getCurrentRoom().getName())) {
				directionString = "UP";
			} else if (directionString.equalsIgnoreCase("Room")) {
				if(Player.getCurrentRoom().getName().equals("Artifact Room")) {
					directionString = "WEST";
				} else if(Player.getCurrentRoom().getName().equals("Spirit Room")) {
					directionString = "EAST";
				}
			} else {
				System.out.println("Oh... You're not leaving...");
			}  
			
			} else {
			if (directionString.equalsIgnoreCase("Crypt Door")) {
				if(player.completedQuest("Totem Quest")) {
					directionString = "DOWN";
				} else {
					System.out.println("I don't recognize that command");
					return;
				}
				
			} else if ("Crypt Entrance".equals(Player.getCurrentRoom().getName()) && directionString.equalsIgnoreCase("NORTH")) {
				if(player.getHasLitTorch()) {
					directionString = "NORTH";
				} else {
					System.out.println("You're too scared of the darkness to head that way... \n");
					GameManagement.displayRoom.accept(Player.getCurrentRoom());
					return;
				}
			} else if (directionString.equalsIgnoreCase("Outside Crypt")) {
				directionString = "UP";
			} else if (directionString.equalsIgnoreCase("Artifact Room") && Player.getCurrentRoom().getName().equals("Crypt Hallway")) {
				directionString = "EAST";
			} else if (directionString.equalsIgnoreCase("Spirit Room") && Player.getCurrentRoom().getName().equals("Crypt Hallway")) {
				directionString = "WEST";
			}
		}	
		try {
			direction = Direction.valueOf(properInputFormat.apply(directionString));
		} catch (IllegalArgumentException e) {
			System.out.println("That is not a valid direction. Valid Directions include: NORTH, EAST, SOUTH, WEST.");
			return;
		}

		Room currentRoom = Player.getCurrentRoom();
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom != null) {
			//check if player can have special actions
			player.setCurrentRoom(nextRoom);
			checkSpecialConditions(Player.getCurrentRoom());
			GameManagement.displayRoom.accept(nextRoom);
		} else {
			System.out.println("You can not travel that direction from here.");
		}
	}

	/**
	 * handles the TAKE command (TAKE ITEM_NAME)
	 * 
	 * @param itemName the item the player is trying to take
	 */
	public void takeItem(String itemName) {
		if (itemName.isEmpty()) {
			System.out.println("There is nothing here for the taking...");
			return;
		}

		//TODO: add to inventory if possible
	}

	/**
	 * handles the EXAMINE command (TAKE ITEM_NAME)
	 * 
	 * @param itemName the item the player is trying to examine
	 * @param isViewingInventory whether the player is viewing the inventory
	 * 	(affects which items are viewable)
	 */
	public void examineItem(String itemName, boolean isViewingInventory) {
		//if viewing inventory, then get list from inventory, otherwise from room
		BaseInteractable foundInteractable = null;
		
		//If the player is not viewing their inventory
		if(!isViewingInventory) 
		{	
			//List to store interactables in the player's current room
			ArrayList<BaseInteractable> interactableList = Player.getCurrentRoom().getInteractableList();
			
			//If an engraved rock exists in the room
			if(itemName.equalsIgnoreCase("ENGRAVED ROCK"))
			{
				//Find the engraved rock interactale
				EngravedRock engravedRock = interactableList.stream()
						.filter(i-> i instanceof EngravedRock)
						.map(i-> (EngravedRock) i)
						.findFirst()
						.orElse(null);
			//Display the description
				if (engravedRock != null)
				{
				 System.out.println("~~~~~ " +engravedRock.getName() + " ~~~~~");
					System.out.println(GameManagement.localizedDesc(engravedRock.getDesc()));
					System.out.println("~~~~~~~~~~~~~~~~~~~~\n");
				}
			}
			//Display item description
			else
			{
				for(BaseInteractable interactable : interactableList) 
				{
					if(interactable.getName().toUpperCase().contains(itemName)) 
					{
						foundInteractable = interactable;
						break;
					}
				}
				//Display a basic interactable
				if (foundInteractable != null) 
				{
					System.out.println("~~~~~ " +foundInteractable.getName() + " ~~~~~");
					System.out.println(GameManagement.localizedDesc(foundInteractable.getDesc()));
					System.out.println("~~~~~~~~~~~~~~~~~~~~\n");
					Player.getCurrentRoom().getRoomActions().forEach(System.out::println);
				} 
			}
		}
		//Display the player's inventory
		else {
			ArrayList<Item> itemList = (ArrayList<Item>) Inventory.getAllInventory();
			Item foundItem = null;
			
			for(Item item : itemList) {
				if(item.getName().toUpperCase().contains(itemName)) {
					foundItem = item;
					break;
				}
			}
			
			if (foundItem != null) {
				System.out.println("~~~~~ " + foundItem.getName() + " ~~~~~");
				System.out.println(foundItem.getDesc());
				System.out.println("~~~~~~~~~~~~~~~~~~~~\n");

				player.getCurrentRoom().getRoomActions().forEach(System.out::println);
				player.setIsViewingInventory(false);

				
			} else {
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
				System.out.println("There is nothing here for examination...");
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
			}			
		}
	}
	
	/**
	 * handles the GRAB command (GRAB ITEM_NAME)
	 * 
	 * @param itemName the item the player is trying to take
	 */
	public void grabItem(String itemName) {
		ArrayList<Item> itemList = Player.getCurrentRoom().getItemList();
				Item foundItem = null;

		for(Item item : itemList) {
			if(item.getName().toUpperCase().contains(itemName)) {
				foundItem = item;
				break;
			}
		}

		if (foundItem != null) {
			Player.getInventory().addItem(foundItem);
			Player.getCurrentRoom().removeItem(foundItem);
			Player.getCurrentRoom().removeRoomAction("GRAB " + itemName);
			checkSpecialConditions(Player.getCurrentRoom());
			System.out.println("~~~~~ " + foundItem.getName() + " ~~~~~");
			System.out.println(foundItem.getDesc() + "\n");
			Player.getCurrentRoom().getRoomActions().forEach(System.out::println);
		} else {
			System.out.println("There is nothing here for examination...");
		}
	}
	
	/**
	 * Handles saving the game
	 * 
	 * @param filePath the path for the location of the file
	 */
	public void saveGame(String filePath) {
		//Try catch in case the file path is not valid
		try {
			//Create the file with a path
			File saveFile = new File(filePath);
			
			//Create new buffered writer that casts a file writer
			try(FileWriter writer = new FileWriter(saveFile)) {
				//Loop through inventory items
				for(Item item : Inventory.getAllInventory()) {
					//Print out the name of each item
					writer.write(item.getName() + "\n");
				}
				
				//Save the file
				writer.flush();
				
			} catch (IOException io) {
				//Print issues about writing to the file
				System.out.println("There was an issue writing to an external file, please try again...");
				
			} finally {
				//Print file results info
				System.out.println("File Size: " + Files.size(saveFile.toPath()));
				System.out.println("File Saved!");
			}
		
		} catch (IOException e) {
			//Print out invalid file path
			System.out.println("The file path you tried to save to was invalid, please try again...");
		}
	}
	
//	public void examineInteractable(String examineObject)
//	{
//		System.out.println("Made it in examineInteractable");
//		System.out.println(examineObject);
//		String doorName = null;
//		if(examineObject.contains("door"))
//		{
//			doorName = examineObject;
//			examineObject = "door";
//		}
//		
//		switch(examineObject)
//		{
//		 case "pedestal":
//			 Pedestal pedestal = Player.getCurrentRoom().getInteractableList().stream()
//			 	.filter(i-> i instanceof Pedestal)
//			 	.map(i-> (Pedestal) i)
//			 	.findFirst()
//			 	.orElse(null);
//			 
//			 if (pedestal != null)
//			 {
//				 System.out.println(GameManagement.localizedDesc(pedestal.getDesc()));
//				 
//			 }
//			 break;
//		 case "engraved rock":
//			 System.out.println("here");
//			 EngravedRock engravedRock = Player.getCurrentRoom().getInteractableList().stream()
//			 .filter(i-> i instanceof EngravedRock)
//			 .map(i-> (EngravedRock) i)
//			 .findFirst()
//			 .orElse(null);
//			 
//			 if (engravedRock != null)
//			 {
//				 System.out.println(GameManagement.localizedDesc(engravedRock.getDesc()));
//			 }
//			 break;
//		 case "door":
//			 List<Door> doors = Player.getCurrentRoom().getInteractableList().stream()
//			 .filter(i-> i instanceof Door)
//			 .map(i -> (Door) i)
//			 .toList();
//			 
//			 //make a final version of door name so this stuff stops yelling at me
//			 final String finalDoorName = doorName;
//			 
//			 Door selectedDoor = doors.stream()
//					 .filter(i->i.getName().equalsIgnoreCase(finalDoorName))
//					 .findFirst()
//					 .orElse(null);
//			 
//			 if (selectedDoor != null)
//			 {
//				 System.out.println(GameManagement.localizedDesc(selectedDoor.getDesc()));
//			 }
//		}
//		
//	}

	/**
	 * handles the TAKE command (TAKE ITEM_NAME)
	 * 
	 * @param itemName the name of the item the player is taking
	 */
	public void useItem(String itemName) {
		ArrayList<Item> playerItemList = (ArrayList<Item>) Player.getInventory().getAllInventory();
		ArrayList<BaseInteractable> roomInteractableList = Player.getCurrentRoom().getInteractableList();
		ArrayList<BaseInteractable> allInteractables = GameManagement.getInteractables();
		
		Item foundItem = null;
		BaseInteractable foundInteractable = null;

		for(Item item : playerItemList) {
			if(item.getName().toUpperCase().contains(itemName)) {
				foundItem = item;
				break;
			}
		}
		
		String useItemOn = "";
		if (foundItem != null) {
			switch (foundItem.getName().toUpperCase()) {
				case "WHEAT TOTEM":
					useItemOn = "WEST PEDESTAL";
					break;
				case "FISH TOTEM":
					useItemOn = "NORTH PEDESTAL";
					break;
				case "SWORD TOTEM":
					useItemOn = "SOUTH PEDESTAL";
					break;
				case "LUMBER TOTEM":
					useItemOn = "EAST PEDESTAL";
					break;
				case "TORCH":
					player.setHasLitTorch(true);
					System.out.println("~~~~~The torch has been lit~~~~~");
					System.out.println("It burns brightly.\n");
					Player.getCurrentRoom().removeRoomAction("Use Torch");
					Player.getCurrentRoom().getRoomActions().forEach(System.out::println);
					return;
				default:
					System.out.println("You can't use that item now...");
			}
			
			//find pedestal 
			for(BaseInteractable interactable : roomInteractableList) 
			{
				if(interactable.getName().toUpperCase().equals(useItemOn)) 
				{
					foundInteractable = interactable;
					break;
				}
			}
			
			if (foundInteractable != null) {
				if (foundInteractable instanceof Pedestal) {
					Pedestal pedestal = (Pedestal) foundInteractable;
					//set item on pedestal
					pedestal.setPedestalItem(foundItem);
					//remove from inventory
					Player.getInventory().removeItem(foundItem);
					
					//remove special action from room
					checkSpecialConditions(Player.getCurrentRoom());
					
					System.out.println("~~~~~" + foundItem.getName() + " Used~~~~~");
					System.out.println("The " + foundItem.getName() + " was set on the " + pedestal.getName() + "!\n");
					
					
					//if all pedestals are done, send a special message
					int completedPedestals = 0;
					for (BaseInteractable interactable : allInteractables) {
						if (interactable instanceof Pedestal) {
							Pedestal currentPedestal = (Pedestal) interactable;
							if(currentPedestal.getPedestalHasItem()) {
								completedPedestals++;
							}							
						}
					}
					if(completedPedestals >=4) {
						player.setQuestComplete("Totem Quest");
						System.out.println("!!!A beacon of light shoots into the sky. It's coming from the crypt entrance.!!!\n");
						
					}
					Player.getCurrentRoom().getRoomActions().forEach(System.out::println);
				}
			}

		} else {
			System.out.println("You do not possess that item...");
		}

	}
	
	/**
	 * Method for checking if the next room has a special condition
	 * 
	 * @param currentRoom The next room the player is trying to enter
	 */
	private void checkSpecialConditions(Room currentRoom) {
		String specialConditionVerb = currentRoom.getSpecialConditionVerb();
		String specialConditionNoun = currentRoom.getSpecialConditionNoun();
		
		if(specialConditionVerb == null || specialConditionVerb.isEmpty()) {
			return;
		}
		
		if(specialConditionNoun == null || specialConditionNoun.isEmpty()) {
			return;
		}
		
		boolean conditionMet = false;
		
		switch (specialConditionVerb) {
			case "Has":
				if(player.hasItem(specialConditionNoun.toUpperCase())) {
					currentRoom.addRoomAction(currentRoom.getSpecialAction());
				} else {
					currentRoom.removeRoomAction(currentRoom.getSpecialAction());
				}
				break;
			case "Completed":
				if(player.completedQuest(specialConditionNoun)) {
					currentRoom.addRoomAction(currentRoom.getSpecialAction());
				}
				break;
		}
		
	}
	
}