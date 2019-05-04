

public class WordStoreImp implements WordStore {

	public WordCellTable sd;

	public WordStoreImp(int _size) {
		sd = new WordCellTable(_size);
	}

	public void add(String word) {
		sd.add(word);
	}

	public int count(String word) {
		return sd.count(word);
	}

	public void remove(String word) {
		sd.remove(word);
	}
}
