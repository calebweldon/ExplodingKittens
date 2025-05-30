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

	@Test
	void playerPlaysCard_withoutHandleAction() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		ImplodingFaceDownCardController controller = EasyMock.createMock(ImplodingFaceDownCardController.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.IMPLODING_FACEDOWN, controller);

		TurnController tc = new TurnController(deck, turnView, cardControllers);

		// "play" phase
		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.isEmpty()).andReturn(false);
		EasyMock.expect(turnView.promptCardChoice(player)).andReturn(CardType.IMPLODING_FACEDOWN);
		player.playCard(CardType.IMPLODING_FACEDOWN);
		turnView.showInvalidCardPlay("Unsupported card type: IMPLODING_FACEDOWN");

		// "draw" phase
		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.ATTACK);
		turnView.showCardDrawn(CardType.ATTACK);
		player.addCard(CardType.ATTACK);

		EasyMock.replay(deck, turnView, player, controller, hand);
		tc.takeTurn(player);
		EasyMock.verify(deck, turnView, player, controller, hand);
	}

	@Test
	void playerPlaysCard_withHandleAction() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		SkipCardController controller = EasyMock.createMock(SkipCardController.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.SKIP, controller);

		TurnController tc = new TurnController(deck, turnView, cardControllers);

		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.isEmpty()).andReturn(false);
		EasyMock.expect(turnView.promptCardChoice(player)).andReturn(CardType.SKIP);
		player.playCard(CardType.SKIP);
		EasyMock.expect(controller.handleCardAction()).andReturn(TurnResult.SKIP);

		EasyMock.replay(deck, turnView, player, controller, hand);
		tc.takeTurn(player);
		EasyMock.verify(deck, turnView, player, controller, hand);
	}

	@Test
	void playerPlaysCard_withNoCards() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		SkipCardController controller = EasyMock.createMock(SkipCardController.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.SKIP, controller);

		TurnController tc = new TurnController(deck, turnView, cardControllers);

		// "play" phase
		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.isEmpty()).andReturn(true);
		turnView.showNoCardsMessage();

		// "draw" phase
		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.ATTACK);
		turnView.showCardDrawn(CardType.ATTACK);
		player.addCard(CardType.ATTACK);

		EasyMock.replay(deck, turnView, player, controller, hand);
		tc.takeTurn(player);
		EasyMock.verify(deck, turnView, player, controller, hand);
	}

	@Test
	void playerGetsInfo() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		SkipCardController controller = EasyMock.createMock(SkipCardController.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		cardControllers.put(CardType.SKIP, controller);

		TurnController tc = new TurnController(deck, turnView, cardControllers);

		// "info" phase
		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("info");
		turnView.getInputForCardInfo(player);

		// "draw" phase
		player.showHand();
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.ATTACK);
		turnView.showCardDrawn(CardType.ATTACK);
		player.addCard(CardType.ATTACK);

		EasyMock.replay(deck, turnView, player, controller, hand);
		tc.takeTurn(player);
		EasyMock.verify(deck, turnView, player, controller, hand);
	}
}
