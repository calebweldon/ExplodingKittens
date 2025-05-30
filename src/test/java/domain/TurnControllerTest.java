package domain;

import org.junit.jupiter.api.*;
import org.easymock.EasyMock;
import ui.TurnView;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;

class TurnControllerTest {

	@Test
	void takeTurn_nullPlayer() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		AttackCardController controller = EasyMock.createMock(AttackCardController.class);
		cardControllers.put(CardType.ATTACK, controller);

		TurnController tc = new TurnController(deck, turnView, cardControllers);
		try {
			tc.takeTurn(null);
		}
		catch (IllegalArgumentException e) {
			assertEquals("Player cannot be null", e.getMessage());
		}
	}

	@Test
	void playerDrawsCard_noHandleDraw() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Player player = EasyMock.createMock(Player.class);

		AttackCardController controller = EasyMock.createMock(AttackCardController.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.ATTACK, controller);

		TurnController tc = new TurnController(deck, turnView, cardControllers);

		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.ATTACK);
		turnView.showCardDrawn(CardType.ATTACK);
		player.addCard(CardType.ATTACK);

		EasyMock.replay(deck, turnView, player, controller);
		tc.takeTurn(player);

		EasyMock.verify(deck, turnView, player, controller);
	}

	@Test
	void playerDrawsCard_withHandleDraw() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Player player = EasyMock.createMock(Player.class);

		ImplodingFaceDownCardController controller = EasyMock.createMock(ImplodingFaceDownCardController.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.IMPLODING_FACEDOWN, controller);

		TurnController tc = new TurnController(deck, turnView, cardControllers);

		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.IMPLODING_FACEDOWN);
		turnView.showCardDrawn(CardType.IMPLODING_FACEDOWN);
		EasyMock.expect(controller.handleCardDraw()).andReturn(TurnResult.CONTINUE);

		EasyMock.replay(deck, turnView, player, controller);
		tc.takeTurn(player);

		EasyMock.verify(deck, turnView, player, controller);
	}
/*


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
		EasyMock.expect(deck.getSize()).andReturn(10);
		deck.insertCardAtIndex(CardType.EXPLODING_KITTEN, 5);
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
		EasyMock.expect(turnView.promptExplodingKittenIndex(10)).andReturn(5);
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
 */
}
