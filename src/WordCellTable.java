public class WordCellTable {

	public WordCell[] LinkedList;
	private int index;
	public int size;
	public int collision;

	public int initialWords = 0;
	public int wordsCapacity = 0;
	public int counter = 0;
	public int oldSize = 0;

	public boolean allowResize = true;

	public String previousWord = "";

	public WordCellTable(int _size) {

		if (_size < 0) {
			try {
				throw new Exception("Cannot make negative tables");
			} catch (Exception e) {
			}
		}

		this.initialWords = _size;
		// System.out.println("Initial words is:" + this.initialWords);

		this.size = convertToPow2(_size);
		// System.out.println("we are resizing the array to: " + this.size);

		LinkedList = new WordCell[this.size];

		this.wordsCapacity = this.size - this.initialWords;
		// System.out.println("Our word limit until resize is: " + this.wordsCapacity);
	}

	public void resize(int _size) {

		WordCell[] newLinkedList = new WordCell[_size];

		// Loop through original
		for (int i = 0; i < oldSize; i++) {

			WordCell currentPtr = LinkedList[i];

			// If it contains data
			if (currentPtr != null) {

				// Add values
				for (int z = 0; z < currentPtr.occurrence; z++) {
					add(currentPtr.word, newLinkedList);
				}

				WordCell ptr = currentPtr.next;
				// If there are more than 1 word here
				while (ptr != null) {

					for (int p = 0; p < ptr.occurrence; p++) {
						add(ptr.word, newLinkedList);
					}

					ptr = ptr.next;
				}
			}

		}

		// override
		LinkedList = newLinkedList;
		size = _size;
		
		// Following is used for WordTest5.java to test resizing
	//	System.out.println(LinkedList.length);
		 
	}

	public int convertToPow2(int _size) {

		if (_size <= 0)
			return 0;

		int n = 2;
		while (n < _size) {
			n *= 2;
		}

		n *= 2;

		return n;
	}

	public void add(String word) {
		
		if (allowResize && !word.equals(previousWord)) {
			previousWord = word;

			/* Used for resizing the HashTable */
			counter++;

			// System.out.println("Counter: " + this.counter + " wordsCapacity: " +
			// this.wordsCapacity);
			if (counter >= this.wordsCapacity) {

				// Reset the counter
				counter = 0;

				// System.out.println("Words capacity is: " + this.wordsCapacity);

				this.oldSize = this.size;

				/* Increase size by 2 */
				if (this.size <= 0)
					this.size = 1;
				this.size *= 2;

				// System.out.println("size has been increased to: " + this.size);

				/* Find out the word capacity */
				this.wordsCapacity = this.size - this.oldSize;

				// System.out.println("Word capacity is: " + this.wordsCapacity);

				resize(size);
			}
		} else if (allowResize && count(word) <= 0) {
			previousWord = word;

			/* Used for resizing the HashTable */
			counter++;

			// System.out.println("Counter: " + this.counter + " wordsCapacity: " +
			// this.wordsCapacity);
			if (counter >= this.wordsCapacity) {

				// Reset the counter
				counter = 0;

				// System.out.println("Words capacity is: " + this.wordsCapacity);

				this.oldSize = this.size;

				/* Increase size by 2 */
				if (this.size <= 0)
					this.size = 1;
				this.size *= 2;

				// System.out.println("size has been increased to: " + this.size);

				/* Find out the word capacity */
				this.wordsCapacity = this.size - this.oldSize;

				// System.out.println("Word capacity is: " + this.wordsCapacity);

				resize(size);
			}
		}
		int wordHash = hashCode(word);
		index = hash(word, wordHash);
		if (LinkedList[index] == null) {
			LinkedList[index] = new WordCell(word);

			return;
		} else {
			WordCell pointer = LinkedList[index];
			for (; pointer != null; pointer = pointer.next) {
				if (wordHash == pointer.word.hashCode() && pointer.word.equals(word)) {
					pointer.occurrence++;
					return;
				}
			}
		}
		WordCell addWord = new WordCell(word);
		addWord.next = LinkedList[index];
		LinkedList[index] = addWord;
		collision++;
	}

	public void add(String word, WordCell[] LinkedList) {
		int wordHash = hashCode(word);
		index = hash(word, wordHash);
		if (LinkedList[index] == null) {
			LinkedList[index] = new WordCell(word);

			return;
		} else {
			WordCell pointer = LinkedList[index];
			for (; pointer != null; pointer = pointer.next) {
				if (wordHash == pointer.word.hashCode() && pointer.word.equals(word)) {
					pointer.occurrence++;
					return;
				}
			}
		}
		WordCell addWord = new WordCell(word);
		addWord.next = LinkedList[index];
		LinkedList[index] = addWord;
		collision++;
	}

	public int count(String word) {

		if (size <= 0)
			return 0;

		int wordHash = word.hashCode();
		index = hash(word, wordHash);
		if (LinkedList[index] != null) {
			WordCell pointer = LinkedList[index];
			for (; pointer != null; pointer = pointer.next) {
				if (wordHash == pointer.word.hashCode() && pointer.word.equals(word)) {
					return pointer.occurrence;
				}
			}
		}
		return 0;
	}

	public void remove(String word) {
		int wordHash = word.hashCode();
		index = hash(word, wordHash);
		WordCell pointer = LinkedList[index];
		if (pointer != null) {
			for (; pointer.next != null && !pointer.word.equals(word); pointer = pointer.next) {
			}
			if (wordHash == pointer.word.hashCode() && pointer.word.equals(word)) {
				if (pointer.occurrence == 1) {
					pointer = pointer.next;
					LinkedList[index] = pointer;
				} else if (pointer.occurrence > 1) {
					pointer.occurrence--;
				}
			}
		}
	}

	public int hashCode(String word) {
		int val = 0;
		for (int i = 0; i < word.length(); i++)
			val = val * 31 + word.charAt(i);
		return val;
	}

	public int hash(String word, int hashWord) {
		int key;
		key = hashWord % size;
		key = makePositive(key);
		return key;
	}

	public int makePositive(int key) {
		if (key < 0)
			key = key * -1;
		return key;
	}
}
