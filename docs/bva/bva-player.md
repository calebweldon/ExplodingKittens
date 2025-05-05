# BVA Analysis for Player


## Method 1: ```public void drawCard(Deck deck)```

### Step 1-3 Results
|        | Input 1                                                   | Input 2                                               | Output                                                           |
|--------|-----------------------------------------------------------|-------------------------------------------------------|------------------------------------------------------------------|
| Step 1 | Player                                                    | Deck                                                  | Player +1 card, Deck -1 card                                     |
| Step 2 | Cards collection                                          | Deck collection                                       | Cards collection w/ another card, Deck collection w/ 1 less card |
| Step 3 | Cards collection w/ 0 cards, Cards collection w/ >0 cards | Deck collection w/ 1 card, Deck collection w/ >1 card | Cards collection w/ +1 card, Deck collection with -1 card        |

> [!NOTE]
> Deck will never be empty, as there will always be at least 1 exploding kitten in the deck.

### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                                    | Expected output                                                    | Implemented?       |
|-------------|----------------------------------------------------------------------|--------------------------------------------------------------------|--------------------|
| Test Case 1 | Player w/ []; deck w/ [TACO_CAT]                                     | Player w/ [TACO_CAT]; deck w/ []                                   | :white_check_mark: |
| Test Case 2 | Player w/ []; deck w/ [POTATO_CAT, SKIP, TACO_CAT, EXPLODING_KITTEN] | Player w/ [POTATO_CAT]; deck w/ [SKIP, TACO_CAT, EXPLODING_KITTEN] | :white_check_mark: |
| Test Case 3 | Player w/ [POTATO_CAT]; deck w/ [SKIP, TACO_CAT, EXPLODING_KITTEN]   | Player w/ [POTATO_CAT, SKIP]; deck w/ [TACO_CAT, EXPLODING_KITTEN] | :white_check_mark: |
| Test Case 4 | Player w/ [POTATO_CAT, SKIP]; deck w/ [TACO_CAT, EXPLODING_KITTEN]   | Player w/ [POTATO_CAT, SKIP, TACO_CAT]; deck w/ [EXPLODING_KITTEN] | :white_check_mark: |


## Method 2: ```public List<Card> viewHand()```
### Step 1-3 Results
|        | Input 1                                                   |  Output                                                 |
|--------|-----------------------------------------------------------|---------------------------------------------------------|
| Step 1 | Player                                                    | All cards in hand                                       |
| Step 2 | Cards collection                                          | Card collection                                         |
| Step 3 | Cards collection w/ 0 cards, cards collection w/ >0 cards | Card collection w/ 0 cards, card collcetion w/ >0 cards |

### Step 4:
##### All-combination or each-choice: Each-choice

|              | System under test                      | Expected output              | Implemented?       |
|--------------|----------------------------------------|------------------------------|--------------------|
| Test Case 1  | Player w/ []                           | []                           | :white_check_mark: |
| Test Case 2  | Player w/ [TACO_CAT]                   | [TACO_CAT]                   | :white_check_mark: |
| Test Case 3  | Player w/ [POTATO_CAT, SKIP, TACO_CAT] | [POTATO_CAT, SKIP, TACO_CAT] | :white_check_mark: |


## Method 3: ```public Card playCard(index: int)```
### Step 1-3 Results
|        | Input 1                                                   | Input 2                    | Output                          |
|--------|-----------------------------------------------------------|----------------------------|---------------------------------|
| Step 1 | Player                                                    | Card index                 | Card at card index              |
| Step 2 | Cards collection                                          | Number                     | Card                            |
| Step 3 | Cards collection w/ 0 cards, cards collection w/ >0 cards | 0, last index, -1, INT_MAX | Card, IndexOutOfBoundsException |

### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                               | Expected output                                 | Implemented? |
|-------------|-------------------------------------------------|-------------------------------------------------|--------------|
| Test Case 1 | Player w/ [TACO_CAT]; index 0                   | TACO_CAT; Player w/ []                          | :white_check_mark:          |
| Test Case 2 | Player w/ [POTATO_CAT, SKIP, TACO_CAT]; index 1 | SKIP; Player w/ [POTATO_CAT, TACO_CAT]          | :white_check_mark:          |
| Test Case 3 | Player w/ [POTATO_CAT, TACO_CAT]; index 1       | TACO_CAT; Player w/ [POTATO_CAT]                | :white_check_mark:          |
| Test Case 4 | Player w/ [TACO_CAT]; index 1                   | IndexOutOfBoundsException; Player w/ [TACO_CAT] | :white_check_mark:          |
| Test Case 5 | Player w/ [TACO_CAT]; index -1                  | IndexOutOfBoundsException; Player w/ [TACO_CAT] | :white_check_mark:          |
| Test Case 6 | Player w/ [TACO_CAT]; index INT_MAX             | IndexOutOfBoundsException; Player w/ [TACO_CAT] | :white_check_mark:          |
| Test Case 7 | Player w/ []; index 0                           | IndexOutOfBoundsException; Player w/ []         | :x:          |

