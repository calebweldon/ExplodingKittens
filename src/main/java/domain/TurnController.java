package domain;

import java.util.*;
import java.util.logging.Logger;

public class TurnController {
    private static final Logger log = Logger.getLogger(TurnController.class.getName());

    private final List<Player> players;
    private final Deck deck;
    private final Deque<Card> discardPile = new ArrayDeque<>();

    private int currentPlayerIdx = 0;
    private Direction direction = Direction.CLOCKWISE;
    private boolean gameOver = false;

    // Tracks how many extra draws the NEXT player must take (from Attack cards)
    private int pendingExtraTurns = 0;
    // If true, current player's draw phase is skipped
    private boolean skipNextDraw = false;

    private enum Direction { CLOCKWISE, COUNTERCLOCKWISE }

    /**
     * @param players non-empty list of players (must each have ≥1 Defuse before start)
     * @param deck a pre-built deck (with no Exploding Kittens yet)
     */
    public TurnController(List<Player> players, Deck deck) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Must have at least one player");
        }
        if (deck == null) {
            throw new IllegalArgumentException("Deck cannot be null");
        }
        this.players = new ArrayList<>(players);
        this.deck = deck;
    }

    /** Deals, inserts Kittens, shuffles, then runs the main loop until only one remains. */
    public void startGame() {
        dealInitialHands();
        insertExplodingKittens();
        deck.shuffleDeck();

        while (!gameOver) {
            Player current = players.get(currentPlayerIdx);
            if (current.isAlive()) {
                playPhase(current);
                drawPhase(current);
                checkGameOver();
            }
            advanceToNextPlayer();
        }

        announceWinner();
    }

    /** Each player gets 7 random cards + exactly one Defuse. */
    private void dealInitialHands() {
        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.addToHand(deck.drawCard());
            }
            // assume deck already contains enough Defuses up front
            p.addToHand(deck.drawCardOfType(CardType.DEFUSE));
        }
    }

    /** Inserts (#players – 1) Exploding Kittens at random positions. */
    private void insertExplodingKittens() {
        int n = players.size() - 1;
        for (int i = 0; i < n; i++) {
            deck.insertCardAtRandomIndex(new Card(CardType.EXPLODING_KITTEN));
        }
    }

    /** Let the player play 0+ cards in any order, ending when they choose “draw.” */
    private void playPhase(Player p) {
        while (true) {
            Optional<Card> choice = p.chooseCardToPlay();  
            if (!choice.isPresent()) {
                // player opts to draw
                return;
            }
            Card c = choice.get();
            p.removeFromHand(c);
            c.applyEffect(this, p);
            discardPile.push(c);

            // if they played a Skip, Stop the play phase immediately
            if (c.getCardType() == CardType.SKIP) {
                skipNextDraw = true;
                return;
            }
        }
    }

    /** Handles forced extra turns, skips, and the normal draw. */
    private void drawPhase(Player p) {
        if (skipNextDraw) {
            skipNextDraw = false;
            log.info(p.getName() + " skips their draw.");
            return;
        }

        int draws = Math.max(1, pendingExtraTurns);
        pendingExtraTurns = 0;

        for (int i = 0; i < draws; i++) {
            Card drawn = Optional.ofNullable(deck.drawCard())
                                 .orElseThrow(() -> new IllegalStateException("Deck is empty!"));
            if (drawn.getCardType() == CardType.EXPLODING_KITTEN) {
                handleExploding(p, drawn);
                if (!p.isAlive()) return;
            } else {
                p.addToHand(drawn);
            }
        }
    }

    /** Places kitten back or eliminates the player if they have no Defuse. */
    private void handleExploding(Player p, Card kitten) {
        log.warning(p.getName() + " drew an Exploding Kitten!");
        if (p.hasCardType(CardType.DEFUSE)) {
            p.removeCardOfType(CardType.DEFUSE);
            discardPile.push(new Card(CardType.DEFUSE));
            // let player choose where to re‐insert
            int pos = p.chooseInsertPosition(deck.getSize());
            deck.insertCardAtIndex(kitten, pos);
            log.info(p.getName() + " defused and reinserted at " + pos);
        } else {
            p.setAlive(false);
            discardPile.push(kitten);
            log.warning(p.getName() + " has exploded and is out!");
        }
    }

    /** Checked after each draw-phase. */
    private void checkGameOver() {
        long alive = players.stream().filter(Player::isAlive).count();
        if (alive <= 1) {
            gameOver = true;
        }
    }

    /** Advances `currentPlayerIdx` according to direction. */
    private void advanceToNextPlayer() {
        int step = (direction == Direction.CLOCKWISE ? 1 : -1);
        currentPlayerIdx = (currentPlayerIdx + step + players.size()) % players.size();
    }

    /** Announces the sole surviving player. */
    private void announceWinner() {
        players.stream()
               .filter(Player::isAlive)
               .findFirst()
               .ifPresent(w -> System.out.println(w.getName() + " wins!"));
    }

    // ───────────
    // Card‐effect helpers
    // ───────────

    /** Called by Attack cards. Next player must draw twice. */
    public void applyAttack() {
        pendingExtraTurns += 2;
    }

    /** Called by Shuffle cards. */
    public void applyShuffle() {
        deck.shuffleDeck();
    }

    /** Called by See the Future. */
    public List<Card> peekTop(int n) {
        List<Card> peeked = new ArrayList<>();
        for (int i = 0; i < n && i < deck.getSize(); i++) {
            peeked.add(deck.getCardAtIndex(i));
        }
        return peeked;
    }

    /** Called by Alter the Future. */
    public void reorderTop(List<Card> newOrder) {
        for (int i = 0; i < newOrder.size(); i++) {
            deck.insertCardAtIndex(newOrder.get(i), i);
        }
    }

    /** Called by Flip the Deck. */
    public void applyFlip() {
        deck.flipDeck();
    }

    /** Called by Favor cards: `from` gives one chosen card to `to`. */
    public void applyFavor(Player from, Player to) {
        Card given = from.chooseCardForFavor();
        from.removeFromHand(given);
        to.addToHand(given);
    }

    /** Called by a pair of matching cats. */
    public void applyPairSteal(Player from, Player to) {
        Card stolen = from.randomDiscard();
        from.removeFromHand(stolen);
        to.addToHand(stolen);
    }

    /** Called by Nope cards: you’d need an effect‐stack to fully support chaining. */
    public boolean applyNope() {
        // TODO: implement a stack of pending effects and allow players to cancel
        return true;
    }
}
