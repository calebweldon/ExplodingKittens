# BVA Analysis for ExplodiaCardController

## Method 1: ```public void handleCardDraw()```
### Step 4:
##### All-combination or each-choice: All-combination

|             | System under test            | Expected output 1   | Implemented?       |
|-------------|------------------------------|---------------------|--------------------|
| Test Case 1 | Player with 0 Explodia Cards | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 2 | Player with 1 Explodia Cards | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 3 | Player with 2 Explodia Cards | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 4 | Player with 3 Explodia Cards | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 5 | Player with 4 Explodia Cards | TurnResult.WON      | :white_check_mark: |

## Method 2: ```public void handleCardAction()```
### Step 4:
##### All-combination or each-choice: Each-choice
|              | System under test           | Expected output 1   | Implemented? |
|--------------|-----------------------------|---------------------|--------------|
| Test Case 1  | AttackCardController        | TurnResult.ATTACK   | :x:          |
| Test Case 2  | SkipCardController          | TurnResult.SKIP     | :x:          |
| Test Case 3  | FavorCardController         | TurnResult.CONTINUE | :x:          |
| Test Case 4  | BasicCardController         | TurnResult.CONTINUE | :x:          |
| Test Case 5  | FlipCardController          | TurnResult.CONTINUE | :x:          |
| Test Case 6  | ShuffleCardController       | TurnResult.CONTINUE | :x:          |
| Test Case 7  | SwapCardController          | TurnResult.CONTINUE | :x:          |
| Test Case 8  | EmbarrassCardController     | TurnResult.CONTINUE | :x:          |
| Test Case 8  | RecycleFutureCardController | TurnResult.CONTINUE | :x:          |
| Test Case 9  | AlterFutureCardController   | TurnResult.CONTINUE | :x:          |
| Test Case 10 | SeeFutureCardController     | TurnResult.CONTINUE | :x:          |