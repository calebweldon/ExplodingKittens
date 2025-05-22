package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShuffleCardControllerTest {
	@Test
	public void handleShuffleCardAction() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnResult expected = TurnResult.CONTINUE;
		ShuffleCardController shuffleCardController = new ShuffleCardController(deck);
		deck.shuffleDeck();
		EasyMock.replay(deck);
		TurnResult result = shuffleCardController.handleCardAction();

		assertEquals(expected, result);
		EasyMock.verify(deck);
	}
}
