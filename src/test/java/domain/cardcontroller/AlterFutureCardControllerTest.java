package domain.cardcontroller;

import domain.CardType;
import domain.Deck;
import domain.Player;
import domain.TurnResult;
import ui.AlterFutureCardView;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.FavorCardView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlterFutureCardControllerTest {
	@Test
	public void handleAlterFutureCardAction_threeCardsNoReorder() {
		CardType cardType1 = CardType.ATTACK;
		CardType cardType2 = CardType.DEFUSE;
		CardType cardType3 = CardType.EXPLODING_KITTEN;
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.getSize()).andReturn(3);
		EasyMock.expect(deck.drawCard()).andReturn(cardType1);
		EasyMock.expect(deck.drawCard()).andReturn(cardType2);
		EasyMock.expect(deck.drawCard()).andReturn(cardType3);
		deck.insertCardAtIndex(cardType1, 0);
		deck.insertCardAtIndex(cardType2, 1);
		deck.insertCardAtIndex(cardType3, 2);
		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		CardType[] topCards = {cardType1, cardType2, cardType3};
		CardType[] reorderedCards = {cardType1, cardType2, cardType3};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.expect(view.promptForNewOrder(topCards)).andReturn(reorderedCards);
		EasyMock.replay(deck, view);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = controller.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(deck, view);
	}

	@Test
	public void handleAlterFutureCardAction_threeCardsReorder() {
		CardType cardType1 = CardType.ATTACK;
		CardType cardType2 = CardType.DEFUSE;
		CardType cardType3 = CardType.EXPLODING_KITTEN;
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.getSize()).andReturn(3);
		EasyMock.expect(deck.drawCard()).andReturn(cardType1);
		EasyMock.expect(deck.drawCard()).andReturn(cardType2);
		EasyMock.expect(deck.drawCard()).andReturn(cardType3);
		deck.insertCardAtIndex(cardType1, 0);
		deck.insertCardAtIndex(cardType3, 1);
		deck.insertCardAtIndex(cardType2, 2);
		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		CardType[] topCards = {cardType1, cardType2, cardType3};
		CardType[] reorderedCards = {cardType1, cardType3, cardType2};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.expect(view.promptForNewOrder(topCards)).andReturn(reorderedCards);
		EasyMock.replay(deck, view);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = controller.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(deck, view);
	}

	@Test
	public void handleAlterFutureCardAction_twoCardsNoReorder() {
		CardType cardType1 = CardType.ATTACK;
		CardType cardType2 = CardType.EXPLODING_KITTEN;
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.getSize()).andReturn(2);
		EasyMock.expect(deck.drawCard()).andReturn(cardType1);
		EasyMock.expect(deck.drawCard()).andReturn(cardType2);
		deck.insertCardAtIndex(cardType1, 0);
		deck.insertCardAtIndex(cardType2, 1);
		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		CardType[] topCards = {cardType1, cardType2};
		CardType[] reorderedCards = {cardType1, cardType2};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.expect(view.promptForNewOrder(topCards)).andReturn(reorderedCards);
		EasyMock.replay(deck, view);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = controller.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(deck, view);
	}

	@Test
	public void handleAlterFutureCardAction_twoCardsReorder() {
		CardType cardType1 = CardType.ATTACK;
		CardType cardType2 = CardType.EXPLODING_KITTEN;
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.getSize()).andReturn(2);
		EasyMock.expect(deck.drawCard()).andReturn(cardType1);
		EasyMock.expect(deck.drawCard()).andReturn(cardType2);
		deck.insertCardAtIndex(cardType2, 0);
		deck.insertCardAtIndex(cardType1, 1);
		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		CardType[] topCards = {cardType1, cardType2};
		CardType[] reorderedCards = {cardType2, cardType1};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.expect(view.promptForNewOrder(topCards)).andReturn(reorderedCards);
		EasyMock.replay(deck, view);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = controller.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(deck, view);
	}

	@Test
	public void getInfo_alterFutureCardController() {
		Deck deck = EasyMock.createMock(Deck.class);
		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		view.getInfo();
		EasyMock.replay(view, deck);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);
		controller.getInfo();

		EasyMock.verify(view, deck);
	}

	@Test
	public void IntegrationTest_alterFuture_threeCardsPartialReorder() {
		Deck deck = new Deck();

		CardType firstCard = deck.getCardAtIndex(0);
		CardType secondCard = deck.getCardAtIndex(1);
		CardType thirdCard = deck.getCardAtIndex(2);
		CardType[] topCards = {firstCard, secondCard, thirdCard};

		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		CardType[] reorderedCards = {firstCard, thirdCard, secondCard};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.expect(view.promptForNewOrder(topCards)).andReturn(reorderedCards);

		EasyMock.replay(view);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);

		TurnResult expectedTurnResult = TurnResult.CONTINUE;
		TurnResult actualTurnResult = controller.handleCardAction();

		assertEquals(expectedTurnResult, actualTurnResult);

		CardType expectedFirstCard = firstCard;
		CardType expectedSecondCard = thirdCard;
		CardType expectedThirdCard = secondCard;

		CardType actualFirstCard = deck.getCardAtIndex(0);
		CardType actualSecondCard = deck.getCardAtIndex(1);
		CardType actualThirdCard = deck.getCardAtIndex(2);

		assertEquals(expectedFirstCard, actualFirstCard);
		assertEquals(expectedSecondCard, actualSecondCard);
		assertEquals(expectedThirdCard, actualThirdCard);

		EasyMock.verify(view);

	}
}
