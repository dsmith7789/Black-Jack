import java.io.FileNotFoundException;

public class Tester {
	
	/**
	 * tests whether the getBetAmount method in the Bet class works correctly
	 * @param amount how much the bet should be for
	 * @return the amount returned by getBetAmount
	 */
	public static int BetTester(int amount) {
		Bet betTest = new Bet(amount);
		return betTest.getBetAmount();
	}
	
	/**
	 * tests whether the getValue method in the Card class works appropriately
	 * @param value the value of the card
	 * @param suit the suit of the card
	 * @return the value returned by getValue
	 */
	public static int CardTester(int value, String suit) {
		Card card = new Card(value, suit);
		return card.getValue();
	}
	
	/**
	 * tests if we correctly assess the value of the hand (especially when adding aces to the hand).
	 */
	public static void valueOfHandTester() {
		Hand testHand = new Hand();
		Card firstCard = new Card(7, "Clubs");
		Card secondCard = new Card(10, "Diamonds");
		
		testHand.addAtEnd(firstCard);
		testHand.addAtEnd(secondCard);
		System.out.println("Testing valueOfHand: The value of this hand should be 7 + 10 = 17, got " + testHand.valueOfHand());
		
		Card thirdCard = new Card(1, "Spades");
		testHand.addAtEnd(thirdCard);
		System.out.println("Testing valueOfHand: The value of this hand should be 7 + 10 = 17, got " + testHand.valueOfHand());
		
		Hand secondHand = new Hand();
		secondHand.addAtEnd(thirdCard);
		System.out.println("Testing valueOfHand: The value of the second hand should be 11, got " + secondHand.valueOfHand());
		
		secondHand.addAtEnd(thirdCard);
		System.out.println("Testing valueOfHand: The value of the second hand should be 11 + 1 = 12, got " + secondHand.valueOfHand());
		
	}
	
	/**
	 * tests if we correctly clear out the hand
	 */
	public static void clearHandTester() {
		Hand testHand = new Hand();
		Card firstCard = new Card(7, "Clubs");
		Card secondCard = new Card(10, "Diamonds");
		
		testHand.addAtEnd(firstCard);
		testHand.addAtEnd(secondCard);
		System.out.println("The size of the hand should be 2, got " + testHand.size());
		
		testHand.clearHand();
		System.out.println("The size of the hand now should be 0, got " + testHand.size());
	}
	
	/**
	 * tests if we reference the next of the card nodes correctly, and if we can set values within the nodes correctly
	 */
	public static void cardNodeTester() {
		Card firstCard = new Card(7, "Clubs");
		Card secondCard = new Card(10, "Diamonds");
		Card thirdCard = new Card(1, "Spades");
		
		CardNode firstNode = new CardNode(firstCard);
		CardNode secondNode = new CardNode(secondCard);
		CardNode thirdNode = new CardNode(thirdCard);
		
		firstNode.setNext(secondNode);
		secondNode.setNext(thirdNode);
		System.out.println("Expecting 7 of Clubs --> 10 of Diamonds, got " + firstNode.getCard().toString() + " ---> " + firstNode.getNext().getCard().toString());
		System.out.println("Expecting 10 of Diamonds --> Ace of Spades, got " + secondNode.getCard().toString() + " ---> " + secondNode.getNext().getCard().toString());
		
		secondNode.setCard(thirdCard);
		System.out.println("Expecting 7 of Clubs --> Ace of Spades, got " + firstNode.getCard().toString() + " ---> " + firstNode.getNext().getCard().toString());
		
	}
	
	/**
	 * tests if our exceptions work correctly for the deck class, if we add cards to the deck queue appropriately
	 * @return true if the exceptions are thrown appropriately, false if otherwise.
	 */
	public static boolean deckTester() {
		Deck firstTestDeck = new Deck();
		Deck secondTestDeck = new Deck();
		try {
			firstTestDeck.createDeckList("testFile1.txt");
			System.out.println("Using testFile2.txt should throw a DeckParsingException.");
			return false;
		} catch (FileNotFoundException e) {

		} catch (DeckParsingException e) {
			// this is what should happen
		}
		
		try {
			secondTestDeck.createDeckList("testFile2.txt");
			System.out.println("Using testFile2.txt should throw a FileNotFoundException.");
			return false;
		} catch (FileNotFoundException e) {
			// this is what should happen
		} catch (DeckParsingException e) {

		}
		Deck thirdTestDeck = new Deck();
		try {
			thirdTestDeck.createDeckList("testFile3.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DeckParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thirdTestDeck.createDeckQueue(thirdTestDeck.cardsAvailable);
		if (thirdTestDeck.queueSize() != 52) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("Testing started.");
		
		System.out.println("Testing getBetAmount for a bet of 100 - expecting 100, got " + BetTester(100));
		System.out.println("Testing getValue for King of Hearts - expecting 10, got " + CardTester(10, "Hearts"));
		valueOfHandTester();
		clearHandTester();
		cardNodeTester();
		System.out.println(deckTester());
	}
}
