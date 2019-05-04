public class WordCell {
	public String word;
	public int occurrence;
	public WordCell next;

	public WordCell(String word) {
		this.word = word;
		occurrence++;
		this.next = null;
	}
}