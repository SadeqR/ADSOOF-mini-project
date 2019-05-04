import java.util.Date;
import java.util.Scanner;

public class WordTest6 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		WordGen.initialise(input);
		
		/*
		 * 1. Initial creation
		 */
		System.out.print("Enter the number of words you wish to generate initially: ");
		int n = input.nextInt();
		WordStore words = new WordStoreImp(n);
		/*
		 * Generate the words of the constructor size
		 */
		for (int i = 0; i < n; i++)
			words.add(WordGen.make());
		
		String line1 = input.nextLine();
		System.out.println("Enter words to test, empty line to exit");
		line1 = input.nextLine();
		
		String savedWord = "";
		while (!line1.equals("")) {
			String[] wordlist = line1.split(" ");
			for (int i = 0; i < wordlist.length; i++) {
				int count1 = words.count(wordlist[i]);
				System.out.print("\"" + wordlist[i] + "\" ");
				if (count1 == 0)
					System.out.println("NOT generated");
				else if (count1 == 1)
					System.out.println("generated once");
				else
					System.out.println("generated " + count1 + " times ");
					savedWord = line1;
			}
			line1 = input.nextLine();
		}
		
		
		System.out.println("Removing: " + savedWord);
		int count1 = words.count(savedWord);
		System.out.println("now there are " + count1 + " words");

		for(int i=0; i <1E6; i++) {
			words.remove(savedWord);
		}
		
		count1 = words.count(savedWord);
		System.out.println("now there are " + count1 + " words left");
	}
}
