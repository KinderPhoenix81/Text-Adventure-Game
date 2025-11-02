package textAdventure;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TryCatchExample {

	public static void main(String[] args) {
		//Try catch example
		Scanner scanner = new Scanner(System.in);
		Logger logger = LogManager.getLogger(Main.class);
		
		System.out.println("Enter a number");
		try {
			int num = scanner.nextInt();
			
			//Debug
			System.out.println("You did it");
			logger.info("You're number was" + num);
		} catch (java.util.InputMismatchException ex) {
			//Display error
			System.out.println("That's not a number:(");
			logger.error(ex.getStackTrace());
		} finally {
			//Debug
			System.out.println("Goodbye");
		}
		
		
	}

}
