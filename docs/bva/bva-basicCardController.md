# BVA Analysis for BasicCardController

## Method 1: ```public void handleCardAction()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                       | Expected output 1   | Expected output 2                                    | Implemented?       |
|-------------|---------------------------------------------------------|---------------------|------------------------------------------------------|--------------------|
| Test Case 1 | Player has second basic card, other player has card     | TurnResult.CONTINUE | Player - basic card, + random card from other player | :x: |
| Test Case 2 | Player has second basic card, other player has no cards | TurnResult.CONTINUE | Player refunded basic card                           | :x: |
| Test case 3 | Player has no second basic card                         | TurnResult.CONTINUE | Player refunded basic card                           | :x: |
