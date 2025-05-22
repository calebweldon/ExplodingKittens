package domain;

import ui.GameView;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class SeeFutureCardControllerTest {
	@Test
	public void handleSeeFutureCardAction() {
		Deck deck = EasyMock.createMock(Deck.class);
		CardType cardType1 = CardType.ATTACK;
		CardType cardType3 = CardType.EXPLODING_KITTEN;
		CardType cardType2 = CardType.DEFUSE;
		EasyMock.expect(deck.getCardAtIndex(0)).andReturn(cardType1);
		EasyMock.expect(deck.getCardAtIndex(1)).andReturn(cardType2);
		EasyMock.expect(deck.getCardAtIndex(2)).andReturn(cardType3);
		GameView gameView = EasyMock.createMock(GameView.class);
		CardType[] topCards = {cardType1, cardType2, cardType3};
		gameView.showTopCards(topCards);
		EasyMock.replay(deck, gameView);

		SeeFutureCardController controller = new SeeFutureCardController(gameView, deck);
		controller.handleCardAction();

		EasyMock.verify(deck);
	}
}
