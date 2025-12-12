package textAdventure;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class InputHandler {
	private final Player player;
	private final Inventory playerInventory;

	public InputHandler(Player player) {
		this.player = player;
		playerInventory = player.getInventory();
	}

	//Unary operator for parsing commands
	UnaryOperator<String> properInputFormat = input -> input.toUpperCase();

	//handles player commands
	public boolean handleCommand(String input) {
		//set this to false as default
		player.setIsViewingInventory(false);
		
		//trim input and make uppercase to avoid capitalization issues
		String playerInput = input.trim().toUpperCase();

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
			
			//create instance of Command
			switch (command) {
				case GO:
					movePlayer(argument);
					break;
				case EXAMINE:
					examineItem(argument, player.getIsViewingInventory());
					break;
				case GRAB:
					grabItem(argument);
					break;
				default:
					System.out.println("I don't recognize that command.");
			}
			
		} else {
			switch (command) {
			case INVENTORY:
				player.setIsViewingInventory(true);
				System.out.println(playerInventory.displayAllInventory());
				break;
			default:
				System.out.println("I don't recognize that command.");
			}
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

	/*
	 * handles the TAKE command (TAKE ITEM_NAME)
	 */
	public void takeItem(String itemName) {
		if (itemName.isEmpty()) {
			System.out.println("There is nothing here for the taking...");
			return;
		}

		//TODO: add to inventory if possible
	}

	/*
	 * handles the EXAMINE command (TAKE ITEM_NAME)
	 */
	public void examineItem(String itemName, boolean isViewingInventory) {
		//if viewing inventory, then get list from inventory, otherwise from room
		BaseInteractable foundInteractable = null;
		
		if(!isViewingInventory) {		
			ArrayList<BaseInteractable> interactableList = player.getCurrentRoom().getInteractableList();
			
			for(BaseInteractable interactable : interactableList) {
				if(interactable.getName().toUpperCase().contains(itemName)) {
					foundInteractable = interactable;
					break;
				}
			}
		}

		if (foundInteractable != null) {
			System.out.println("~~~~~ " +foundInteractable.getName() + " ~~~~~");
			System.out.println(foundInteractable.getLore());
			System.out.println("~~~~~~~~~~~~~~~~~~~~\n");
			player.getCurrentRoom().getRoomActions().forEach(System.out::println);
			
		} else {
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
				System.out.println(foundItem.getLore());
				System.out.println("~~~~~~~~~~~~~~~~~~~~\n");
				player.getCurrentRoom().getRoomActions().forEach(System.out::println);
				
			} else {
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
				System.out.println("There is nothing here for examination...");
				System.out.println("~~~~~~~~~~~~~~~~~~~~");
			}			
		}
	}
	
	/*
	 * handles the GRAB command (GRAB ITEM_NAME)
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
			System.out.println(foundItem.getLore() + "\n");
			player.getCurrentRoom().getRoomActions().forEach(System.out::println);
		} else {
			System.out.println("There is nothing here for examination...");
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

	/*
	 * handles the TAKE command (TAKE ITEM_NAME)
	 */
	public void useItem(String itemName) {
		//TODO: 
		if (itemName.isEmpty()) {
			System.out.println("There is nothing here for the taking...");
			return;
		}

		//TODO: make use of the item
	}
	
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
				}
				
		}
		
	}
	
}