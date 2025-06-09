# BVA Analysis for BasicCardController

## Method 1: ```public void handleCardAction()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test         | Expected output 1   | Expected output 2                                    | Implemented?       |
|-------------|---------------------------|---------------------|------------------------------------------------------|--------------------|
| Test Case 1 | Other player has card     | TurnResult.CONTINUE | Player - basic card, + random card from other player | :white_check_mark: |
| Test Case 2 | Other player has no cards | TurnResult.CONTINUE | Player refunded basic cards                          | :white_check_mark: |
