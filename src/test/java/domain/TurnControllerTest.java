package domain;

import domain.cardcontroller.*;
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

		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
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

		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
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
		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.isEmpty()).andReturn(false);
		EasyMock.expect(turnView.promptCardChoice(player)).andReturn(CardType.IMPLODING_FACEDOWN);
		player.playCard(CardType.IMPLODING_FACEDOWN);
		turnView.showInvalidCardPlay(CardType.IMPLODING_FACEDOWN);

		// "draw" phase
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

		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
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
		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.isEmpty()).andReturn(true);
		turnView.showNoCardsMessage();

		// "draw" phase
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.ATTACK);
		turnView.showCardDrawn(CardType.ATTACK);
		player.addCard(CardType.ATTACK);

		EasyMock.replay(deck, turnView, player, controller, hand);
		tc.takeTurn(player);
		EasyMock.verify(deck, turnView, player, controller, hand);
	}

	@Test
	void playerInvalidInput() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		SkipCardController controller = EasyMock.createMock(SkipCardController.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();

		TurnController tc = new TurnController(deck, turnView, cardControllers);

		// User inputs an invalid command
		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
		EasyMock.expect(turnView.promptForInput()).andReturn("sdddjdkfjfkj");
		turnView.showUnexpectedAction();
		
		// "draw" phase
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
		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
		EasyMock.expect(turnView.promptForInput()).andReturn("info");
		turnView.getCardInfo();

		// "draw" phase
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.ATTACK);
		turnView.showCardDrawn(CardType.ATTACK);
		player.addCard(CardType.ATTACK);

		EasyMock.replay(deck, turnView, player, controller, hand);
		tc.takeTurn(player);
		EasyMock.verify(deck, turnView, player, controller, hand);
	}

	@Test
	void registerTurnObserver() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		SwapHandCardController controller = EasyMock.createMock(SwapHandCardController.class);
		cardControllers.put(CardType.SWAP, controller);

		TurnController turnController = new TurnController(deck, turnView, cardControllers);
		assertEquals(1, turnController.getTurnObserverSize());
	}

	@Test
	void unregisterTurnObserver() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		SwapHandCardController controller = EasyMock.createMock(SwapHandCardController.class);
		cardControllers.put(CardType.SWAP, controller);

		TurnController turnController = new TurnController(deck, turnView, cardControllers);
		assertEquals(1, turnController.getTurnObserverSize());
		turnController.unregisterObserver(controller);
		assertEquals(0, turnController.getTurnObserverSize());
	}

	@Test
	void notifyTurnObservers() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		Player player = EasyMock.createMock(Player.class);
		SwapHandCardController controller = EasyMock.createMock(SwapHandCardController.class);
		cardControllers.put(CardType.SWAP, controller);

		controller.updatePlayer(player);

		// "draw" phase
		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.ATTACK);
		turnView.showCardDrawn(CardType.ATTACK);
		player.addCard(CardType.ATTACK);

		EasyMock.replay(deck, turnView, player, controller);
		TurnController turnController = new TurnController(deck, turnView, cardControllers);
		turnController.takeTurn(player);

		EasyMock.verify(deck, turnView, player, controller);
	}

	@Test
	void notifyLastPlayedObservers() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		Player player = EasyMock.createMock(Player.class);
		RecycleCardController controller = EasyMock.createMock(RecycleCardController.class);
		cardControllers.put(CardType.RECYCLE, controller);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		controller.updatePlayer(player);

		// "play" phase
		turnView.displayHand(player);
		EasyMock.expect(deck.getImplodingIndex()).andReturn(-1);
		turnView.showImplodingIndex(-1);
		EasyMock.expect(turnView.promptForInput()).andReturn("play");
		EasyMock.expect(player.viewHand()).andReturn(hand);
		EasyMock.expect(hand.isEmpty()).andReturn(false);
		EasyMock.expect(turnView.promptCardChoice(player)).andReturn(CardType.RECYCLE);
		controller.updateLastPlayed(CardType.RECYCLE);
		player.playCard(CardType.RECYCLE);
		EasyMock.expect(controller.handleCardAction()).andReturn(TurnResult.CONTINUE);
		controller.updateLastPlayed(CardType.RECYCLE);

		// "draw" phase
		EasyMock.expect(turnView.promptForInput()).andReturn("draw");
		EasyMock.expect(deck.drawCard()).andReturn(CardType.ATTACK);
		turnView.showCardDrawn(CardType.ATTACK);
		player.addCard(CardType.ATTACK);

		EasyMock.replay(deck, turnView, player, controller, hand);
		TurnController tc = new TurnController(deck, turnView, cardControllers);
		tc.registerObserver((LastPlayedObserver)controller);
		tc.takeTurn(player);
		EasyMock.verify(deck, turnView, player, controller, hand);

	}

	@Test
	void unregisterLastPlayedObservers() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		RecycleCardController controller = EasyMock.createMock(RecycleCardController.class);
		cardControllers.put(CardType.RECYCLE, controller);

		TurnController turnController = new TurnController(deck, turnView, cardControllers);
		assertEquals(1, turnController.getLastPlayedObserverSize());
		turnController.unregisterObserver(controller);
		assertEquals(0, turnController.getLastPlayedObserverSize());
	}

	@Test
	void constructor_withNullDeck_throwsException() {
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new TurnController(null, turnView, cardControllers);
		});
		assertEquals("Deck cannot be null", exception.getMessage());
	}

	@Test
	void constructor_withNullTurnView_throwsException() {
		Deck deck = EasyMock.createMock(Deck.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new TurnController(deck, null, cardControllers);
		});
		assertEquals("TurnView cannot be null", exception.getMessage());
	}

	@Test
	void constructor_withNullCardControllers_throwsException() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new TurnController(deck, turnView, null);
		});
		assertEquals("CardControllers cannot be null", exception.getMessage());
	}

	@Test
	void getCardCount_runs() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnView turnView = EasyMock.createMock(TurnView.class);
		Map<CardType, CardController> cardControllers = new HashMap<>();
		TurnController turnController = new TurnController(deck, turnView, cardControllers);

		EasyMock.expect(deck.getCardCount(CardType.ATTACK)).andReturn(10);
		EasyMock.replay(deck, turnView);

		int count = turnController.getCardCount(CardType.ATTACK);
		assertTrue(count == 10);
	}
}
