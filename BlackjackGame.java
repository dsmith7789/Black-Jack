import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

/**
 * Main class for running the poker game
 * @author Dante Smith
 *
 */

public class BlackjackGame {
	
	// instance variables
	private static int playerMoney;	// the money the player currently has
	
	private static Bet playerTotalBet = new Bet(0);	// the total amount of money the player has bet
	
	private static Hand playerHand = new Hand();		// the cards the player has at the moment
	private static Hand dealerHand = new Hand();		// the cards the dealer (computer player) has at the moment
	private static Deck deck = new Deck();
	
	
	/**
	 * constructor class for the blackjack game
	 * @param startingMoney the amount of money the player brought to play blackjack
	 */
	public BlackjackGame(int startingMoney) {
		playerMoney = startingMoney;	// player will start with the money they brought
		try {
			deck.createDeckList("StartingDeck.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (DeckParsingException e) {
			System.out.println(e.getMessage());
		}
		Collections.shuffle(deck.cardsAvailable);
		deck.createDeckQueue(deck.cardsAvailable);
	}
	
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to Blackjack!");
		System.out.println("How much money have you brought?: ");
		int startingMoney = kb.nextInt();
		kb.nextLine(); // to read past the newline
		BlackjackGame myGame = new BlackjackGame(startingMoney);
		
		boolean playerWantsToContinue = true;
		while (playerMoney > 0 && playerWantsToContinue) {
			myGame.handleBets();
			myGame.initialDeal();
			myGame.processHit();
			myGame.assessGame();
			if (playerMoney > 0) {
				System.out.println("Would you like to continue playing? Enter 'Y' for Yes or 'N' for No.");
				char contChoice;
				contChoice = kb.next().charAt(0);
				kb.nextLine();
				while (Character.toUpperCase(contChoice) != 'Y' && Character.toUpperCase(contChoice) != 'N') {
					System.out.println("You must enter either 'Y' or 'N'.");
					System.out.println("Would you like to continue playing? Enter 'Y' for Yes or 'N' for No.");
					String extra2 = kb.nextLine();
					contChoice = kb.nextLine().charAt(0);
				}
				if (Character.toUpperCase(contChoice) == 'N') {
					playerWantsToContinue = false;
				}
			}
		}
		
		System.out.println("\n ------------------------ \n");
		System.out.println("\t FINAL RESULTS");
		System.out.println("\n ------------------------ \n");
		System.out.println("Thanks for playing!");
		System.out.println("You started with $" + startingMoney + " and finished with $" + playerMoney);
	
		kb.close();
		
		
	}
	
	/**
	 * prompt the player to bet if they have chosen to play
	 */
	public void handleBets() {
		System.out.println("\n ------------------------ \n");
		System.out.println("Betting... \n");
		Scanner scan = new Scanner(System.in);
		System.out.println("You must place an initial bet. How much would you like to bet?");
		Bet initialBet = new Bet(scan.nextInt());
		scan.nextLine(); // to read past the newline
		while (initialBet.getBetAmount() > playerMoney) {
			System.out.println("You only have " + playerMoney + " dollars, you cannot bet above this amount! \n");
			System.out.println("You must place an initial bet. How much would you like to bet?");
			initialBet = new Bet(scan.nextInt());
			scan.nextLine(); // to read past the newline
		}
		playerTotalBet.setBetAmount(initialBet.getBetAmount());
		playerMoney -= initialBet.getBetAmount();
		System.out.println("Your total bet is: " + playerTotalBet.getBetAmount());
		System.out.println("You have " + playerMoney + " dollars left.");
	}
	
	/**
	 * both the dealer and the player get 2 cards to start.
	 */
	public void initialDeal() {
		System.out.println("\n ------------------------ \n");
		System.out.println("Dealing Cards... \n");
		
		// initialize (i.e. clear) the player and dealer hands
		if (playerHand.size() != 0) {
			playerHand.clearHand();
		}
		
		if (dealerHand.size() != 0) {
			dealerHand.clearHand();
		}
		
		// first to the player:
		playerHand.addAtEnd(deck.deal());
		playerHand.addAtEnd(deck.deal());
		System.out.println("\t PLAYER HAND:");
		System.out.println(playerHand.toString());
		
		// then to the dealer:
		System.out.println("\t DEALER HAND:");
		dealerHand.addAtEnd(deck.deal());
		dealerHand.addAtEnd(deck.deal());
		//System.out.println(dealerHand.toString());
		System.out.print("Exposed Card = ");
		System.out.println(dealerHand.get(0));


	}
	
	/**
	 * give the user the choice to "hit" or "stay".
	 * the dealer will "hit" if the value of their hand is lower than 15.
	 */
	public void processHit() {
		System.out.println("\n ------------------------ \n");
		System.out.println("Hit/Stay Phase... \n");
		Scanner scnr = new Scanner(System.in);
		char userChoice;
		String gameState = "continue";
		//scnr.nextLine();
		
		// give player option to hit
		while (gameState == "continue") {
			System.out.println("Would you like to hit? Enter 'Y' for yes or 'N' for no.");
			//String extra = scnr.nextLine();
			//System.out.println(extra);
			userChoice = scnr.next().charAt(0);
			//scnr.nextLine();
			System.out.println(userChoice);
			if (Character.toUpperCase(userChoice) == 'Y') {
				System.out.print("Adding: ");
				System.out.println(deck.peek());
				playerHand.addAtEnd(deck.deal());
				System.out.println("");
			}
			else if (Character.toUpperCase(userChoice) == 'N') {
				System.out.println("You chose to stay.");
				gameState = "end";
			}
			else {
				System.out.println("You must enter 'Y' or 'N'!");
			}
			if (playerHand.bustedHand()) {
				gameState = "end";
			}
			System.out.println("\t PLAYER HAND:");
			System.out.println(playerHand.toString() + "\n");
			
			System.out.print("Exposed Card = ");
			System.out.println(dealerHand.get(0));
		}
		
		// let's give the dealer a chance to hit, if they have a hand with low value
		if (dealerHand.valueOfHand() < 15) {
			System.out.println("Dealer chose to hit.");
			dealerHand.addAtEnd(deck.deal());
		}

	}
	
	/**
	 * assess whether the user won or lost this round.
	 * player loses if: player's hand is busted, or player's hand is worth less than the dealer's hand
	 * player wins if: the dealer's hand is busted (but the player's is not), or player's hand is worth more than the dealer's
	 */
	public void assessGame() {
		System.out.println("\n ------------------------ \n");
		System.out.println("ROUND RESULTS... \n");
		
		System.out.println("\t PLAYER HAND:");
		System.out.println(playerHand.toString() + "\n");
		
		System.out.println("\t DEALER HAND:");
		System.out.println(dealerHand.toString());
		
		if (playerHand.bustedHand()) {
			System.out.println("You lost.");
			System.out.println("You only have " + playerMoney + " dollars left.");
		}
		else if (dealerHand.bustedHand()) {
			System.out.println("Yay, You won!");
			int finalAmt = playerMoney + 2 * playerTotalBet.getBetAmount();
			System.out.println("You now have " + finalAmt + " dollars!");
			playerMoney = finalAmt;
		}
		else if (playerHand.valueOfHand() > dealerHand.valueOfHand()) {
			System.out.println("Yay, You won!");
			int finalAmt = playerMoney + 2 * playerTotalBet.getBetAmount();
			System.out.println("You now have " + finalAmt + " dollars!");
			playerMoney = finalAmt;
		}
		else {
			System.out.println("You lost.");
			System.out.println("You only have " + playerMoney + " dollars left.");
		}

	}
	

}
