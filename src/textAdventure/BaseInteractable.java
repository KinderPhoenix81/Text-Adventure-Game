package textAdventure;

public abstract class BaseInteractable implements Interactable, Comparable<BaseInteractable> {

	//Fields for any interactable
	private int id;
	private String name;
	private String description;
	private String lore;
	
	public BaseInteractable(int id, String name, String desc, String lore) {
		this.id = id;
		this.name = name;
		this.description = desc;
		this.lore = lore;
	}
	
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

	@Override
	public int getID() {
		return id;
	}
	
	@Override
	public int compareTo(BaseInteractable other) {
	return Integer.compare(this.id, other.id);
	}
	
}
