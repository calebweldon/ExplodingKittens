package domain;

import ui.GameView;
import ui.SeeFutureCardView;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeeFutureCardControllerTest {
	@Test
	public void handleSeeFutureCardAction() {
		CardType cardType1 = CardType.ATTACK;
		CardType cardType2 = CardType.DEFUSE;
		CardType cardType3 = CardType.EXPLODING_KITTEN;
		Deck deck = EasyMock.createMock(Deck.class);
		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(cardType1);
		EasyMock.expect(deck.getCardAtIndex(1)).andReturn(cardType2);
		EasyMock.expect(deck.getCardAtIndex(2)).andReturn(cardType3);
		SeeFutureCardView view = EasyMock.createMock(SeeFutureCardView.class);
		CardType[] topCards = {cardType1, cardType2, cardType3};
		view.actionMessage();
		view.displayTopCards(topCards);
		EasyMock.replay(deck, view);

		SeeFutureCardController controller = new SeeFutureCardController(view, deck);

		TurnResult expected = TurnResult.CONTINUE;
		TurnResult actual = controller.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(deck, view);
	}
}
