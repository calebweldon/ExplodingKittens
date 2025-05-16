# BVA Analysis for Deck

## Method 1: ```public void insertCardAtIndex(CardType card, int index)```
### Step 1-3 Results
|        | input 1                                  | input 2                                                         | input 3              | Output                                                                                   |
|--------|------------------------------------------|-----------------------------------------------------------------|----------------------|------------------------------------------------------------------------------------------|
| Step 1 | Deck                                     | CardType                                                        | Number               | Deck                                                                                     |
| Step 2 | collection                               | CardType                                                        | interval [0,INT_MAX] | collection                                                                               |
| Step 3 | [], [CardType.ATTACK], array of max size | [CardType.SKIP], [CardType.EXPLODING_KITTEN], [CardType.DEFUSE] | -1, 0, INT_MAX       | IndexOutOfBoundsException, [CardType.SKIP], [CardType.ATTACK, CardType.EXPLODING_KITTEN] |
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                              | Expected output                             | Implemented?       |
|-------------|------------------------------------------------|---------------------------------------------|--------------------|
| Test Case 1 | [], CardType.ATTACK, -1                        | IndexOutOfBoundsException                   | :white_check_mark: |
| Test Case 2 | [], CardType.SKIP, INT_MAX                     | IndexOutOfBoundsException                   | :white_check_mark: |
| Test Case 3 | [], CardType.DEFUSE, 0                         | [CardType.DEFUSE]                           | :white_check_mark: |
| Test Case 4 | [CardType.SHUFFLE], CardType.SEE_THE_FUTURE, 1 | [CardType.SHUFFLE, CardType.SEE_THE_FUTURE] | :white_check_mark: |


## Method 2: ```public void insertCardAtRandomIndex(CardType Card)```
### Step 1-3 Results
|        | input 1                | input 2                                                         | Output                                                         |
|--------|------------------------|-----------------------------------------------------------------|----------------------------------------------------------------|
| Step 1 | Deck                   | Card                                                            | Deck                                                           |
| Step 2 | collection             | Card                                                            | collection                                                     |
| Step 3 | [], [CardType.SHUFFLE] | [CardType.SKIP], [CardType.EXPLODING_KITTEN], [CardType.DEFUSE] | [CardType.SKIP], [CardType.SHUFFLE, CardType.EXPLODING_KITTEN] |
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                            | Expected output                             | Implemented?       |
|-------------|----------------------------------------------|---------------------------------------------|--------------------|
| Test Case 1 | [], CardType.DEFUSE                          | [CardType.DEFUSE]                           | :white_check_mark: |
| Test Case 2 | [CardType.SHUFFLE], CardType.SEE_THE_FUTURE) | [CardType.SHUFFLE, CardType.SEE_THE_FUTURE] | :white_check_mark: |


## Method 3: ```public CardType drawCard()```
### Note: 
 - White Box BVA: The Deck will never be empty since we will always reinsert Exploding Kittens
### Step 1-3 Results
|        | Input                                                          | Output 1                        | Output 2                        |
|--------|----------------------------------------------------------------|---------------------------------|---------------------------------|
| Step 1 | Deck                                                           | CardType                        | Deck                            |
| Step 2 | collection                                                     | CardType                        | collection                      |
| Step 3 | [CardType.SHUFFLE], [CardType.SKIP, CardType.EXPLODING_KITTEN] | CardType.SHUFFLE, CardType.SKIP | [], [CardType.EXPLODING_KITTEN] |
### Step 4:
##### All-combination or each-choice: Each-Choice

|             | System under test                                           | Expected output           | Implemented?       |
|-------------|-------------------------------------------------------------|---------------------------|--------------------|
| Test Case 1 | [CardType.EXPLODING_KITTEN]                                 | CardType.EXPLODING_KITTEN | :white_check_mark: |
| Test Case 2 | [CardType.SKIP, CardType.EXPLODING_KITTEN]                  | CardType.SKIP             | :white_check_mark: |
| Test Case 2 | [CardType.ATTACK, CardType.SKIP, CardType.EXPLODING_KITTEN] | CardType.ATTACK           | :white_check_mark: |


## Method 4: ```public void shuffleDeck()```

## Note:
 - Implement using Collection object's static shuffle() method. Hence, not unit-testable


## Method 5: ```public void flipDeck()```

## Note:
- Implement using Collection object's static reverse() method. Hence, not unit-testable


