# BVA Analysis for TurnController

## Method 1: `public TurnResult takeTurn(Player player)`
### Step 1-3 Results
|        | Input                            | (if more to consider for input)    | Output                              |
|--------|----------------------------------|------------------------------------|-------------------------------------|
| Step 1 | player parameter                 | any Player reference               | None                                |
|        | player actions                   | "play", "draw", or "info" commands |                                     |
|        | game state                       | card types, hand contents          |                                     |
| Step 2 | draw card without handleDraw()   |                                    |                                     |
|        | draw card with handleDraw()      |                                    |                                     |
|        | play card without handleAction() |                                    |                                     |
|        | play card with handleAction()    |                                    |                                     |
|        | get info of card                 |                                    |                                     |
|        | play card with empty hand        |                                    |                                     |
|        | draw card with empty deck        | Deck will never be empty           |                                     |
|        | player == null                   |                                    |
| Step 3 | player ≠ null                    |                                    | enters turn loop, processes actions |
|        | draw non-EK card                 |                                    |                                     |
|        | play other cards                 |                                    |                                     |

### Step 4:
##### Each-choice

|             | System under test                                                                 | Expected output                         | Implemented?       |
|-------------|-----------------------------------------------------------------------------------|-----------------------------------------|--------------------|
| Test Case 1 | player = null                                                                     | IllegalArgumentException on entry       | :white_check_mark: |
| Test Case 2 | player ≠ null, user chooses "draw", draws card without handleDraw()               | card added to hand                      | :white_check_mark: |
| Test Case 3 | player ≠ null, user chooses "draw", draws card with handleDraw() that is playable | draw handled                            | :white_check_mark: |
| Test Case 4 | player ≠ null, user chooses "play", plays card without handleAction()             | shows error, continues to next action   | :x:                |
| Test Case 5 | player ≠ null, user chooses "play", plays card with handleAction()                | action handled, and card removed        | :x:                |
| Test Case 6 | player ≠ null, user chooses "play", empty hand                                    | shows message, continues to next action | :x:                |
| Test Case 7 | player ≠ null, user chooses "info", gets info of card                             | shows message, continues to next action | :x:                |

