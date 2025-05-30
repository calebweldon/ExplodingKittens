# BVA Analysis for SwapHandCardController

## Method 1: ```public void handleCardAction()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                       | Expected output 1   | Expected output 2                                               | Implemented?       |
|-------------|---------------------------------------------------------|---------------------|-----------------------------------------------------------------|--------------------|
| Test Case 1 | Two players excluding currentPlayer, both with 6 cards. | TurnResult.CONTINUE | Player who plays card swaps hand with the selected other player | :white_check_mark: |
| Test Case 2 | One player excluding currentPlayer with 0 cards.        | TurnResult.CONTINUE | Player who plays card swaps hand with the selected other player | :white_check_mark: |

    
