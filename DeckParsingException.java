/**
 * Identifies an exception when reading a starting deck file for the Poker Game
 * @author Dante Smith
 */

public class DeckParsingException extends Exception {
private static final long serialVersionUID = 17L;
	
	public DeckParsingException() {
		this("Could not parse deck file. Check to make sure card entries are valid.");
	}
	
	public DeckParsingException(String message) {
		super(message);
	}
}
