package domain;

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
		EasyMock.replay(deck);

		SeeFutureCardController shuffleCardController = new SeeFutureCardController(deck);
		shuffleCardController.handleCardAction();

		EasyMock.verify(deck);
	}
}
