import java.util.Scanner;

public class WordTest7 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		/*
		 * 1. Initial creation
		 */
		System.out.println("Enter a word");
		String s = input.nextLine();
		
		/*
		System.out.print("Enter the initial hashtable size: ");
		int n = input.nextInt();
		*/
		int n = 1;
		WordStore words = new WordStoreImp(n);
		System.out.print("Enter the amount of times you want your word ("+ s +") be added into the hashtable: ");
		n = input.nextInt();
		
		/*
		 * Generate the words of the constructor size
		 */
		for (int i = 0; i < n; i++)
			words.add(s);
		System.out.println("There is a total of: " + words.count(s) + " added into this hashtable");
	}
}
