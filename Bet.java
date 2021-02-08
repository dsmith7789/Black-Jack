/**
 * Class to represent a bet in the game.
 * @author Dante Smith
 *
 */
public class Bet {
	
	private int amount;
	
	/**
	 * Constructor for the Bet class
	 * @param amount how much money is being bet
	 */
	public Bet(int amount) {
		this.amount = amount;
	}
	
	public Bet() {
		this.amount = 0;
	}
	
	
	/**
	 * accessor method for the bet
	 * @return how much money is associated with this bet
	 */
	public int getBetAmount() {
		return this.amount;
	}
	
	/**
	 * mutator method for the bet
	 * @param amount the new amount of the bet
	 */
	public void setBetAmount(int amount) {
		this.amount = amount;
	}

}
