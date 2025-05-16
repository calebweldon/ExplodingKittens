package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class FlipCardControllerTest {
	@Test
	public void handleFlipCardAction() {
		Deck deck = EasyMock.createMock(Deck.class);
		deck.flipDeck();
		EasyMock.replay(deck);

		FlipCardController flipCardController = new FlipCardController(deck);
		flipCardController.handleCardAction();

		EasyMock.verify(deck);
	}
}
