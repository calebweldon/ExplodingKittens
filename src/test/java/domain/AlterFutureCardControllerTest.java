package domain;

import ui.GameView;
import ui.AlterFutureCardView;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlterFutureCardControllerTest {
	@Test
	public void handleAlterFutureCardAction_threeCards() {
		CardType cardType1 = CardType.ATTACK;
		CardType cardType2 = CardType.DEFUSE;
		CardType cardType3 = CardType.EXPLODING_KITTEN;
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.getSize()).andReturn(3);
		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(cardType1);
		EasyMock.expect(deck.getCardAtIndex(1)).andReturn(cardType2);
		EasyMock.expect(deck.getCardAtIndex(2)).andReturn(cardType3);
		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		CardType[] topCards = {cardType1, cardType2, cardType3};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.replay(deck, view);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = controller.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(deck, view);
	}

	@Test
	public void handleAlterFutureCardAction_twoCards() {
		CardType cardType1 = CardType.ATTACK;
		CardType cardType2 = CardType.EXPLODING_KITTEN;
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.getSize()).andReturn(2);
		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(cardType1);
		EasyMock.expect(deck.getCardAtIndex(1)).andReturn(cardType2);
		AlterFutureCardView view = EasyMock.createMock(AlterFutureCardView.class);
		CardType[] topCards = {cardType1, cardType2};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.replay(deck, view);

		AlterFutureCardController controller = new AlterFutureCardController(view, deck);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = controller.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(deck, view);
	}
}
