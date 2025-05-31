# BVA Analysis for GodCatCardController

## Method 1: ```public void handleCardAction()```
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
| Test Case 7  | SwapHandCardController      | TurnResult.CONTINUE | :x:          |
| Test Case 8  | EmbarrassCardController     | TurnResult.CONTINUE | :x:          |
| Test Case 9  | RecycleFutureCardController | TurnResult.CONTINUE | :x:          |
| Test Case 10 | AlterFutureCardController   | TurnResult.CONTINUE | :x:          |
| Test Case 11 | SeeFutureCardController     | TurnResult.CONTINUE | :x:          |