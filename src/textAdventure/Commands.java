package textAdventure;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum of the various commands the user can enter
 */
public enum Commands {
	
	/**
	 * The various commands the user can enter
	 * 
	 * GO: Handles movement
	 * GRAB: Pick up an item and add it to inventory
	 * EXAMINE: Look at a point of interest
	 * USE: Use an item
	 * INVENTORY: Display the player's inventory
	 * HELP: Displays supplementary information
	 * QUIT: Exit the game
	 * UNKNOWN: 'error message' for invalid commands
	 */
	GO("go", 1, "MOVE"), //takes direction
	GRAB("grab", 1, "GET_ITEM"), //takes item
	EXAMINE("examine", 1, "LOOK_AT"), //takes item
	USE("use", 2, "USE_ITEM"), //takes item and something to interact with
	INVENTORY("inventory", 0, "CHECK_INVENTORY"), //no params
	HELP("help", 0, "SHOW_HELP"), // no params
	QUIT("quit", 0, "EXIT_GAME"), // no params
	UNKNOWN("unknown", 0, "INVALID_COMMAND"); // no params
	
	/**
	 * Fields for the methods below
	 */
	private final String commandText;
	private final int argumentCount;
	private final String internalAction;
	private static final Map<String, Commands> commandMap = new HashMap<>();
	
	/**
	 * A static method that loads the command map
	 */
	//runs one time when commands are loaded to fill map
	static {
		for (Commands command : Commands.values()) {
			commandMap.put(command.commandText, command);
		}
	}
	
	/**
	 * Constructor to make the enum
	 * 
	 * @param commandText
	 * @param argumentCount
	 * @param internalAction
	 */
	//create each enum
	private Commands(String commandText, int argumentCount, String internalAction) {
		this.commandText = commandText;
		this.argumentCount = argumentCount;
		this.internalAction = internalAction;
	}
	
	/**
	 * Getter for commandText
	 * 
	 * @return commandText
	 */
	public String getCommandText() {
		return commandText;
	}
	
	/**
	 * Getter for argumentCount
	 * 
	 * @return argumentCount
	 */
	public int getArgumentCount() {
		return argumentCount;
	}
	
	/**
	 * Getter for internalAction
	 * 
	 * @return internalAction
	 */
	public String getInternalAction() {
		return internalAction;
	}
	
	/**
	 * Parse the user input to a command from the list
	 * 
	 * @param input user input
	 * @return a command from the command map
	 */
	//handle the command
	public static Commands getCommandFromString(String input) {
		String cleanInput = input.trim().toLowerCase();
		return commandMap.getOrDefault(cleanInput, UNKNOWN);
	}

}
