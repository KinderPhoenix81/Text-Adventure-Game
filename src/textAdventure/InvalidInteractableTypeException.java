package textAdventure;

@SuppressWarnings("serial")
public class InvalidInteractableTypeException extends Exception{

	//Exception method
	public InvalidInteractableTypeException(String type) {
		super("Invalid interactable type found: " + type + ". Valid types are door, pedestal, and engraved-rock.");
	}
	
}