## Method 6: ```public CardType getCardAtIndex(int index)```
### Step 1-3 Results
|        | input 1                                                     | input 2           | Output                                                                                          |
|--------|-------------------------------------------------------------|-------------------|-------------------------------------------------------------------------------------------------|
| Step 1 | Deck                                                        | Number            | CardType                                                                                        |
| Step 2 | collection                                                  | Interval          | CardType                                                                                        |
| Step 3 | [CardType.SKIP, CardType.EXPLODING_KITTEN, CardType.DEFUSE] | -1, 0, 1, INT_MAX | IndexOutOfBoundsException, CardType.SKIP, CardType.EXPLODING_KITTEN], IndexOutOfBoundsException |

### Step 4:
##### All-combination or each-choice: Each-choice
|             | System under test                                                    | Expected output           | Implemented?       |
|-------------|----------------------------------------------------------------------|---------------------------|--------------------|
| Test Case 1 | [CardType.SKIP, CardType.EXPLODING_KITTEN, CardType.DEFUSE], -1      | IndexOutOfBoundsException | :white_check_mark: |
| Test Case 2 | [CardType.SKIP, CardType.EXPLODING_KITTEN, CardType.DEFUSE], INT_MAX | IndexOutOfBoundsException | :white_check_mark: |
| Test Case 3 | [CardType.SKIP, CardType.EXPLODING_KITTEN, CardType.DEFUSE] 0        | CardType.SKIP             | :white_check_mark: |
| Test Case 4 | [CardType.SKIP, CardType.EXPLODING_KITTEN, CardType.DEFUSE] 1        | CardType.EXPLODING_KITTEN | :white_check_mark: |


## Method 7: ```public CardType drawCardFromBottom()```
### Note:
- White Box BVA: The Deck will never be empty since we will always reinsert Exploding Kittens
### Step 1-3 Results
|        | Input                                                          | Output 1                                    | Output 2            |
|--------|----------------------------------------------------------------|---------------------------------------------|---------------------|
| Step 1 | Deck                                                           | CardType                                    | Deck                |
| Step 2 | collection                                                     | CardType                                    | collection          |
| Step 3 | [CardType.SHUFFLE], [CardType.SKIP, CardType.EXPLODING_KITTEN] | CardType.SHUFFLE, CardType.EXPLODING_KITTEN | [], [CardType.SKIP] |

### Step 4:
##### All-combination or each-choice: Each-Choice
|             | System under test                                           | Expected output           | Implemented?       |
|-------------|-------------------------------------------------------------|---------------------------|--------------------|
| Test Case 1 | [CardType.ATTACK]                                           | CardType.ATTACK           | :white_check_mark: |
| Test Case 2 | [CardType.ATTACK, CardType.SKIP]                            | CardType.SKIP             | :white_check_mark: |
| Test Case 2 | [CardType.ATTACK, CardType.SKIP, CardType.EXPLODING_KITTEN] | CardType.EXPLODING_KITTEN | :white_check_mark: |


## Method 8: ```public void addSpecialCards(int numExplodingCards)```
### Step 1-3 Results
|        | input 1    | input 2    | Output                                                                       |
|--------|------------|------------|------------------------------------------------------------------------------|
| Step 1 | Deck       | Number     | Deck                                                                         |
| Step 2 | collection | Interval   | collection                                                                   |
| Step 3 | Full Deck  | 1, 2, 3, 4 | Full Deck with exploding kittens, defuses, explodia, and an imploding kitten |

### Step 4:
##### All-combination or each-choice: Each-Choice
|             | System under test | Expected output                    | Implemented?       |
|-------------|-------------------|------------------------------------|--------------------|
| Test Case 1 | Deck, 1           | Full Deck with 1 Exploding Kitten  | :white_check_mark: |
| Test Case 2 | Deck, 2           | Full Deck with 2 Exploding Kittens | :white_check_mark: |
| Test Case 3 | Deck, 3           | Full Deck with 3 Exploding Kittens | :white_check_mark: |
| Test Case 4 | Deck, 4           | Full Deck with 4 Exploding Kittens | :white_check_mark: |


## Method 9: ```public int getImplodingIndex()```
### Step 1-3 Results
|        | input 1                                                   | Output     |
|--------|-----------------------------------------------------------|------------|
| Step 1 | Deck                                                      | Number     |
| Step 2 | collection                                                | Collection |
| Step 3 | Deck with IMPLODING_FACEUP, Deck without IMPLODING_FACEUP | 0, -1      |

### Step 4:
##### All-combination or each-choice: Each-Choice
|             | System under test                   | Expected output | Implemented? |
|-------------|-------------------------------------|-----------------|--------------|
| Test Case 1 | Deck / CardType.IMPLODING_FACEUP    | -1              | :x:          |
| Test Case 2 | Deck with CardType.IMPLODING_FACEUP | 0               | :x:          |