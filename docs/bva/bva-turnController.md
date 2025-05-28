# BVA Analysis for TurnController

[comment]: # (TODO: Remove - unnecessary for constructor)
## Method 1: ```public TurnController(Deck deck)```
### Step 1-3 Results
|        | Input         | (if more to consider for input) | Output                         |
|--------|---------------|---------------------------------|--------------------------------|
| Step 1 | deck parameter| any Deck reference              | new TurnController instance    |
| Step 2 | deck = null   |                                 | NPE or IllegalArgumentException|
| Step 3 | deck ≠ null   |                                 | instance created successfully  |

### Step 4:
##### Each-choice

|              | System under test      | Expected output                                    | Implemented? |
|--------------|------------------------|----------------------------------------------------|--------------|
| Test Case 1  | deck = null            | constructor throws NPE or IllegalArgumentException | ✅           |
| Test Case 2  | deck ≠ null            | new TurnController created                         | ✅           |


## Method 2: ```public boolean takeTurn(Player player)```
### Step 1-3 Results
|        | Input           | (if more to consider for input) | Output                             |
|--------|-----------------|---------------------------------|------------------------------------|
| Step 1 | player parameter| any Player reference            | boolean (survived or eliminated)   |
| Step 2 | player = null   |                                 | NPE on entry                       |
| Step 3 | player ≠ null   |                                 | enters turn loop                   |

### Step 4:
##### Each-choice

|              | System under test                                    | Expected output                                                      | Implemented? |
|--------------|------------------------------------------------------|----------------------------------------------------------------------|--------------|
| Test Case 1  | player = null                                        | NPE on entry                                                         | ✅           |
| Test Case 2  | player ≠ null, deck.drawCard() returns non-EK        | card added to hand; returns true                                     | ✅           |
| Test Case 3  | player ≠ null, deck.drawCard() returns EK, no defuse | prints “No defuse—you're out!”; returns false                        | ✅           |
| Test Case 4  | player ≠ null, deck.drawCard() returns EK, has defuse| removes defuse; reinserts EK; prints “Defuse! You stay in.”; returns true | ✅      |


[comment]: # (TODO: Remove - unnecessary for private)
## Method 3: ```private boolean handleExplodingKitten(Player player)```
### Step 1-3 Results
|        | Input                          | (if more to consider for input) | Output                                                |
|--------|--------------------------------|---------------------------------|-------------------------------------------------------|
| Step 1 | player.viewHand() contents      | list of cards in hand           | boolean (survived or eliminated)                      |
| Step 2 | no DEFUSE cards in hand        |                                 | prints “No defuse—you're out!”; returns false         |
| Step 3 | ≥1 DEFUSE card in hand         |                                 | removes one DEFUSE; reinserts EK; prints defuse msg; returns true |

### Step 4:
##### Each-choice

|              | System under test                          | Expected output                                              | Implemented? |
|--------------|---------------------------------------------|--------------------------------------------------------------|--------------|
| Test Case 1  | hand has 0 DEFUSE                           | prints out-of-game; returns false                            | ✅           |
| Test Case 2  | hand has 1 DEFUSE                           | removes defuse; re-inserts EK; prints stay-in; returns true  | ✅           |
| Test Case 3  | hand has >1 DEFUSE                          | same behavior as 1 DEFUSE                                    | ✅           |


[comment]: # (TODO: Remove - unnecessary for private)
## Method 4: ```private int promptCardIndex(Player player)```
### Step 1-3 Results
|        | Input                 | (if more to consider for input)   | Output                        |
|--------|-----------------------|-----------------------------------|-------------------------------|
| Step 1 | raw String from Scanner| any user input                   | integer index or reprompt     |
| Step 2 | non-numeric or out of bounds| invalid integer or String   | prints error; loops           |
| Step 3 | valid integer in range| 0 ≤ idx < player.viewHand().size()| returns idx                   |

### Step 4:
##### Each-choice

|              | System under test                                   | Expected output                                   | Implemented? |
|--------------|------------------------------------------------------|---------------------------------------------------|--------------|
| Test Case 1  | input = "-1"                                        | “Invalid index”; reprompt                        | ✅           |
| Test Case 2  | input = "0", hand size ≥1                           | returns 0                                         | ✅           |
| Test Case 3  | input = String.valueOf(N-1)                         | returns N-1                                       | ✅           |
| Test Case 4  | input = String.valueOf(N)                           | “Invalid index”; reprompt                        | ✅           |
| Test Case 5  | input = "abc"                                       | “Please enter a valid number”; reprompt          | ✅           |
| Test Case 6  | input in [0, N-1]                                   | returns parsed integer                           | ✅           |
