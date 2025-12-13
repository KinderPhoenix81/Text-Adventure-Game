package textAdventure;

/*
 * 1.2: Use of Interface
 */
public interface Interactable {

	//default behvaiors of any interactable
	public String getName();
	public String getDesc();
	public String getLore();
	public int getID();
	int compareTo(Interactable other);
	
}
