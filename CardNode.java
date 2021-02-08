/**
 * Class to represent a card node
 * We will make queues of card nodes to represent our deck
 * @author Dante Smith
 *
 */
public class CardNode {
	
	// instance variables
	private Card card;
	private CardNode next;
	
	/**
	 * Constructor for the card node
	 * @param card a card object to put in the node
	 */
	public CardNode(Card card) {
		this.card = card;
		this.next =  null;
	}
	
	/**
	 * accessor method for the card in the node
	 * @return the card in this node
	 */
	public Card getCard() {
		return this.card;
	}
	
	/**
	 * accessor method for the next pointer in the node
	 * @return the next node 
	 */
	public CardNode getNext() {
		return this.next;
	}
	
	/** 
	 * mutator method to allow you to set the next node 
	 * @param next the CardNode we want to be the next node
	 */
	public void setNext(CardNode next) {
		this.next = next;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}

}
