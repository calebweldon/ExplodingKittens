package domain.cardcontroller;

import domain.CardType;
import domain.Deck;
import domain.Player;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.ExplodingKittenView;

import java.util.Map;

import static org.easymock.EasyMock.expectLastCall;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExplodingKittenCardControllerTest {

	@Test
	public void handleCardDraw_PlayerIsNull_ThrowsIllegalStateException() {
		// BVA Test Case 1: Player is null
		ExplodingKittenView view = EasyMock.createMock(ExplodingKittenView.class);
		Deck deck = EasyMock.createMock(Deck.class);

		EasyMock.replay(view, deck);

		ExplodingKittenCardController controller = new ExplodingKittenCardController(view, deck);
		// Don't call updatePlayer, so currentPlayer remains null

		IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
			controller.handleCardDraw();
		});

		assertEquals("Player not set", exception.getMessage());
		EasyMock.verify(view, deck);
	}

	@Test
	public void handleCardDraw_PlayerHasDefuseCard_ReturnsContinue() {
		// BVA Test Case 2: Player has defuse card, deck size > 0
		ExplodingKittenView view = EasyMock.createMock(ExplodingKittenView.class);
		Deck deck = EasyMock.createMock(Deck.class);
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		ExplodingKittenCardController controller = new ExplodingKittenCardController(view, deck);
		controller.updatePlayer(player);

		// Set up expectations
		view.actionMessage();
		player.removeCard(CardType.DEFUSE);
		view.showDefuseUsed();
		EasyMock.expect(deck.getSize()).andReturn(5);
		EasyMock.expect(view.promptExplodingKittenIndex(5)).andReturn(2);
		deck.insertCardAtIndex(CardType.EXPLODING_KITTEN, 2);

		EasyMock.replay(view, deck, player, hand);

		TurnResult result = controller.handleCardDraw();

		assertEquals(TurnResult.CONTINUE, result);
		EasyMock.verify(view, deck, player, hand);
	}

	@Test
	public void handleCardDraw_PlayerHasNoDefuseCard_ReturnsEliminated() {
		// BVA Test Case 3: Player has no defuse card
		ExplodingKittenView view = EasyMock.createMock(ExplodingKittenView.class);
		Deck deck = EasyMock.createMock(Deck.class);
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		ExplodingKittenCardController controller = new ExplodingKittenCardController(view, deck);
		controller.updatePlayer(player);

		// Set up expectations
		view.actionMessage();
		player.removeCard(CardType.DEFUSE);
		expectLastCall().andThrow(new IllegalArgumentException("Not enough cards to remove.")); // Player has no defuse
		view.showNoDefuseFound();

		EasyMock.replay(view, deck, player, hand);

		TurnResult result = controller.handleCardDraw();

		assertEquals(TurnResult.ELIMINATED, result);
		EasyMock.verify(view, deck, player, hand);
	}

	@Test
	public void handleCardDraw_PlayerHasDefuseCardEmptyDeck_ReturnsContinue() {
		// BVA Test Case 4: Player has defuse card, empty deck (size = 0)
		ExplodingKittenView view = EasyMock.createMock(ExplodingKittenView.class);
		Deck deck = EasyMock.createMock(Deck.class);
		Player player = EasyMock.createMock(Player.class);
		Map<CardType, Integer> hand = EasyMock.createMock(Map.class);

		ExplodingKittenCardController controller = new ExplodingKittenCardController(view, deck);
		controller.updatePlayer(player);

		// Set up expectations
		view.actionMessage();
		player.removeCard(CardType.DEFUSE);
		view.showDefuseUsed();
		EasyMock.expect(deck.getSize()).andReturn(0); // Empty deck
		EasyMock.expect(view.promptExplodingKittenIndex(0)).andReturn(0);
		deck.insertCardAtIndex(CardType.EXPLODING_KITTEN, 0);

		EasyMock.replay(view, deck, player, hand);

		TurnResult result = controller.handleCardDraw();

		assertEquals(TurnResult.CONTINUE, result);
		EasyMock.verify(view, deck, player, hand);
	}

	@Test
	public void getInfo_explodingKittenCardController() {
		ExplodingKittenView view = EasyMock.createMock(ExplodingKittenView.class);
		view.getInfo();
		EasyMock.replay(view);

		ExplodingKittenCardController controller = new ExplodingKittenCardController(view, null);
		controller.getInfo();

		EasyMock.verify(view);
	}
} 
