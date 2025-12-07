package textAdventure;

public class Main {
	
	public static void main(String[] args) {
		//Run the gameManagement constructor
		GameManagement newGame = new GameManagement();
		
		//Show the starting room
		GameManagement.displayRoom.accept(Player.getCurrentRoom());
	}
}
