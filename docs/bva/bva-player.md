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
| Test Case 1 | Player w/ []; card = TACO_CAT                     | Player w/ [TACO_CAT]                   | :white_check_mark: |
| Test Case 2 | Player w/ [TACO_CAT]; card = POTATO_CAT           | Player w/ [TACO_CAT, POTATO_CAT]       | :white_check_mark: |
| Test Case 3 | Player w/ [TACO_CAT, POTATO_CAT]; card = TACO_CAT | Player w/ [TACO_CAT x2, POTATO_CAT]    | :white_check_mark: |
| Test Case 4 | Player w/ []; card = EXPLODING_KITTEN             | Player w/ []; IllegalArgumentException | :white_check_mark: |


## Method 2: ```public void playCard(CardType card)```

### Step 1-3 Results
|        | Input 1                           | Input 2                                 | Output                                                               |
|--------|-----------------------------------|-----------------------------------------|----------------------------------------------------------------------|
| Step 1 | Player                            | Card                                    | Player w/ card removed                                               |
| Step 2 | Cards collection                  | Card Enum                               | Cards collection -[1, 2] to card enum type                           |
| Step 3 | Cards collection w/ [0, 1+] cards | [Basic Kitten?] Card [in, not in] cards | Cards collection -[1, 2] to card enum type, IllegalArgumentException |

### Step 4:
##### All-combination or each-choice: Each-choice
|             | System under test                        | Expected output                                | Implemented?       |
|-------------|------------------------------------------|------------------------------------------------|--------------------|
| Test Case 1 | Player w/ [ATTACK]; card = ATTACK        | Player w/ []                                   | :white_check_mark  |
| Test Case 2 | Player w/ [ATTACK x2]; card = ATTACK     | Player w/ [ATTACK]                             | :white_check_mark  |
| Test Case 3 | Player w/ [TACO_CAT x2]; card = TACO_CAT | Player w/ []                                   | :white_check_mark: |
| Test Case 4 | Player w/ []; card = ATTACK              | Player w/ []; IllegalArgumentException         | :white_check_mark: |
| Test Case 6 | Player w/ [TACO_CAT]; card = TACO_CAT    | Player w/ [TACO_CAT]; IllegalArgumentException | :white_check_mark: |


## Method 3: ```public void removeCard(CardType card)```

### Step 1-3 Results
|        | Input 1                           | Input 2                 | Output                                                          |
|--------|-----------------------------------|-------------------------|-----------------------------------------------------------------|
| Step 1 | Player                            | Card                    | Player w/ card removed                                          |
| Step 2 | Cards collection                  | Card Enum               | Cards collection -1 to card enum type                           |
| Step 3 | Cards collection w/ [0, 1+] cards | Card [in, not in] cards | Cards collection -1 to card enum type, IllegalArgumentException |

> [!NOTE]
> This method is distinct from playCard, as it doesn't handle basic kitten logic.

### Step 4:
##### All-combination or each-choice: Each-choice
|             | System under test                        | Expected output                        | Implemented?       |
|-------------|------------------------------------------|----------------------------------------|--------------------|
| Test Case 1 | Player w/ [ATTACK]; card = ATTACK        | Player w/ []                           | :white_check_mark: |
| Test Case 2 | Player w/ [ATTACK x2]; card = ATTACK     | Player w/ [ATTACK]                     | :white_check_mark: |
| Test Case 3 | Player w/ [TACO_CAT x2]; card = TACO_CAT | Player w/ [TACO_CAT]                   | :white_check_mark: |
| Test Case 4 | Player w/ []; card = ATTACK              | Player w/ []; IllegalArgumentException | :white_check_mark: |


## Method 4: ```public int getId()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test | Expected output 1 | Implemented?       |
|-------------|-------------------|-------------------|--------------------|
| Test Case 1 | Player.id = 1     | 1                 | :white_check_mark: |

## Method 5: ```public int getHandSize()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                    | Expected output 1 | Implemented?        |
|-------------|------------------------------------------------------|-------------------|---------------------|
| Test Case 1 | Player has hand size of 0                            | 0                 | :white_check_mark:  |
| Test Case 2 | Player has hand size of 3 with 2 different CardTypes | 3                 | :white_check_mark:  |


## Method 6: ```public void swapHandWith(Player playerToSwapWith)```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                             | Expected output 1               | Implemented?       |
|-------------|---------------------------------------------------------------|---------------------------------|--------------------|
| Test Case 1 | Two players, one with hand size of 0 and other with size of 3 | Players have reverse hand sizes | :white_check_mark: |

## Method 7: ```public CardType takeRandomCard()```
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test               | Expected output 1     | Implemented?       |
|-------------|---------------------------------|-----------------------|--------------------|
| Test Case 1 | Player has 1 card of cardType   | CardType              | :white_check_mark: |
| Test Case 2 | Player has >1 card              | Soem random card      | :x: |
| Test Case 3 | Player has no cards             | IllegalStateException | :x: |
