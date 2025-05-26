# BVA Analysis for SwapHandCardController

## Method 1: ```public void handleCardAction()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                                         | Expected output 1   | Expected output 2                                               | Implemented? |
|-------------|---------------------------------------------------------------------------|---------------------|-----------------------------------------------------------------|--------------|
| Test Case 1 | Three players, each with 6 card hands                                     | TurnResult.CONTINUE | Player who plays card swaps hand with the selected other player | :x:          |
| Test Case 1 | Two players, the person playing has 5 and swaps with someone who has zero | TurnResult.CONTINUE | Player who plays card swaps hand with the selected other player | :x:          |

    
