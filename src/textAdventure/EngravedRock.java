package textAdventure;

public class EngravedRock extends BaseInteractable{
	
	//Constructor
	public EngravedRock(int id, String name, String desc, String lore) {
		//Uses parent constructor
		super(id, name, desc, lore);
	}

	@Override
	public int compareTo(Interactable o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getID(), o.getID());
	}
	
}
