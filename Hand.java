import java.util.Iterator;

/**
 * Represents a hand (linked list of CardNodes) for the BlackJack game
 * @author smith
 *
 */

public class Hand implements ListADT<Card>, Iterable<Card>{
	// instance variables
	private CardNode head;
	private CardNode tail;
	private int numOfCards;
	
	
	/**
	 * constructor for the Hand class
	 */
	public Hand() {
		this.head = null;
		this.tail = null;
		this.numOfCards = 0;
	}
	
	/**
	 * adds up the cards to get the value of the hand
	 * @return the total value of the cards in your hand
	 */
	public int valueOfHand() {
		int totalValue = 0;
		Iterator<Card> iter = this.iterator();
		while (iter.hasNext()) {
			int cardValue = iter.next().getValue();
			if (cardValue == 1) {
				if (totalValue <= 10) {
					totalValue += 11;
				}
				else {
					totalValue++;
				}
			}
			else {
				totalValue += cardValue;
			}
		}
		return totalValue;
	}
	
	/**
	 * use to determine if the hand is busted or not
	 * @return true if the value of the hand is > 21, false otherwise
	 */
	public boolean bustedHand() {
		if (this.valueOfHand() > 21) {
			return true;
		}
		else {
			return false;
		}
 	}
	
	/**
	 * @return a string representation of the hand
	 */
	public String toString() {
		String s = "Number of cards in hand: " + this.numOfCards + "\n";
		s += "Cards: \n";
		CardNode current = this.head;
		while (current != null) {
			String cardName = current.getCard().toString();
			s += cardName + "\n";
			current = current.getNext();
		}
		s += "Value of hand = " + this.valueOfHand() + "\n";
		return s;
	}

	/**
	 * adds a card to the end of the hand
	 */
	@Override
	public void addAtEnd(Card element) {
		CardNode newNode = new CardNode(element);
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
			this.numOfCards = this.size() + 1;
		}
		else {
			this.tail.setNext(newNode);
			this.tail = newNode;
			this.numOfCards = this.size() + 1;
		}
		
	}

	/**
	 * adds a card to the hand at the specified index
	 */
	@Override
	public void add(int index, Card element) throws IndexOutOfBoundsException {
		/*
		 * 1. Create new node: CardNode newNode = new CardNode(element);
		 * 2. Traverse list. 
		 * 3. Find node at location = (index - 1). Store as prevNode
		 * 4. Find node at location = index. Store as nextNode
		 * 5. Set the next on prevNode = newNode
		 * 6. Set the next on newNode = nextNode
		 */
		if (index >= 0 && index <= this.size()) {
			int count = 0; // used to help traverse the list
			CardNode newNode = new CardNode(element); // the new node we'll be adding to the list
			CardNode prevNode = null; // initialize, we need to change the next pointer on the node at the location
										// before we're inserting to reference the new node
			CardNode nextNode = null; // initialize, we need to set the next on our new node to be the node that just
										// got displaced.
			CardNode current = this.head; // so that we start from the start of the list
			while (current != null) {
				if (count == (index - 1)) {
					prevNode = current;
				}
				if (count == index) {
					nextNode = current;
				}
				count++;
				current = current.getNext();
			}
			// prevNode would only be null if we have a blank list, so we already handled
			// that case above
			if (prevNode == null && nextNode == null) {
				this.addAtEnd(element);
				this.head = newNode;
				this.tail = newNode;
			} else if (prevNode == null) {
				newNode.setNext(nextNode);
				this.head = newNode;
				this.numOfCards = this.size() + 1; // since we added a node to the list, the size is now increased by 1
			}
			// if nextNode is blank and prevNode is not, that has to mean we're adding to
			// the end of the list. We already handled that above.
			else if (nextNode == null) {
				this.addAtEnd(element);
				this.tail = newNode;
			} else {
				prevNode.setNext(newNode);
				newNode.setNext(nextNode);
				this.numOfCards = this.size() + 1; // since we added a node to the list, the size is now increased by 1
			}
		} else {
			throw new IndexOutOfBoundsException(
					"WARNING: The index of the new node must be greater than 0 and less than the size of the list");
		}

	}


	/**
	 * gives the current number of cards in the hand
	 * @return current number of cards in the hand
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.numOfCards;
	}

	/**
	 * @param index the index of the card being removed
	 * @return the card that was removed from the hand
	 */
	@Override
	public Card remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
				/*
				 * 1. Traverse list. 
				 * 2. Find node at location = (index - 1). Store as prevNode
				 * 3. Find node at location = index. Store as thisNode
				 * 4. Find node at location = (index + 1). Store as nextNode
				 * 5. Set the next on prevNode = nextNode
				 */
				Card discardCard = null;
				if (index >= 0 && index < this.size()) {
					int count = 0;	// used to help traverse the list
					CardNode current = this.head;	// so that we start from the start of the list
					CardNode prevNode = null;	// initialize, we need to change the next pointer on the node at the location before we're inserting to reference the new node
					CardNode thisNode = null;	// initialize
					CardNode nextNode = null;	// initialize, we need to set the next on our new node to be the node that just got displaced.
					while (current != null) {
						if (count == (index - 1)) {
							prevNode = current;
						}
						if (count == index) {
							thisNode = current;
						}
						if (count == (index + 1)) {
							nextNode = current;
						}
						count++;
						current = current.getNext();
					}
					// if the prevNode is null, then that means we're removing the head node
					if (prevNode == null) {
						// Save off data from the current node, then clear out the data and the next. 
						discardCard = thisNode.getCard();
						thisNode.setNext(null); 	// NEW LINE
						this.head = nextNode;
						this.numOfCards = this.size() - 1;
					}
					// if the prevNode is not null and the nextNode is, that means we're removing the tail
					else if (nextNode == null) {
						// Save off data from the current node, then clear out the data and the next. 
						discardCard = thisNode.getCard();
						prevNode.setNext(null);		// NEW LINE
						this.tail = prevNode;
						this.numOfCards = this.size() - 1;
					}
					else {
						// Save off data from the current node, then clear out the data and the next. 
						discardCard = thisNode.getCard();
						prevNode.setNext(nextNode);
						this.numOfCards = this.size() - 1;
					}
				}
				else {
					throw new IndexOutOfBoundsException("WARNING: The index of the new node must be greater than 0 and less than the size of the list");
				}
				return discardCard;
	}

	/**
	 * removes the head card in the linked list
	 * @return the card that was removed
	 */
	@Override
	public Card removeFromFront() {
		Card discardCard = this.remove(0);
		return discardCard;
	}

	/**
	 * @return the card at the specified index
	 */
	@Override
	public Card get(int index) throws IndexOutOfBoundsException {
		Card returnCard = null;
		if (index >= 0 && index < this.size()) {
			int count = 0;
			CardNode current = this.head;
			while (current != null) {
				if (count == index) {
					returnCard = current.getCard();
				}
				count++;
				current = current.getNext();
			}
		}
		else {
			throw new IndexOutOfBoundsException("WARNING: The index of the new node must be greater than 0 and less than the size of the list");
		}
		return returnCard;
	}
	
	/**
	 * @return an iterator so we can iterate through the nodes of the hand's linked list
	 */
	@Override
	public Iterator<Card> iterator() {
		return new HandIterator(this.head);
	}
	
	/**
	 * by clearing the card in the head node and the next reference, we've basically gotten rid of the hand.
	 */
	public void clearHand() {
		this.head = null;
		System.out.println("Cleared hand.");
		this.numOfCards = 0;
	}

}
