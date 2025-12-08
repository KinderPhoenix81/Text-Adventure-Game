package textAdventure;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		//Run the gameManagement constructor
		GameManagement newGame = new GameManagement();
		
		//Show the starting room
		GameManagement.displayRoom.accept(Player.getCurrentRoom());
		
		//scanner which will take user text and complete actions
		Scanner scanner = new Scanner(System.in);
		boolean playing = true;
		
		while (playing) {
			System.out.println("\n> ");
			String input = scanner.nextLine().trim();
			
			//handle the command from the user
			playing = newGame.handleCommand(input);
		}
		
		
	}
}
