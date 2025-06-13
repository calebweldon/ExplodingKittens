# BVA Analysis for ExplodingKittenCardController

## Method 1: ```public TurnResult handleCardDraw()```
### Step 1-3 Results
|        | Input                                          | (if more to consider for input)    | Output                                   |
|--------|------------------------------------------------|------------------------------------|------------------------------------------|
| Step 1 | Current player's hand state                    | Player with/without defuse cards   | TurnResult enum value                    |
|        | Deck state                                     | Deck size for reinsertion          |                                          |
| Step 2 | Player reference (null/valid)                  |                                    |                                          |
|        | Defuse card presence (boolean)                 |                                    |                                          |
|        | Deck size (integer)                            |                                    |                                          |
| Step 3 | Player = null                                  |                                    | IllegalStateException                    |
|        | Player ≠ null, has defuse card                 |                                    | TurnResult.CONTINUE                      |
|        | Player ≠ null, no defuse card                  |                                    | TurnResult.ELIMINATED                    |

### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                              | Expected output         | Implemented?         |
|-------------|------------------------------------------------|-------------------------|----------------------|
| Test Case 1 | Player is null                                 | IllegalStateException   | :white_check_mark:   |
| Test Case 2 | Player has defuse card, deck size > 0          | TurnResult.CONTINUE     | :white_check_mark:   |
| Test Case 3 | Player has no defuse card                      | TurnResult.ELIMINATED   | :white_check_mark:   |
| Test Case 4 | Player has defuse card, empty deck (size = 0)  | TurnResult.CONTINUE     | :white_check_mark:   | 