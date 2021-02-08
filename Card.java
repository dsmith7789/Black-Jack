
public class Card {
	// instance variables
		private int value; // 1 is Ace, 11 is Jack, 12 is Queen, 13 is King
		private String suit;	// clubs spades hearts diamonds
		
		// constructor
		public Card (int value, String suit) {
			this.value = value;
			this.suit = suit;
		}
		
		// accessor methods

		/**
		 * gets the value (1-13) of the card
		 * @return the number representation of the card, a number from 1-13
		 */
		public int getValue() {
			return this.value;
		}
		
		/**
		 * gets the suit of the card
		 * @return the suit of the card (clubs, spades, hearts, diamonds)
		 */
		public String getSuit() {
			return this.suit;
		}
		
		/**
		 * @return string representation of the card so we can print it out
		 */
		public String toString() {
			String result;
			if (value == 11) {
				result = "Jack of " + this.suit;
			}
			else if (value == 12) {
				result = "Queen of " + this.suit;
			}
			else if (value == 13) {
				result = "King of " + this.suit;
			}
			else if (value == 1) {
				result = "Ace of " + this.suit;
			}
			else {
				result = value + " of " + this.suit;
			}
			return result;
		}
}
