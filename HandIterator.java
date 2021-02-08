import java.util.Iterator;
import java.util.NoSuchElementException;

public class HandIterator implements Iterator<Card> {

	private CardNode current;
	
	/**
	 * constructor for this iterator
	 * @param head the first node in the hand's associated linked list
	 */
	public HandIterator(CardNode head){
		this.current = head;
	}
	
	/**
	 * returns true if the hand's associated linked list has a next node
	 */
	@Override
	public boolean hasNext() {
		return this.current != null;
	}

	/**
	 * returns the next card in the hand's associated linked list
	 */
	@Override
	public Card next() throws NoSuchElementException {
		if (this.hasNext()) {
			Card temp = this.current.getCard();
			this.current = this.current.getNext();
			return temp;
		}
		else {
			throw new NoSuchElementException();
		}
		
	}

}
