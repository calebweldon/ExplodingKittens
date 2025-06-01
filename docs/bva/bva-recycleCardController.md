# BVA Analysis for RecycleCardController

## Method 1: ```public void handleCardAction()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test   | Expected output 1   | Expected output 2                                               | Implemented?       |
|-------------|---------------------|---------------------|----------------------------|-----|
| Test Case 1 | Attack last played  | TurnResult.CONTINUE | Last played is now Recycle | :x: |
| Test Case 2 | Defuse last played  | TurnResult.CONTINUE | Last played is now Recycle | :x: |
| Test Case 3 | No last played      | TurnResult.CONTINUE | Recycle readded to player  | :x: |
| Test Case 4 | Recycle last played | TurnResult.CONTINUE | Recycle readded to player  | :x: |

