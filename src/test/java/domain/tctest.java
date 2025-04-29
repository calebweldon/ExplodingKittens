package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class TurnControllerTest {

    @Test
    public void constructor_validInputs_createsInstance() {
        List<Player> players = List.of(new Player("Alice"));
        Deck deck = new Deck();

        TurnController tc = new TurnController(players, deck);

        assertNotNull(tc);
    }

    @Test
    public void constructor_nullPlayers_throwsException() {
        Deck deck = new Deck();

        assertThrows(IllegalArgumentException.class, () -> {
            new TurnController(null, deck);
        });
    }

    @Test
    public void constructor_nullDeck_throwsException() {
        List<Player> players = List.of(new Player("Alice"));

        assertThrows(IllegalArgumentException.class, () -> {
            new TurnController(players, null);
        });
    }

    @Test
    public void applyAttack_runsWithoutError() {
        List<Player> players = List.of(new Player("Alice"));
        Deck deck = new Deck();
        TurnController tc = new TurnController(players, deck);

        tc.applyAttack(); // no crash = pass
    }

    @Test
    public void applyShuffle_runsWithoutError() {
        List<Player> players = List.of(new Player("Alice"));
        Deck deck = new Deck();
        TurnController tc = new TurnController(players, deck);

        tc.applyShuffle(); // no crash = pass
    }

    @Test
    public void peekTop_withEmptyDeck_returnsEmptyList() {
        List<Player> players = List.of(new Player("Alice"));
        Deck deck = new Deck();
        TurnController tc = new TurnController(players, deck);

        List<Card> peeked = tc.peekTop(3);
        assertTrue(peeked.isEmpty());
    }
}
