package textAdventure;

public class Item {

	//Fields for an item
	private String name;
	private String description;
	
	//Constructor
	public Item(String name, String desc) {
		this.name = name;
		description = desc;
	}
	
	//Getters for information
	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return description;
	}
}
