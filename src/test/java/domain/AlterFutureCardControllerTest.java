package domain;

import domain.cardcontroller.AlterFutureCardController;
import ui.AlterFutureCardView;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

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
}
