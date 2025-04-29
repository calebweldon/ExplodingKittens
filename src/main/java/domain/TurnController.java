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

    private int pendingExtraTurns = 0;
    private boolean skipNextDraw = false;

    private enum Direction { CLOCKWISE, COUNTERCLOCKWISE }

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

    private void dealInitialHands() {
        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.addToHand(deck.drawCard());
            }
            p.addToHand(deck.drawCardOfType(CardType.DEFUSE));
        }
    }

    private void insertExplodingKittens() {
        int n = players.size() - 1;
        for (int i = 0; i < n; i++) {
            deck.insertCardAtRandomIndex(new Card(CardType.EXPLODING_KITTEN));
        }
    }

    /** Called by PlayerController to play a card. */
    public void playCard(Card card, Player player) {
        player.removeFromHand(card);
        card.applyEffect(this, player);
        discardPile.push(card);

        if (card.getCardType() == CardType.SKIP) {
            skipNextDraw = true;
        }
    }

    private void playPhase(Player p) {
        while (true) {
            Optional<Card> choice = p.chooseCardToPlay();
            if (!choice.isPresent()) {
                return;
            }
            Card c = choice.get();
            playCard(c, p);

            if (c.getCardType() == CardType.SKIP) {
                return;
            }
        }
    }

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

    private void handleExploding(Player p, Card kitten) {
        log.warning(p.getName() + " drew an Exploding Kitten!");
        if (p.hasCardType(CardType.DEFUSE)) {
            p.removeCardOfType(CardType.DEFUSE);
            discardPile.push(new Card(CardType.DEFUSE));
            int pos = p.chooseInsertPosition(deck.getSize());
            deck.insertCardAtIndex(kitten, pos);
            log.info(p.getName() + " defused and reinserted at " + pos);
        } else {
            p.setAlive(false);
            discardPile.push(kitten);
            log.warning(p.getName() + " has exploded and is out!");
        }
    }

    private void checkGameOver() {
        long alive = players.stream().filter(Player::isAlive).count();
        if (alive <= 1) {
            gameOver = true;
        }
    }

    private void advanceToNextPlayer() {
        int step = (direction == Direction.CLOCKWISE ? 1 : -1);
        currentPlayerIdx = (currentPlayerIdx + step + players.size()) % players.size();
    }

    private void announceWinner() {
        players.stream()
               .filter(Player::isAlive)
               .findFirst()
               .ifPresent(w -> System.out.println(w.getName() + " wins!"));
    }

    // ───────────
    // some helpers
    // ───────────

    public void applyAttack() {
        pendingExtraTurns += 2;
    }

    public void applyShuffle() {
        deck.shuffleDeck();
    }

    public List<Card> peekTop(int n) {
        List<Card> peeked = new ArrayList<>();
        for (int i = 0; i < n && i < deck.getSize(); i++) {
            peeked.add(deck.getCardAtIndex(i));
        }
        return peeked;
    }

    public void reorderTop(List<Card> newOrder) {
        for (int i = 0; i < newOrder.size(); i++) {
            deck.insertCardAtIndex(newOrder.get(i), i);
        }
    }

    public void applyFlip() {
        deck.flipDeck();
    }

    public void applyFavor(Player from, Player to) {
        Card given = from.chooseCardForFavor();
        from.removeFromHand(given);
        to.addToHand(given);
    }

    public void applyPairSteal(Player from, Player to) {
        Card stolen = from.randomDiscard();
        from.removeFromHand(stolen);
        to.addToHand(stolen);
    }
}
