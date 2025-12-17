package textAdventure;

import java.util.Scanner;

/**
 * Main class for running the program
 */
public class Main {
	
	/**
	 * Main method that runs the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Run the gameManagement constructor
		GameManagement newGame = new GameManagement();
		
		//create input handler
		InputHandler inputHandler = newGame.getInputHandler();
		
		//scanner which will take user text and complete actions
		try(Scanner scanner = new Scanner(System.in)) {
			boolean playing = true;
			
			while (playing) {
				System.out.println("\n> ");
				String input = scanner.nextLine().trim();
				
				//handle the command from the user
				playing = inputHandler.handleCommand(input);
			}
		} catch (Exception e) {
			System.err.println("An unexpected error occured: " + e.getMessage());
		}

		
		
	}
}
