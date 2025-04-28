# BVA Analysis for Card

## Method 1: ```public void insertCardAtIndex(card Card, int index)```
### Step 1-3 Results
|        | input 1                               | input 2                                                | input 3              | Output                                                                              |
|--------|---------------------------------------|--------------------------------------------------------|----------------------|-------------------------------------------------------------------------------------|
| Step 1 | Deck                                  | Card                                                   | Number               | Deck                                                                                |
| Step 2 | collection                            | Card                                                   | interval [0,INT_MAX] | collection                                                                          |
| Step 3 | [], [Card(ATTACK)], array of max size | [Card(SKIP)], [Card(EXPLODING_KITTEN)], [Card(DEFUSE)] | -1, 0, INT_MAX       | IndexOutOfBoundsException, [Card(SKIP)], [Card(ATTACK), Card(EXPLODING_KITTEN)] |
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                        | Expected output                       | Implemented?       |
|-------------|------------------------------------------|---------------------------------------|--------------------|
| Test Case 1 | [], CARD(ATTACK), -1                     | IndexOutOfBoundsException             | :white_check_mark: |
| Test Case 2 | [], CARD(SKIP), INT_MAX                  | IndexOutOfBoundsException             | :white_check_mark: |
| Test Case 3 | [Card(NOPE)], Card(DEFUSE), 0            | [Card(DEFUSE), Card(NOPE)]            | :white_check_mark: |
| Test Case 4 | [Card(SHUFFLE)], Card(SEE_THE_FUTURE), 1 | [Card(SHUFFLE), Card(SEE_THE_FUTURE)] | :white_check_mark: |


## Method 2: ```public void insertCardAtRandomIndex(card Card)```
### Step 1-3 Results
|        | input 1             | input 2                                                | Output                                                |
|--------|---------------------|--------------------------------------------------------|-------------------------------------------------------|
| Step 1 | Deck                | Card                                                   | Deck                                                  |
| Step 2 | collection          | Card                                                   | collection                                            |
| Step 3 | [], [Card(SHUFFLE)] | [Card(SKIP)], [Card(EXPLODING_KITTEN)], [Card(DEFUSE)] | [Card(SKIP)], [Card(SHUFFLE), Card(EXPLODING_KITTEN)] |
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                     | Expected output                       | Implemented?       |
|-------------|---------------------------------------|---------------------------------------|--------------------|
| Test Case 1 | [], Card(DEFUSE)                      | [Card(Defuse)]                        | :white_check_mark: |
| Test Case 2 | [Card(SHUFFLE)], Card(SEE_THE_FUTURE) | [Card(SHUFFLE), Card(SEE_THE_FUTURE)] | :white_check_mark: |


## Method 3: ```public Card drawCard()```
### Note: 
 - White Box BVA: The Deck will never be empty since we will always reinsert Exploding Kittens
### Step 1-3 Results
|        | Input                                                 | Output 1                  | Output 2                     |
|--------|-------------------------------------------------------|---------------------------|------------------------------|
| Step 1 | Deck                                                  | Card                      | Deck                         |
| Step 2 | collection                                            | Card                      | collection                   |
| Step 3 | [Card(SHUFFLE)], [Card(SKIP), Card(EXPLODING_KITTEN)] | Card(SHUFFLE), Card(SKIP) | [], [Card(EXPLODING_KITTEN)] |
### Step 4:
##### All-combination or each-choice: Each-Choice

|             | System under test                    | Expected output | Implemented? |
|-------------|--------------------------------------|-----------------|--------------|
| Test Case 1 | [Card(SHUFFLE)]                      | Card(SHUFFLE)   | :x:          |
| Test Case 2 | [Card(SKIP), Card(EXPLODING_KITTEN)] | Card(SKIP)      | :x:          |

## Method 4: ```public void shuffleDeck()```

## Note:
 - It may be easiest to implement using Collection object's static shuffle() method...
 - If so, unit tests aren't viable and BVA may not be helpful
 - Discuss with professor

### Step 1-3 Results
|        | Input                                                                             | Output                                                                            |
|--------|-----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| Step 1 | Deck                                                                              | Deck                                                                              |
| Step 2 | collection                                                                        | collection                                                                        |
| Step 3 | [Card(SHUFFLE), Card(ATTACK)], [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)] | [Card(ATTACK), Card(SHUFFLE)], [Card(DEFUSE), Card(SKIP), Card(EXPLODING_KITTEN)] |
### Step 4:
##### All-combination or each-choice: Each-Choice

|             | System under test                                  | Expected output                                    | Implemented? |
|-------------|----------------------------------------------------|----------------------------------------------------|--------------|
| Test Case 1 | [Card(SHUFFLE), Card(ATTACK)]                      | [Card(ATTACK), Card(SHUFFLE)]                      | :x:          |
| Test Case 2 | [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)] | [Card(DEFUSE), Card(SKIP), Card(EXPLODING_KITTEN)] | :x:          |

## Method 5: ```public void flipDeck()```

## Note:
- It may be easiest to implement using Collection object's static reverse() method...
- If so, unit tests aren't viable and BVA may not be helpful
- Discuss with professor

### Step 1-3 Results
|        | Input                                                                             | Output                                                                            |
|--------|-----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| Step 1 | Deck                                                                              | Deck                                                                              |
| Step 2 | collection                                                                        | collection                                                                        |
| Step 3 | [Card(SHUFFLE), Card(ATTACK)], [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)] | [Card(ATTACK), Card(SHUFFLE)], [Card(DEFUSE), Card(EXPLODING_KITTEN), Card(SKIP)] |
### Step 4:
##### All-combination or each-choice: Each-Choice

|             | System under test                                  | Expected output                                    | Implemented? |
|-------------|----------------------------------------------------|----------------------------------------------------|--------------|
| Test Case 1 | [Card(SHUFFLE), Card(ATTACK)]                      | [Card(ATTACK), Card(SHUFFLE)]                      | :x:          |
| Test Case 2 | [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)] | [Card(DEFUSE), Card(EXPLODING_KITTEN), Card(SKIP)] | :x:          |

## Method 6: ```public void getCardAtIndex(int index)```
### Step 1-3 Results
|        | input 1                                            | input 3           | Output                                                                                    |
|--------|----------------------------------------------------|-------------------|-------------------------------------------------------------------------------------------|
| Step 1 | Deck                                               | Number            | Card                                                                                      |
| Step 2 | collection                                         | Interval          | Card                                                                                      |
| Step 3 | [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)] | -1, 0, 1, INT_MAX | IndexOutOfBoundsException, Card(SKIP), Card(EXPLODING_KITTEN)], IndexOutOfBoundsException |
### Step 4:
##### All-combination or each-choice: Each-choice

|             | System under test                                           | Expected output           | Implemented?       |
|-------------|-------------------------------------------------------------|---------------------------|--------------------|
| Test Case 1 | [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)], -1      | IndexOutOfBoundsException | :white_check_mark: |
| Test Case 2 | [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)], INT_MAX | IndexOutOfBoundsException | :white_check_mark: |
| Test Case 3 | [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)] 0        | Card(SKIP)                | :white_check_mark: |
| Test Case 4 | [Card(SKIP), Card(EXPLODING_KITTEN), Card(DEFUSE)] 1        | Card(EXPLODING_KITTEN)    | :white_check_mark: |
