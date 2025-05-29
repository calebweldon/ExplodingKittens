package domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.easymock.EasyMock;
import ui.TurnView;

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
		InputStream testIn = new ByteArrayInputStream(
			data.getBytes(StandardCharsets.UTF_8));
		System.setIn(testIn);
	}

	// --- Constructor ---
	/*

	@Test
	void constructor_nullDeck_throwsException() {
		TurnView turnView = EasyMock.createMock(TurnView.class);
		assertThrows(IllegalArgumentException.class, () -> new TurnController(null, turnView));
	}

	@Test
	void constructor_nullTurnView_throwsException() {
		Deck deck = EasyMock.createMock(Deck.class);
		assertThrows(IllegalArgumentException.class, () -> new TurnController(deck, null));
	}

	@Test
	void constructor_validDeckAndTurnView_success() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		TurnController tc = new TurnController(deck, turnView);
		assertNotNull(tc);
	}
	*/

	// --- Core Game Logic Tests ---

	@Test
	void takeTurn_nullPlayer_throwsNPE() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		EasyMock.replay(turnView);
		TurnController tc = new TurnController(deck, turnView);
		assertThrows(NullPointerException.class, () -> tc.takeTurn(null));
	}

	@Test
	void takeTurn_drawNonEK_cardAddedAndContinues() {
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(CardType.DEFUSE);
		EasyMock.replay(deck);

		Map<CardType, Integer> hand = new HashMap<>();
		Player player = EasyMock.createMock(Player.class);
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		player.addCard(CardType.DEFUSE);
		EasyMock.expectLastCall();
		EasyMock.replay(player);

		TurnView turnView = EasyMock.createMock(TurnView.class);
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		turnView.showCardDrawn(CardType.DEFUSE);
		EasyMock.expectLastCall();
		EasyMock.replay(turnView);

		TurnController tc = new TurnController(deck, turnView);
		TurnResult result = tc.takeTurn(player);

		assertEquals(TurnResult.CONTINUE, result);
		EasyMock.verify(deck, player, turnView);
	}

	@Test
	void takeTurn_playSkipCard_returnsSkip() {
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.replay(deck);

		Map<CardType, Integer> hand = new HashMap<>();
		hand.put(CardType.SKIP, 1);
		Player player = EasyMock.createMock(Player.class);
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		player.playCard(CardType.SKIP);
		EasyMock.expectLastCall();
		EasyMock.replay(player);

		TurnView turnView = EasyMock.createMock(TurnView.class);
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		turnView.showPlayerHand(hand);
		EasyMock.expectLastCall();
		EasyMock.expect(turnView.promptCardChoice(player)).andReturn(CardType.SKIP);
		turnView.showCardPlayed(CardType.SKIP);
		EasyMock.expectLastCall();
		EasyMock.replay(turnView);

		TurnController tc = new TurnController(deck, turnView);
		TurnResult result = tc.takeTurn(player);

		assertEquals(TurnResult.SKIP, result);
		EasyMock.verify(deck, player, turnView);
	}

	@Test
	void takeTurn_playAttackCard_returnsAttack() {
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.replay(deck);

		Map<CardType, Integer> hand = new HashMap<>();
		hand.put(CardType.ATTACK, 1);
		Player player = EasyMock.createMock(Player.class);
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		player.playCard(CardType.ATTACK);
		EasyMock.expectLastCall();
		EasyMock.replay(player);

		TurnView turnView = EasyMock.createMock(TurnView.class);
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		turnView.showPlayerHand(hand);
		EasyMock.expectLastCall();
		EasyMock.expect(turnView.promptCardChoice(player)).andReturn(CardType.ATTACK);
		turnView.showCardPlayed(CardType.ATTACK);
		EasyMock.expectLastCall();
		EasyMock.replay(turnView);

		TurnController tc = new TurnController(deck, turnView);
		TurnResult result = tc.takeTurn(player);

		assertEquals(TurnResult.ATTACK, result);
		EasyMock.verify(deck, player, turnView);
	}

	@Test
	void takeTurn_drawsEK_noDefuse_playerEliminated() {
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(CardType.EXPLODING_KITTEN);
		EasyMock.replay(deck);

		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> emptyHand = Collections.emptyMap();
		EasyMock.expect(player.viewHand()).andReturn(emptyHand).anyTimes();
		EasyMock.replay(player);

		TurnView turnView = EasyMock.createMock(TurnView.class);
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		turnView.showCardDrawn(CardType.EXPLODING_KITTEN);
		EasyMock.expectLastCall();
		turnView.showNoDefuseFound();
		EasyMock.expectLastCall();
		EasyMock.replay(turnView);

		TurnController tc = new TurnController(deck, turnView);
		TurnResult result = tc.takeTurn(player);

		assertEquals(TurnResult.ELIMINATED, result);
		EasyMock.verify(deck, player, turnView);
	}

	@Test
	void takeTurn_drawsEK_withDefuse_playerSurvives() {
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(CardType.EXPLODING_KITTEN);
		deck.insertCardAtRandomIndex(CardType.EXPLODING_KITTEN);
		EasyMock.expectLastCall();
		EasyMock.replay(deck);

		Map<CardType, Integer> hand = new HashMap<>();
		hand.put(CardType.DEFUSE, 1);
		Player player = EasyMock.createMock(Player.class);
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		player.removeCard(CardType.DEFUSE);
		EasyMock.expectLastCall();
		EasyMock.replay(player);

		TurnView turnView = EasyMock.createMock(TurnView.class);
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		turnView.showCardDrawn(CardType.EXPLODING_KITTEN);
		EasyMock.expectLastCall();
		turnView.showDefuseUsed();
		EasyMock.expectLastCall();
		EasyMock.replay(turnView);

		TurnController tc = new TurnController(deck, turnView);
		TurnResult result = tc.takeTurn(player);

		assertEquals(TurnResult.CONTINUE, result);
		EasyMock.verify(deck, player, turnView);
	}

	// --- Edge Case Tests ---

	@Test
	void takeTurn_playWithEmptyHand_showsMessageAndContinues() {
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(CardType.DEFUSE);
		EasyMock.replay(deck);

		Map<CardType, Integer> emptyHand = Collections.emptyMap();
		Player player = EasyMock.createMock(Player.class);
		EasyMock.expect(player.viewHand()).andReturn(emptyHand).anyTimes();
		player.addCard(CardType.DEFUSE);
		EasyMock.expectLastCall();
		EasyMock.replay(player);

		TurnView turnView = EasyMock.createMock(TurnView.class);
		EasyMock.expect(turnView.promptForInput()).andReturn("play").andReturn("draw");
		turnView.showNoCardsMessage();
		EasyMock.expectLastCall();
		turnView.showCardDrawn(CardType.DEFUSE);
		EasyMock.expectLastCall();
		EasyMock.replay(turnView);

		TurnController tc = new TurnController(deck, turnView);
		TurnResult result = tc.takeTurn(player);

		assertEquals(TurnResult.CONTINUE, result);
		EasyMock.verify(deck, player, turnView);
	}

	@Test
	void takeTurn_errorHandling_showsErrorMessages() {
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.drawCard()).andReturn(CardType.DEFUSE);
		EasyMock.replay(deck);

		Map<CardType, Integer> hand = new HashMap<>();
		hand.put(CardType.DEFUSE, 1);
		Player player = EasyMock.createMock(Player.class);
		EasyMock.expect(player.viewHand()).andReturn(hand).anyTimes();
		player.playCard(CardType.DEFUSE);
		EasyMock.expectLastCall().andThrow(new IllegalArgumentException("Test error"));
		player.addCard(CardType.DEFUSE);
		EasyMock.expectLastCall();
		EasyMock.replay(player);

		TurnView turnView = EasyMock.createMock(TurnView.class);
		EasyMock.expect(turnView.promptForInput()).andReturn("play").andReturn("draw");
		turnView.showPlayerHand(hand);
		EasyMock.expectLastCall();
		EasyMock.expect(turnView.promptCardChoice(player)).andReturn(CardType.DEFUSE);
		turnView.showInvalidCardPlay("Test error");
		EasyMock.expectLastCall();
		turnView.showCardDrawn(CardType.DEFUSE);
		EasyMock.expectLastCall();
		EasyMock.replay(turnView);

		TurnController tc = new TurnController(deck, turnView);
		TurnResult result = tc.takeTurn(player);

		assertEquals(TurnResult.CONTINUE, result);
		EasyMock.verify(deck, player, turnView);
	}
}
