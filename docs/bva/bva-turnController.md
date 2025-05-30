# BVA Analysis for TurnController

## Method 2: `public TurnResult takeTurn(Player player)`
### Step 1-3 Results
|        | Input              | (if more to consider for input) | Output                                    |
|--------|--------------------|--------------------------------|-------------------------------------------|
| Step 1 | player parameter   | any Player reference           | TurnResult enum value                     |
|        | player actions     | "play" or "draw" commands      | (CONTINUE, SKIP, ATTACK, ELIMINATED, WON) |
|        | game state         | card types, hand contents      |                                           |
| Step 2 | player = null      |                                | NullPointerException on entry            |
|        | draw EK, no defuse |                                | TurnResult.ELIMINATED                     |
|        | draw EK, has defuse|                                | TurnResult.CONTINUE (defuse used)         |
|        | play Skip card     |                                | TurnResult.SKIP                           |
|        | play Attack card   |                                | TurnResult.ATTACK                         |
| Step 3 | player ≠ null      |                                | enters turn loop, processes actions       |
|        | draw non-EK card   |                                | TurnResult.CONTINUE                       |
|        | play other cards   |                                | TurnResult.CONTINUE                       |

### Step 4:
##### Each-choice

|              | System under test                                           | Expected output                           | Implemented? |
|--------------|--------------------------------------------------------------|-------------------------------------------|--------------|
| Test Case 1  | player = null                                               | NullPointerException on entry            | ✅           |
| Test Case 2  | player ≠ null, user chooses "draw", draws non-EK card      | card added to hand; returns CONTINUE     | ✅           |
| Test Case 3  | player ≠ null, user chooses "draw", draws EK, no defuse    | returns ELIMINATED                        | ✅           |
| Test Case 4  | player ≠ null, user chooses "draw", draws EK, has defuse   | defuse used, EK reinserted; returns CONTINUE | ✅       |
| Test Case 5  | player ≠ null, user chooses "play", plays Skip card        | returns SKIP                              | ✅           |
| Test Case 6  | player ≠ null, user chooses "play", plays Attack card      | returns ATTACK                            | ✅           |
| Test Case 7  | player ≠ null, user chooses "play", empty hand             | shows message, continues to next action   | ✅           |
| Test Case 8  | player ≠ null, user chooses "play", invalid card play     | shows error, continues to next action     | ✅           |
