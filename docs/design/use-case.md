## Use Case
As a player of Exploding Kittens, I want the game to be properly set up automatically when a new game starts, so that I can begin playing without manually organizing the deck and distributing cards.

### Acceptance Criteria
1. :x: The game must not start unless there are 2 to 5 players.

2. :x: Each player must receive exactly 7 random cards from the deck.

3. :x: Each player must receive 1 Defuse card in addition to the 7 random cards.

4. :x: The number of Exploding Kitten cards added back into the deck must be one less than the number of players.

5. :x: The deck must be shuffled after adding the Exploding Kitten cards back in.

6. :x: The turn order must be initialized once setup is complete.

### Use Case 1: Start New Game

- **Actor:** Player

- **Preconditions:**

    - The game application is launched.

- **Main Flow:**
    1. Player clicks “Start Game”.

    2. System asks for the total number of player.

    3. The player enters the number of players.

    4. (Your team can also add "asking for player name" feature and etc.. )

    5. System shuffles the deck.

    6. System deals 7 cards to each player.

    7. System gives each player 1 Defuse card.

    8. System inserts the correct number of Exploding Kitten cards to the deck (1 less than the number of players).

    9. System shuffles the deck again.

    10. System sets the initial turn order.

- **Alternate Flows:**

    - 2.a The system indicates that the number of players the user enters is invalid

    - 2.b Resumes at Step 2


- **Postconditions:**
-
    - Each player has 8 cards (7 random + 1 Defuse).

    - The deck contains the correct number of Exploding Kittens.

    - The game is ready for the first turn.