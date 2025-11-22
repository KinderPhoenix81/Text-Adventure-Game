package textAdventure;

public class EngravedRock extends BaseInteractable{

	//Fields for an engraved rock
	private int id;
	
	//Constructor
	public EngravedRock(int id, String name, String desc, String lore) {
		//Uses parent constructor
		super(name, desc, lore);
		
		this.id = id;
	}
	
	//Getter
	public int getID() {
		return id;
	}
}
