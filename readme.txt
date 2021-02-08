MAIN METHOD: is in BlackjackGame.java


PURPOSE: to allow the user to play Blackjack against a computer dealer.

The object of the game is to try to get the value of your hand to be as close to 21, without exceeding 21. 

The value of each card is as follows:

Aces = 1 or 11; We will automatically set the value of the ace to 11, unless the value of your hand will exceed
21 if the ace value is 11, then we will automatically make it worth 1.

Face Cards (Jack, Queen, King) = 10

All other cards = their pip value (ex. a "3 of diamonds" has a value of 3)

Your hand is fully visible at all times. You can only see one card in the dealer's hand, until the end of the game, when the 
dealer's hand is fully revealed.

After receiving your initial hand, you can choose to add cards to your hand to increase the value of your hand to get closer to (or
exactly on) 21. This is known as "hitting". At any point during the hitting phase you can "stay", which means you are comfortable with
the value of your hand and do not want to receive any more cards. During this phase, the dealer will automatically hit if the value of 
their hand is lower than 15. If you hit and the resulting value of your hand is greater than 21, this is known as "busting" and you 
automatically lose.

Once all the players have finished hitting, the hands are revealed. 
You have won the round if the dealer's hand is busted (but yours is not), or your hand is worth more than the dealer's.
You have lost the round if your hand is busted (regardless of if the dealer's hand is also busted), or your hand is worth less than the dealer's hand.

At the end of the game, your new money balance is displayed. As long as you have more than $0 available, you can opt to keep playing. You can 
leave the table at any time, but you are forced to leave the table (i.e. the game automatically ends) once you reach a balance of $0.


COMMANDS (To play the game):
1. Make sure you have a tab delimited text file to define the cards in your deck. "StartingDeck.txt" is provided by default.
2. Run the BlackjackGame.java program.
3. Indicate the amount of money you have brought to the table (starting money).
4. Indicate how much you'd like to bet this round.
5. Both you and the dealer will be dealt 2 cards. This is your hand.
6. You can choose to hit or stay. The dealer will auto-hit if the value of their hand is less than 15.
7. Hands are revealed and the result is displayed.
8. You can choose to play another round if you like. Your money from the previous round carries over to the next round.


LIMITATIONS of the game:
1. I have not accounted for the deck running out of cards. If you play enough rounds to where the game runs out of cards, you may experience
a crash.
2. If you try to reference a non-existent or not properly formatted deck file, the game will crash.


REQUIREMENTS (from part 3 of the assignment)
I use >= 7 classes:
1. Bet class
2. Blackjack game class
3. Card class
4. CardNode class
5. Deck class
6. DeckParsingException class
7. Hand class
8. Hand Iterator class
9. Tester class

Also use 2 interfaces:
1. ListADT
2. QueueADT