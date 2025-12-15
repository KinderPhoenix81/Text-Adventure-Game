package textAdventure;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
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
	public InputHandler(Player player) {
		this.player = player;
		playerInventory = player.getInventory();
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
		//set this to false as default
		player.setIsViewingInventory(false);
		
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
					movePlayer(argument.toUpperCase());
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
			default:
				System.out.println("I don't recognize that command.");
			}
		}



		return true;
	}

	/**
	 * Move the player
	 * 
	 * @param directionString The direction the player wishes to move in
	 */
	//handles player movement
	public void movePlayer(String directionString) {
		Direction direction;
		try {
			direction = Direction.valueOf(properInputFormat.apply(directionString));
		} catch (IllegalArgumentException e) {
			System.out.println("That is not a valid direction. Valid Directions include: NORTH, EAST, SOUTH, WEST.");
			return;
		}

		Room currentRoom = this.player.getCurrentRoom();
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom != null) {
			//check if player can have special actions
			checkSpecialConditions(nextRoom);
			player.setCurrentRoom(nextRoom);
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
		
		if(!isViewingInventory) 
		{		
			ArrayList<BaseInteractable> interactableList = player.getCurrentRoom().getInteractableList();
			
			if(itemName.equalsIgnoreCase("ENGRAVED ROCK"))
			{
			
				
				EngravedRock engravedRock = interactableList.stream()
						.filter(i-> i instanceof EngravedRock)
						.map(i-> (EngravedRock) i)
						.findFirst()
						.orElse(null);
			 
				if (engravedRock != null)
				{
				 System.out.println("~~~~~ " +engravedRock.getName() + " ~~~~~");
					System.out.println(GameManagement.localizedDesc(engravedRock.getDesc()));
					System.out.println("~~~~~~~~~~~~~~~~~~~~\n");
				 
				}
			}
		
			
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
			
				
				if (foundInteractable != null) 
				{
					System.out.println("~~~~~ " +foundInteractable.getName() + " ~~~~~");
					System.out.println(GameManagement.localizedDesc(foundInteractable.getDesc()));
					System.out.println("~~~~~~~~~~~~~~~~~~~~\n");
					player.getCurrentRoom().getRoomActions().forEach(System.out::println);
				} 
			}
		}

		
		else {
			ArrayList<Item> itemList = (ArrayList<Item>) player.getInventory().getAllInventory();
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
		ArrayList<Item> itemList = player.getCurrentRoom().getItemList();
				Item foundItem = null;

		for(Item item : itemList) {
			if(item.getName().toUpperCase().contains(itemName)) {
				foundItem = item;
				break;
			}
		}

		if (foundItem != null) {
			player.getInventory().addItem(foundItem);
			player.getCurrentRoom().removeItem(foundItem);
			player.getCurrentRoom().removeRoomAction("GRAB " + itemName);
			System.out.println("~~~~~ " + foundItem.getName() + " ~~~~~");
			System.out.println(foundItem.getDesc() + "\n");
			player.getCurrentRoom().getRoomActions().forEach(System.out::println);
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
		ArrayList<Item> playerItemList = (ArrayList<Item>) player.getInventory().getAllInventory();
		ArrayList<BaseInteractable> roomInteractableList = player.getCurrentRoom().getInteractableList();
		ArrayList<BaseInteractable> allInteractables = gameManagement.getInteractables();
		
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
					player.getInventory().removeItem(foundItem);
					
					//remove special action from room
					checkSpecialConditions(player.getCurrentRoom());
					
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
						System.out.println("!!!A beacon of light shoots into the sky. It's coming from the crypt entrance.!!!\n");
						
					}
					player.getCurrentRoom().getRoomActions().forEach(System.out::println);
				}
			}

		} else {
			System.out.println("You do not possess that item...");
		}

	}
	
	/**
	 * Method for checking if the next room has a special condition
	 * 
	 * @param nextRoom The next room the player is trying to enter
	 */
	private void checkSpecialConditions(Room nextRoom) {
		String specialConditionVerb = nextRoom.getSpecialConditionVerb();
		String specialConditionNoun = nextRoom.getSpecialConditionNoun();
		
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
					nextRoom.addRoomAction(nextRoom.getSpecialAction());
				} else {
					nextRoom.removeRoomAction(nextRoom.getSpecialAction());
				}
				break;
			case "Completed":
				if(player.completedQuest(specialConditionNoun)) {
					nextRoom.addRoomAction(nextRoom.getSpecialAction());
				}
				break;
		}
		
	}
	
}