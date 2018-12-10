/* This is the class used to run the program
 * 
 */
package autocomplete;

import java.util.List;
import java.util.Scanner;

public class Driver {
	public static AutocompleteProvider provider = new AutocompleteProvider(); // initialize the autocomplete provider
	
	public static void main(String[] args) {
		System.out.println("Asymmetrik Autocomplete Program\n");
		mainMenu();
	}
	
	public static void mainMenu() { // handles the user interface of the program
		System.out.println("1. Train the autocomplete algorithm with input text");
		System.out.println("2. Test the algorithm by entering a word fragment");
		System.out.println("3. Exit (The algorithm will be reset on subsequent runs of the program)");
		int choice = getValidInt(1, 3); // get the user's selection
		System.out.println(""); // for appearances
		Scanner scan = new Scanner(System.in);
		
		//evaluate the user's choice
		switch (choice) {
		case CONSTANTS.TRAIN:
			
			System.out.print("Enter a passage to train the algorithm (press enter to end the passage): ");
			String passage = scan.nextLine();
			provider.train(passage);
			break;
		
		case CONSTANTS.TEST:
			
			String prefix;
			do { // keep asking for input until a valid alphabetical string is given
				System.out.print("Enter a prefix to test autocomplete (alphabetic characters only): ");
				prefix = scan.nextLine();
			} while (!prefix.matches("[a-zA-z]+"));
			System.out.println(""); // for appearances
			showResults(provider.getWords(prefix.toLowerCase()));
			break;
			
		case CONSTANTS.EXIT:
			
			System.exit(0);
			break;
		}
		mainMenu();
	}
	
	public static void showResults(List<Candidate> candidates) { // formats and prints the resulting list of candidates from a test
		if (candidates.isEmpty()) {
			System.out.println("No autocomplete candidates found\n");
		}
		
		else {
			for (int i = 0; i < candidates.size(); i++) {
				System.out.println(String.format("WORD: %s\nCONFIDENCE: %d\n", candidates.get(i).getWord(), candidates.get(i).getConfidence()));
			}
		}
	}
	
	public static int getValidInt(int min, int max) { // gets an integer from the user between min and max
		int choice;
		Scanner scan = new Scanner(System.in);
		try {
			do {
				System.out.println(String.format("Please enter a choice between %d and %d", min, max));
				choice = scan.nextInt();
			} while (choice < min || choice > max);
		}
		catch (java.util.InputMismatchException e) {
			System.out.println("Invalid input. Entry must be an integer");
			return getValidInt(min, max);
		}
		return choice;
	}

}
