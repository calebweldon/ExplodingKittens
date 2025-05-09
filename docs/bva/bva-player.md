# BVA Analysis for Player

## Method 1: ```public void addCard(CardType card)```

### Step 1-3 Results
|        | Input 1                           | Input 2                 | Output                                                          |
|--------|-----------------------------------|-------------------------|-----------------------------------------------------------------|
| Step 1 | Player                            | Card                    | Player +1 Card                                                  |
| Step 2 | Cards collection                  | Card Enum               | Cards collection w/ incremented enum                            |
| Step 3 | Cards collection w/ [0, 1+] cards | Card [in, not in] cards | Cards collection +1 to card enum type, IllegalArgumentException |

### Step 4:
##### All-combination or each-choice: Each-choice
|             | System under test                                 | Expected output                        | Implemented?       |
|-------------|---------------------------------------------------|----------------------------------------|--------------------|
| Test Case 1 | Player w/ []; card = TACO_CAT                     | Player w/ [TACO_CAT]                   | :x:                |
| Test Case 2 | Player w/ [TACO_CAT]; card = POTATO_CAT           | Player w/ [TACO_CAT, POTATO_CAT]       | :x:                |
| Test Case 3 | Player w/ [TACO_CAT, POTATO_CAT]; card = TACO_CAT | Player w/ [TACO_CAT x2, POTATO_CAT]    | :x:                |
| Test Case 4 | Player w/ []; card = EXPLODING_KITTEN             | Player w/ []; IllegalArgumentException | :x:                |


## Method 2: ```public void playCard(CardType card)```

### Step 1-3 Results
|        | Input 1                           | Input 2                 | Output                                                                 |
|--------|-----------------------------------|-------------------------|------------------------------------------------------------------------|
| Step 1 | Player                            | Card                    | Player w/ card removed                                                 |
| Step 2 | Cards collection                  | Card Enum               | Cards collection -[1, 2] to card enum type                             |
| Step 3 | Cards collection w/ [0, 1+] cards | Card [in, not in] cards | Cards collection -[1, 2] to card enum type, InsufficientCardsException |

### Step 4:
##### All-combination or each-choice: Each-choice
|             | System under test                        | Expected output                                  | Implemented? |
|-------------|------------------------------------------|--------------------------------------------------|--------------|
| Test Case 1 | Player w/ [ATTACK]; card = ATTACK        | Player w/ []                                     | :x:          |
| Test Case 2 | Player w/ [ATTACK x2]; card = ATTACK     | Player w/ [ATTACK]                               | :x:          |
| Test Case 3 | Player w/ [TACO_CAT x2]; card = TACO_CAT | Player w/ []                                     | :x:          |
| Test Case 4 | Player w/ []; card = ATTACK              | Player w/ []; InsufficientCardsException         | :x:          |
| Test Case 6 | Player w/ [TACO_CAT]; card = TACO_CAT    | Player w/ [TACO_CAT]; InsufficientCardsException | :x:          |


## Method 3: ```public void removeCard(CardType card)```

### Step 1-3 Results
|        | Input 1                           | Input 2                 | Output                                                            |
|--------|-----------------------------------|-------------------------|-------------------------------------------------------------------|
| Step 1 | Player                            | Card                    | Player w/ card removed                                            |
| Step 2 | Cards collection                  | Card Enum               | Cards collection -1 to card enum type                             |
| Step 3 | Cards collection w/ [0, 1+] cards | Card [in, not in] cards | Cards collection -1 to card enum type, InsufficientCardsException |

> [!NOTE]
> This method is distinct from playCard, as it doesn't handle base kitten logic.

### Step 4:
##### All-combination or each-choice: Each-choice
|             | System under test                        | Expected output                          | Implemented? |
|-------------|------------------------------------------|------------------------------------------|--------------|
| Test Case 1 | Player w/ [ATTACK]; card = ATTACK        | Player w/ []                             | :x:          |
| Test Case 2 | Player w/ [ATTACK x2]; card = ATTACK     | Player w/ [ATTACK]                       | :x:          |
| Test Case 3 | Player w/ [TACO_CAT x2]; card = TACO_CAT | Player w/ [TACO_CAT]                     | :x:          |
| Test Case 4 | Player w/ []; card = ATTACK              | Player w/ []; InsufficientCardsException | :x:          |

