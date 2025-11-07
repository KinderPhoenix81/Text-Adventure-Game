package textAdventure;

public abstract class BaseInteractable implements Interactable {

	//Fields for any interactable
	private String name;
	private String description;
	private String lore;
	
	
	//Override the behavior of the methods in the interface
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return description;
	}

	@Override
	public String getLore() {
		return lore;
	}

}
