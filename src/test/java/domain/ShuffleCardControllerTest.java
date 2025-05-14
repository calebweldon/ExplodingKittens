package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class ShuffleCardControllerTest {
	@Test
	public void handleShuffleCardAction() {
		Deck deck = EasyMock.createMock(Deck.class);
		ShuffleCardController shuffleCardController = new ShuffleCardController(deck);
		deck.shuffleDeck();
		EasyMock.replay(deck);
		shuffleCardController.handleCardAction();
		EasyMock.verify(deck);
	}
}
