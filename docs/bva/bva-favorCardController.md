# BVA Analysis for FavorCardController

## Method 1: ```public TurnResult handleCardAction()```
### Step 1-3 Results
|        | Input                             | Output                   |
|--------|-----------------------------------|--------------------------|
| Step 1 | Current Player, Other Players     | TurnResult, Card Transfer|
| Step 2 | Player reference, Collection      | Enum, Player state change|
| Step 3 | Valid current player, 1-4 others  | CONTINUE, card moved     |

### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                        | Expected output 1    | Expected output 2                                | Implemented?        |
|-------------|----------------------------------------------------------|----------------------|--------------------------------------------------|---------------------|
| Test Case 1 | Current player with 0 cards, 1 other player with 2 cards | TurnResult.CONTINUE  | Current player receives 1 card from other player | :white_check_mark:  |
| Test Case 2 | Both Players have no cards                               | TurnResult.CONTINUE  | No card transfer                                 | :white_check_mark:  |
| Test Case 3 | current player receives a null card                      | TurnResult.CONTINUE  | Exception is caught, continue is returned        | :white_check_mark:  |
