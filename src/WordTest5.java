import java.util.Date;
import java.util.Scanner;

class WordTest5 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		WordGen.initialise(input);
		/*
		 * Test how long (after constructor) takes to fill the HashTable
		 */
		System.out.print("Enter the number of words you wish to generate initially: ");
		int n = input.nextInt();
		long time5, time6;
		WordStore words = new WordStoreImp(n);
		/*
		 * Generate the words of the constructor size
		 */
		for (int i = 0; i < n; i++)
			words.add(WordGen.make());
		/*
		 * Call resize by adding more words
		 */
		System.out.print("Enter number of words you wish to add: ");
		n = input.nextInt();
		String[] testWords5 = new String[n];
		for (int i = 0; i < n; i++)
			testWords5[i] = WordGen.make();
		long time9, time10;
		time9 = new Date().getTime();
		for (int i = 0; i < n; i++)
			words.add(testWords5[i]);
		time10 = new Date().getTime();
		System.out.print("Time taken to add " + n + " more words ");
		System.out.println("is " + (time10 - time9) + "ms");
	}
}
