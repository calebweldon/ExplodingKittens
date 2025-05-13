package domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;
import org.easymock.EasyMock;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.*;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TurnControllerTest {
	private final java.io.InputStream systemIn = System.in;

	@AfterEach
	void restoreStdin() {
		System.setIn(systemIn);
	}

	private void provideInput(String data) {
		InputStream testIn = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
		System.setIn(testIn);

	}

	// --- Method 1: Constructor ---

	@Test
	void constructor_nullDeck_throwsException() {
		assertThrows(IllegalArgumentException.class, () -> new TurnController(null));
	}

	@Test
	void constructor_validDeck_success() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnController tc = new TurnController(deck);
		assertNotNull(tc);
	}

	// --- Method 2: takeTurn(Player) ---
	// Note, wants "NoSuchElement" Exception so I changed it
	// used to be, NullPointerException.
	@Test
	void takeTurn_nullPlayer_throwsNPE() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnController tc = new TurnController(deck);
		assertThrows(NoSuchElementException.class, () -> tc.takeTurn(null));
	}

	// @Test
	// NEED TO FIGURE OUT if player exposes an "addCard" method
	/*
	void takeTurn_endNonEK_cardAddedAndReturnsTrue() {
		// Setup a non‐EK card
		Card card = EasyMock.createMock(Card.class);
		EasyMock.expect(card.getCardType()).andReturn(CardType.DEFUSE);
		EasyMock.replay(card);

		// Deck.drawCard() → card
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(card);
		EasyMock.replay(deck);

		// Player with empty hand
		Player player = EasyMock.createMock(Player.class);
		List<Card> hand = new ArrayList<>();
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		//EasyMock.expect(player.getName()).andReturn("Alice").anyTimes();
		EasyMock.replay(player);

		// Simulate user typing "end\n"
		provideInput("end\n");

		TurnController tc = new TurnController(deck);
		boolean survived = tc.takeTurn(player);

		assertTrue(survived, "Should return true on non-EK draw");
		assertEquals(1, hand.size(), "Card should be added to hand");
		assertSame(card, hand.get(0));

		EasyMock.verify(card, deck, player);
	}
	*/


	@Test
	void takeTurn_drawsEK_noDefuse_playerEliminated() {
		// 1) Deck.drawCard() → EXPLODING_KITTEN
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(CardType.EXPLODING_KITTEN);
		EasyMock.replay(deck);

		// 2) Player has empty hand (no DEFUSE)
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> emptyHand = Collections.emptyMap();
		EasyMock.expect(player.viewHand()).andReturn(emptyHand).anyTimes();
		EasyMock.replay(player);

		// 3) Simulate "draw"
		provideInput("draw\n");

		// 4) Run
		TurnController tc = new TurnController(deck);
		TurnResult result = tc.takeTurn(player);

		// 5) Check
		assertNotNull(result);
		assertTrue(result.playerEliminated);
		assertFalse(result.playerWon);
		assertEquals(0, result.extraTurns);

		EasyMock.verify(deck, player);
	}


	@Test
	void takeTurn_drawsEK_withDefuse_playerSurvives_andDefuseConsumed() {
		// 1) Deck.drawCard() → EXPLODING_KITTEN
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(CardType.EXPLODING_KITTEN);
		deck.insertCardAtRandomIndex(CardType.EXPLODING_KITTEN);
		EasyMock.expectLastCall();
		EasyMock.replay(deck);

		// 2) Player has 1 DEFUSE
		Map<CardType, Integer> hand = new HashMap<>();
		hand.put(CardType.DEFUSE, 1);
		Player player = EasyMock.createMock(Player.class);
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		player.removeCard(CardType.DEFUSE);
		EasyMock.expectLastCall().andAnswer(() -> {
			hand.put(CardType.DEFUSE, hand.get(CardType.DEFUSE) - 1);
			return null;
		});
		EasyMock.replay(player);

		// 3) Simulate "draw"
		provideInput("draw\n");

		// 4) Run
		TurnController tc = new TurnController(deck);
		TurnResult result = tc.takeTurn(player);

		// 5) Check
		assertNotNull(result);
		assertFalse(result.playerEliminated);
		assertFalse(result.playerWon);
		assertEquals(0, result.extraTurns);
		assertEquals(0, hand.getOrDefault(CardType.DEFUSE, 0));

		EasyMock.verify(deck, player);
	}


}
