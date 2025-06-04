# BVA Analysis for FavorCardController

## Method 1: ```public TurnResult handleCardAction()```
### Step 1-3 Results
|        | Input                            | Output                   |
|--------|----------------------------------|--------------------------|
| Step 1 | Current Player, Other Players    | TurnResult, Card Transfer|
| Step 2 | Player reference, Collection     | Enum, Player state change|
| Step 3 | Valid current player, 1-4 others| CONTINUE, card moved     |

### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                              | Expected output 1    | Expected output 2                                                | Implemented? |
|-------------|----------------------------------------------------------------|----------------------|------------------------------------------------------------------|--------------|
| Test Case 1 | Current player with 0 cards, 1 other player with 5 cards     | TurnResult.CONTINUE  | Current player receives 1 card from other player                | :x:          |

## Method 2: ```public void updatePlayer(Player currentPlayer)```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test     | Expected output 1                      | Implemented? |
|-------------|-----------------------|----------------------------------------|--------------|
| Test Case 1 | Valid Player object   | Current player reference updated       | :x:          |
| Test Case 2 | Null Player object    | IllegalArgumentException or NullPointer| :x:          |

## Method 3: ```public void updateActivePlayersExcludingCurrent(List<Player> activePlayersExcludingCurrent)```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                | Expected output 1                           | Implemented? |
|-------------|----------------------------------|---------------------------------------------|--------------|
| Test Case 1 | List with 1 other player        | Active players list updated                 | :x:          |
| Test Case 2 | Empty list                       | Active players list updated (empty)        | :x:          |
| Test Case 3 | Null list                        | IllegalArgumentException or NullPointer    | :x:          | 