package textAdventure;

/**
 * Exception class for invalid interactable types
 */
@SuppressWarnings("serial")
public class InvalidInteractableTypeException extends Exception{

	/**
	 * Constructor for InvalidInteractableTypeException
	 * 
	 * @param type Type of (invalid) interactable
	 */
	//Exception method
	public InvalidInteractableTypeException(String type) {
		super("Invalid interactable type found: " + type + ". Valid types are door, pedestal, and engraved-rock.");
	}
	
}
