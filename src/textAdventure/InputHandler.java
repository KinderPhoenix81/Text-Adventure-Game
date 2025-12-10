package textAdventure;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public class InputHandler {
	private final Player player;
	
	public InputHandler(Player player) {
		this.player = player;
	}
	
	//Unary operator for parsing commands
	UnaryOperator<String> properInputFormat = input -> input.toUpperCase();
	
	//handles player commands
	public boolean handleCommand(String input) {
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
		
		//make sure there is a noun
		if (parts.length > 1) {
			argument = parts[1].trim();
		} else {
			System.out.println("???");
		}
		
		//create instance of Command
		Commands command = Commands.getCommandFromString(commandWord);
		
		switch (command) {
			case GO:
				movePlayer(argument);
				break;
			case INSPECT:
				inspectItem(argument);
			
			default:
				System.out.println("I don't recognize that command.");
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
	 * handles the INSPECT command (TAKE ITEM_NAME)
	 */
	public void inspectItem(String itemName) {
		System.out.println(itemName);
		ArrayList<BaseInteractable> interactableList = GameManagement.getInteractables();
		BaseInteractable foundInteractable = null;
		
		for(BaseInteractable interactable : interactableList) {
			if(interactable.getName().equalsIgnoreCase(itemName)) {
				foundInteractable = interactable;
				break;
			}
		}
		
		if (foundInteractable != null) {
			System.out.println(foundInteractable.getDesc());
		} else {
			System.out.println("There is nothing here for inspection...");
		}
				//TODO: display item description
	}
	
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
}
