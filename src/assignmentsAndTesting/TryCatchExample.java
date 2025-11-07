package assignmentsAndTesting;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TryCatchExample {

	final static Logger log = LogManager.getLogger("LoggingExample");
	
	public static void main(String[] args) {
		//Try catch example
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter a number");
		try {
			int num = scanner.nextInt();
			
			//Debug
			System.out.println("You did it");
			log.info("Your number was " + num);
		} catch (java.util.InputMismatchException ex) {
			//Display errror
			System.out.println("That's not a number:(");
			log.error("Invalid Input", ex);
		} finally {
			//Debug
			System.out.println("Goodbye");
		}
		
	}

}
