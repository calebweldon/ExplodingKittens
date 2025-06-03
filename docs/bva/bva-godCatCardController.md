# BVA Analysis for GodCatCardController

## Method 1: ```public void handleCardAction()```
### Step 4:
##### All-combination or each-choice: Each-choice
|              | System under test           | Expected output 1   | Implemented?       |
|--------------|-----------------------------|---------------------|--------------------|
| Test Case 1  | AttackCardController        | TurnResult.ATTACK   | :white_check_mark: |
| Test Case 2  | SkipCardController          | TurnResult.SKIP     | :white_check_mark: |
| Test Case 3  | FavorCardController         | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 4  | BasicCardController         | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 5  | FlipCardController          | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 6  | ShuffleCardController       | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 7  | SwapHandCardController      | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 8  | EmbarrassCardController     | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 9  | RecycleFutureCardController | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 10 | AlterFutureCardController   | TurnResult.CONTINUE | :white_check_mark: |
| Test Case 11 | SeeFutureCardController     | TurnResult.CONTINUE | :white_check_mark: |