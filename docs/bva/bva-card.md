# BVA Analysis for Card

## Method 1: ```public CardType getCardType()```
### Step 1-3 Results
|        | Input                                                                                                                                                                      | (if more to consider for input)  | Output                 |
|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------|------------------------|
| Step 1 | Card                                                                                                                                                                       |                                  | CardType               |
| Step 2 | Card with a CardType field set                                                                                                                                             |                                  | CardType               |
| Step 3 | Cards of each CardType: EXPLODING_KITTEN, DEFUSE, ATTACK, SHUFFLE, SKIP, SEE_THE_FUTURE, NOPE, FAVOUR, TACO_CAT, CATTERMELLON, POTATO_CAT, BEARD_CAT, RAINBOW_RALPHING_CAT |                                  | Corresponding CardType |
### Step 4:
##### All-combination or each-choice: Each-choice

|              | System under test          | Expected output      | Implemented?              |
|--------------|----------------------------|----------------------|---------------------------|
| Test Case 1  | Card(EXPLODING_KITTEN)     | EXPLODING_KITTEN     | :white_check_mark:        |
| Test Case 2  | Card(DEFUSE)               | DEFUSE               | :white_check_mark:        |
| Test Case 3  | Card(ATTACK)               | ATTACK               | :white_check_mark:        |
| Test Case 4  | Card(SHUFFLE)              | SHUFFLE              | :white_check_mark:        |
| Test Case 5  | Card(SKIP)                 | SKIP                 | :white_check_mark:        |
| Test Case 6  | Card(SEE_THE_FUTURE)       | SEE_THE_FUTURE       | :white_check_mark:        |
| Test Case 7  | Card(NOPE)                 | NOPE                 | :white_check_mark:        |
| Test Case 8  | Card(FAVOUR)               | FAVOUR               | :white_check_mark:        |
| Test Case 9  | Card(TACO_CAT)             | TACO_CAT             | :white_check_mark:        |
| Test Case 10 | Card(CATTERMELLON)         | CATTERMELLON         | :white_check_mark:        |
| Test Case 11 | Card(POTATO_CAT)           | POTATO_CAT           | :white_check_mark:        |
| Test Case 12 | Card(BEARD_CAT)            | BEARD_CAT            | :white_check_mark:        |
| Test Case 13 | Card(RAINBOW_RALPHING_CAT) | RAINBOW_RALPHING_CAT | :white_check_mark:        |


## Method 2: ```public boolean checkIfFaceUp()```
### Step 1-3 Results
|        | Input                                                               | (if more to consider for input)  | Output        |
|--------|---------------------------------------------------------------------|----------------------------------|---------------|
| Step 1 | Card                                                                |                                  | true or false |
| Step 2 | Card with an isFaceUp field                                         |                                  | boolean       |
| Step 3 | Two cards, one with isFaceUp set to true and the other set to false |                                  | True or False |
### Step 4:
##### All-combination or each-choice: Each-choice

|              | System under test      | Expected output | Implemented? |
|--------------|------------------------|-----------------|--------------|
| Test Case 1  | Card, isFaceUp = True  | True            | :x:          |
| Test Case 2  | Card, isFaceUp = False | False           | :x:          |


## Method 3: ```public void setToFaceUp()```
### Step 1-3 Results
|        | Input                                                                | (if more to consider for input)  | Output                             |
|--------|----------------------------------------------------------------------|----------------------------------|------------------------------------|
| Step 1 | Card                                                                 |                                  | card is faceup                     |
| Step 2 | Card with an isFaceUp field                                          |                                  | Card.IsFaceUp                      |
| Step 3 | Two cards, one with isFaceUp set to true and the other set to false  |                                  | Card.IsFaceUp = true in both cases |
### Step 4:
##### All-combination or each-choice: All Combination

|              | System under test      | Expected output        | Implemented? |
|--------------|------------------------|------------------------|--------------|
| Test Case 1  | Card, isFaceUp = True  | Card.isFaceUp = true   | :x:          |
| Test Case 2  | Card, isFaceUp = False | Card.isFaceUp = true   | :x:          |

## Method 4: ```public void setToFaceDown()```
### Step 1-3 Results
|        | Input                                                                | (if more to consider for input)  | Output                              |
|--------|----------------------------------------------------------------------|----------------------------------|-------------------------------------|
| Step 1 | Card                                                                 |                                  | card is faceup                      |
| Step 2 | Card with an isFaceUp field                                          |                                  | Card.IsFaceUp                       |
| Step 3 | Two cards, one with isFaceUp set to true and the other set to false  |                                  | Card.IsFaceUp = false in both cases |
### Step 4:
##### All-combination or each-choice: All Combination

|              | System under test      | Expected output       | Implemented? |
|--------------|------------------------|-----------------------|--------------|
| Test Case 1  | Card, isFaceUp = True  | Card.isFaceUp = false | :x:          |
| Test Case 2  | Card, isFaceUp = False | Card.isFaceUp = false | :x:          |

