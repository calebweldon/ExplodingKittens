package domain.cardcontroller;

import domain.Deck;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlipCardControllerTest {
	@Test
	public void handleFlipCardAction() {
		Deck deck = EasyMock.createMock(Deck.class);
		TurnResult expected = TurnResult.CONTINUE;
		deck.flipDeck();
		EasyMock.replay(deck);

		FlipCardController flipCardController = new FlipCardController(deck);
		TurnResult result = flipCardController.handleCardAction();

		assertEquals(expected, result);
		EasyMock.verify(deck);
	}
}
