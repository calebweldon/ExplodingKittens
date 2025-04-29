# BVA Analysis for TurnController

## Constructor: public TurnController(List<Player> players, Deck deck)

### Step 1-3 Results
|        | Input                                | (if more to consider for input) | Output             |
|--------|--------------------------------------|---------------------------------|--------------------|
| Step 1 | List of players, Deck object         |                                 | New TurnController |
| Step 2 | Players must be non-null, non-empty  |                                 | Exception or success |
| Step 3 | Deck must be non-null                |                                 | Exception or success |

### Step 4:
##### Each-choice

|              | System under test                  | Expected output        | Implemented?        |
|--------------|-------------------------------------|-------------------------|---------------------|
| Test Case 1  | null players, valid deck            | IllegalArgumentException | ✅ |
| Test Case 2  | empty players list, valid deck      | IllegalArgumentException | ✅ |
| Test Case 3  | valid players, null deck            | IllegalArgumentException | ✅ |
| Test Case 4  | valid players, valid deck           | Success (instance created) | ✅ |


## Method 1: public void startGame()

### Step 1-3 Results
|        | Input                  | (if more to consider for input) | Output                      |
|--------|-------------------------|---------------------------------|------------------------------|
| Step 1 | Pre-built players and deck |                               | Game plays to completion    |
| Step 2 | Valid setup with Defuse cards |                            | Winner announced            |
| Step 3 | Game with multiple players   |                             | Game ends properly           |

### Step 4:
##### Each-choice

|              | System under test               | Expected output                | Implemented?        |
|--------------|----------------------------------|---------------------------------|---------------------|
| Test Case 1  | Game starts with 2 players       | 1 winner announced              | ✅ |
| Test Case 2  | Game starts with 4 players       | 1 winner announced              | ✅ |


# BVA Analysis for TurnController (Helper Methods)

## Method: public void applyAttack()

### Step 1-3 Results
|        | Input     | (if more to consider for input) | Output               |
|--------|-----------|---------------------------------|----------------------|
| Step 1 | Call applyAttack |                         | Adds +2 pending turns |

### Step 4:
##### Each-choice

|              | System under test            | Expected output         | Implemented?        |
|--------------|-------------------------------|--------------------------|---------------------|
| Test Case 1  | pendingExtraTurns = 0, call applyAttack | pendingExtraTurns = 2 | ✅ |
| Test Case 2  | pendingExtraTurns = 1, call applyAttack | pendingExtraTurns = 3 | ✅ |


## Method: public void applyShuffle()

### Step 1-3 Results
|        | Input     | (if more to consider for input) | Output          |
|--------|-----------|---------------------------------|-----------------|
| Step 1 | Call applyShuffle |                       | Deck is shuffled |

### Step 4:
##### Each-choice

|              | System under test         | Expected output   | Implemented?        |
|--------------|----------------------------|-------------------|---------------------|
| Test Case 1  | applyShuffle called         | Deck is shuffled  | ✅ |


## Method: public List<Card> peekTop(int n)

### Step 1-3 Results
|        | Input       | (if more to consider for input) | Output                         |
|--------|-------------|---------------------------------|--------------------------------|
| Step 1 | int n        |                                 | Top n cards from deck          |
| Step 2 | Deck smaller than n |                        | As many as available           |

### Step 4:
##### Each-choice

|              | System under test              | Expected output                          | Implemented?        |
|--------------|---------------------------------|------------------------------------------|---------------------|
| Test Case 1  | n = 3, deck has ≥3 cards         | Returns 3 cards                         | ✅ |
| Test Case 2  | n = 5, deck has 2 cards          | Returns 2 cards                         | ✅ |
| Test Case 3  | n = 0                           | Returns empty list                      | ✅ |


## Method: public void reorderTop(List<Card> newOrder)

### Step 1-3 Results
|        | Input                | (if more to consider for input) | Output             |
|--------|----------------------|---------------------------------|--------------------|
| Step 1 | newOrder list of cards |                                 | Top of deck reordered |

### Step 4:
##### Each-choice

|              | System under test                | Expected output              | Implemented?        |
|--------------|-----------------------------------|-------------------------------|---------------------|
| Test Case 1  | Reorder 3 cards at top            | Top 3 cards match newOrder    | ✅ |
| Test Case 2  | Reorder empty list                | No change                    | ✅ |


## Method: public void applyFavor(Player from, Player to)

### Step 1-3 Results
|        | Input                         | (if more to consider for input) | Output                |
|--------|-------------------------------|---------------------------------|-----------------------|
| Step 1 | from and to are valid players  |                                 | One card transferred  |
| Step 2 | from has no cards              |                                 | May crash if unchecked |

### Step 4:
##### Each-choice

|              | System under test                        | Expected output                 | Implemented?        |
|--------------|-------------------------------------------|----------------------------------|---------------------|
| Test Case 1  | from has cards, to receives one           | to's hand +1 card                | ✅ |
| Test Case 2  | from has no cards                         | Should throw or handle error     | (Depends on Player class) |
