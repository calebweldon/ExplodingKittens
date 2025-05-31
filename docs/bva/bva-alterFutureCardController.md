# BVA Analysis for AlterFutureCardController

## Method 1: ```public void handleCardAction()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                    | Expected output 1   | Expected output 2 | Implemented?       |
|-------------|--------------------------------------|---------------------|-------------------|--------------------|
| Test Case 1 | Deck with >=3 cards; same order      | TurnResult.CONTINUE | Deck, same order  | :x: |
| Test Case 2 | Deck with >=3 cards; different order | TurnResult.CONTINUE | Deck, reordered   | :x: |
| Test Case 3 | Deck with <3 cards; same order       | TurnResult.CONTINUE | Deck, same order  | :x: |
| Test Case 4 | Deck with <3 cards; different order  | TurnResult.CONTINUE | Deck, reordered   | :x: |


