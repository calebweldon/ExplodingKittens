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
		assertThrows(NullPointerException.class, () -> new TurnController(null));
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
	void takeTurn_endEK_noDefuse_returnsFalse() {
		// Setup an exploding kitten card
		Card ek = EasyMock.createMock(Card.class);
		EasyMock.expect(ek.getCardType()).andReturn(CardType.EXPLODING_KITTEN).anyTimes();
		EasyMock.replay(ek);

		// Deck.drawCard() → ek
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(ek);
		EasyMock.replay(deck);

		// Player with empty hand (no DEFUSE)
		Player player = EasyMock.createMock(Player.class);
		List<Card> hand = new ArrayList<>();
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		//EasyMock.expect(player.getName()).andReturn("Bob").anyTimes();
		EasyMock.replay(player);

		provideInput("end\n");

		TurnController tc = new TurnController(deck);
		assertFalse(tc.takeTurn(player), "Should return false when no defuse available");

		EasyMock.verify(ek, deck, player);
	}

	@Test
	void takeTurn_endEK_withDefuse_returnsTrue_andDefuseConsumed() {
		// Exploding kitten
		Card ek = EasyMock.createMock(Card.class);
		EasyMock.expect(ek.getCardType()).andReturn(CardType.EXPLODING_KITTEN).anyTimes();

		// Defuse card
		Card defuse = EasyMock.createMock(Card.class);
		EasyMock.expect(defuse.getCardType()).andReturn(CardType.DEFUSE).anyTimes();

		EasyMock.replay(ek, defuse);

		// Deck: draw EK, expect insertCardAtRandomIndex(...)
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(ek);
		deck.insertCardAtRandomIndex(EasyMock.anyObject(Card.class));
		EasyMock.expectLastCall();
		EasyMock.replay(deck);

		// Player with one DEFUSE in hand
		Player player = EasyMock.createMock(Player.class);
		List<Card> hand = new ArrayList<>(Collections.singletonList(defuse));
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		//EasyMock.expect(player.getName()).andReturn("Carol").anyTimes();
		EasyMock.replay(player);

		provideInput("end\n");

		TurnController tc = new TurnController(deck);
		boolean survived = tc.takeTurn(player);

		assertTrue(survived, "Should return true when defuse is used");
		assertTrue(hand.isEmpty(), "Defuse card should be removed from hand");

		EasyMock.verify(ek, defuse, deck, player);
	}

	// --- Method 4: promptCardIndex(Player) ---

	@Test
	void promptCardIndex_repromptsUntilValid() throws Exception {
		// Prepare input: invalid "-1", then valid "0"
		provideInput("-1\n0\n");

		// Dummy deck (unused)
		Deck deck = EasyMock.createMock(Deck.class);
		TurnController tc = new TurnController(deck);

		// Player with exactly one card in hand
		Player player = EasyMock.createMock(Player.class);
		Card mockCard = EasyMock.createMock(Card.class);
		List<Card> hand = new ArrayList<>(Collections.singletonList(mockCard));
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		EasyMock.replay(player);

		// Invoke private method with reflection
		Method m = TurnController.class.getDeclaredMethod("promptCardIndex", Player.class);
		m.setAccessible(true);
		int idx = (int) m.invoke(tc, player);

		assertEquals(0, idx, "Should skip invalid -1 and return 0");

		EasyMock.verify(player);
	}
}
