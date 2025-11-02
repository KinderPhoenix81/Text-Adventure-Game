package textAdventure;

import java.util.Scanner;

public class TryCatchExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter a number");
		try {
			int num = scanner.nextInt();
			System.out.println("You did it");
		} catch (java.util.InputMismatchException ex) {
			System.out.println("That's not a number:(");
		} finally {
			System.out.println("Goodbye");
		}
		
		
	}

}
