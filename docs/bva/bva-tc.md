# BVA Analysis for TurnController

## 1. Constructor: public TurnController(Deck deck)
Case | deck      | Expected Behavior
---- | --------- | -----------------
C1   | null      | NPE or IllegalArgumentException at construction
C2   | non-null  | Instance created successfully

## 2. takeTurn(Player player)
```java
public boolean takeTurn(Player player)
```

### 2.1. player parameter
Case | player    | Expected Behavior
---- | --------- | -----------------
T1   | null      | NPE on entry
T2   | non-null  | Enters turn loop

### 2.2. Empty vs. non-empty hand (for choosing card)
Case | Hand size | First command = "play" | Behavior
---- | --------- | ----------------------- | ---------------------------------------------------
T3   | 0         | "play" → then "end"     | Prints “no cards to play”, loops until "end"
T4   | ≥1        | "play" + valid index    | Removes chosen card, calls stubbed playCard logic

### 2.3. Ending the turn ("end")
Case | Deck size | Drawn CardType      | Expected return / side-effect
---- | --------- | ------------------- | -------------------------------------------------------
T5   | 0         | N/A                 | Exception (underflow) or defined deck-empty behavior
T6   | ≥1        | EXPLODING_KITTEN    | Delegate to handleExplodingKitten(...) (see §3)
T7   | ≥1        | any other type      | Card added to hand; method returns true (survived)

## 3. handleExplodingKitten(Player player)
```java
private boolean handleExplodingKitten(Player player)
```

Case | # of DEFUSE cards in hand | Expected return / side-effect
---- | ------------------------- | ---------------------------------------------------------
H1   | 0                         | Prints “No defuse—you're out!”; returns false
H2   | 1                         | Removes one defuse; reinserts EK randomly; prints “Defuse! You stay in.”; returns true
H3   | >1                        | Same as H2 (only first defuse used)

## 4. promptCardIndex(Player player)
```java
private int promptCardIndex(Player player)
```
Let N = player.getHand().size() (assume N ≥ 1 when called).

Case | Input String             | Parsed Index | Expected Behavior
---- | ------------------------ | ------------ | -----------------------------------------------
P1   | "-1"                     | -1           | “Invalid index” message; reprompt
P2   | "0"                      | 0            | Returns 0
P3   | String.valueOf(N-1)      | N-1          | Returns N-1
P4   | String.valueOf(N)        | N            | “Invalid index” message; reprompt
P5   | "abc"                    | N/A          | “Please enter a valid number”; reprompt
P6   | (any valid)              | in [0,N−1]   | Returns parsed integer
