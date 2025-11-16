package textAdventure;

import java.util.HashMap;
import java.util.Map;

public enum Commands {
	
	GO("go", 1, "MOVE"), //takes direction
	TAKE("take", 1, "GET_ITEM"), //takes itmeem
	EXAMINE("examine", 1, "LOOK_AT"), //takes item
	USE("use", 2, "USE_ITEM"), //takes item and something to interact with
	INVTENTORY("inventory", 0, "CHECK_INVENTORY"), //no params
	HELP("help", 0, "SHOW_HELP"), // no params
	QUIT("quit", 0, "EXIT_GAME"), // no params
	UNKNOWN("unknown", 0, "INVALID_COMMAND"); // no params
	
	private final String commandText;
	private final int argumentCount;
	private final String internalAction;
	private static final Map<String, Commands> commandMap = new HashMap<>();
	
	//runs one time when commands are loaded to fill map
	static {
		for (Commands command : Commands.values()) {
			commandMap.put(command.commandText, command);
		}
	}
	
	//create each enum
	private Commands(String commandText, int argumentCount, String internalAction) {
		this.commandText = commandText;
		this.argumentCount = argumentCount;
		this.internalAction = internalAction;
	}
	
	public String getCommandText() {
		return commandText;
	}
	
	public int getArgumentCount() {
		return argumentCount;
	}
	
	public String getInternalAction() {
		return internalAction;
	}
	
	//handle the command
	public static Commands getCommandFromString(String input) {
		String cleanInput = input.trim().toLowerCase();
		return commandMap.getOrDefault(cleanInput, UNKNOWN);
	}

}
