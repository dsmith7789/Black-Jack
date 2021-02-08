import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Deck implements QueueADT<Card> {
	
	// instance variables
	protected ArrayList<Card> cardsAvailable = new ArrayList<Card>();;
	protected final String [] suits = {"Clubs", "Spades", "Hearts", "Diamonds"};
	protected final String [] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
	
	private CardNode top;	// cards come off the top of the deck
	private CardNode bottom;	// cards get added to the bottom of the deck
	private int numOfCards;
	
	
	/**
	 * constructor for the Deck class.
	 */
	public Deck() {
		//cardsAvailable = new ArrayList<Card>();
		this.top = null;
		this.bottom = null;
		Collections.shuffle(cardsAvailable);
		createDeckQueue(cardsAvailable);
	}
	
	
	/**
	 * Instantiates the cards based on the deck input file, adds the cards to the deck.
	 * @param inputFileName the tab delimited file that has the cards
	 * @throws FileNotFoundException if there is no file with the name given.
	 * @throws DeckParsingException if a line within the input file is not formatted appropriately.
	 */
	public void createDeckList(String inputFileName) throws FileNotFoundException, DeckParsingException {
		File inFile = new File(inputFileName);
		Scanner scnr = null;
		try {
			scnr = new Scanner(inFile);
			int inputLine = 1;	// we'll always start with line 1
			while (scnr.hasNextLine()) {
				String[] tokenArray = scnr.nextLine().trim().split("\t");
				String[] cardArray = new String[tokenArray.length];
				// this will get rid of any leading/trailing space in the actual elements of the array so we can compare properly
				for (int i = 0; i < tokenArray.length; i++) {
					cardArray[i] = tokenArray[i].trim();
				}
				// a card has to have 2 elements, the value and the suit
				if (cardArray.length != 2) {
					throw new DeckParsingException("WARNING: Card on line " + inputLine + " is not formatted properly (value suit).");
				}
				else if (checkValidValue(cardArray[0]) == false) {
					throw new DeckParsingException("WARNING: Card on line " + inputLine + "has an invalid value (must be 1-13).");
				}
				else if (checkValidSuit(cardArray[1]) == false) {
					throw new DeckParsingException("WARNING: Card on line " + inputLine + "has an invalid suit (must be Spades/Clubs/Hearts/Diamonds).");
				}
				// if the array has at least 3 elements, the first one is the office and the others are candidates
				else {
					Card c = new Card(Integer.parseInt(cardArray[0]), cardArray[1]);
					this.cardsAvailable.add(c);
				}
				inputLine++;
			}
		} finally {
			if (scnr != null) { 
		        scnr.close(); // if we created the scanner we have to close it to avoid resource leak
		    } 
		}
	}
	
	/**
	 * Translates the shuffled deck Array List to a queue.
	 * @param deckList
	 */
	public void createDeckQueue(ArrayList<Card> deckList) {
		for (int i = 0; i < deckList.size(); i++) {
			this.enqueue(deckList.get(i));
		}
	}
	
	/**
	 * Checks to make sure the card has a valid value
	 * @param cardValue the value assigned to the card. 1 is Ace, 11 is Jack, 12 is Queen, 13 is King
	 * @return true if the card has a valid value (1-13), otherwise false.
	 */
	public boolean checkValidValue(String cardValue) {
		int matches = 0;
		for (int i = 0; i < this.values.length; i++) {
			if (cardValue.contentEquals(this.values[i])) {
				matches++ ;
			}
		}
		if (matches > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Checks to make sure the card has a valid suit
	 * @param cardSuit the suit assigned to the card.
	 * @return true if the card has a valid suit (spades, clubs, hearts, diamonds), otherwise false.
	 */
	public boolean checkValidSuit(String cardSuit) {
		int matches = 0;
		for (int i = 0; i < this.suits.length; i++) {
			if (cardSuit.contentEquals(this.suits[i])) {
				matches++ ;
			}
		}
		if (matches > 0) {
			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * Determines whether or not this queue is empty.
	 * @return True when queue is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (this.numOfCards > 0) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * Gives the number of card nodes in this queue.
	 * @return The current number of nodes in the queue.
	 */
	@Override
	public int queueSize() {
		return this.numOfCards;
	}


	/**
	 * Adds the card to the hand.
	 * @param card the card we're adding to the hand.
	 */
	@Override
	public void enqueue(Card card) {
		CardNode newNode = new CardNode(card);
		// if we have an empty queue then this is our front and back of the queue
		if (this.top == null) {
			this.top = newNode;
			this.bottom = newNode;
			this.numOfCards = this.queueSize() + 1;
		}
		else {
			this.bottom.setNext(newNode);
			this.numOfCards = this.queueSize() + 1;
			this.bottom = newNode;
		}
		
	}



	/**
	 * Removes all groups from this queue. This queue will become empty.
	 */
	@Override
	public void clear() {
		if (this.isEmpty() == false) {
			this.top.setNext(null);
			this.bottom = null;
			this.bottom = null;
			this.numOfCards = 0;
		}
		
	}


	@Override
	public Card peek() throws NoSuchElementException {
		if (this.top != null) {
			return this.top.getCard();
		}
		else {
			throw new NoSuchElementException("WARNING: Cannot peek. Deck is empty.");
		}
	}


	/**
	 * "Deals" a card off the top of the deck. Removes that card from the list of available cards.
	 * @return the card that was on the top of the deck.
	 */
	@Override
	public Card deal() throws NoSuchElementException {
		if (this.top != null) {
			if (this.queueSize() == 1) {
				Card discardCard = this.top.getCard();
				this.clear();
				return discardCard;
			}
			else {
				Card discardCard = this.top.getCard();
				this.top = this.top.getNext();
				this.numOfCards = this.queueSize() - 1;
				return discardCard;
			}
		}
		else {
			throw new NoSuchElementException("WARNING: Cannot remove card from hand. Hand is empty.");
		}
	}

}
