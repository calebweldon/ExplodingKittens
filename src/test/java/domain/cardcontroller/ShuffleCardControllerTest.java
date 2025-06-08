package domain.cardcontroller;

import domain.Deck;
import domain.TurnResult;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.ShuffleCardView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShuffleCardControllerTest {
	@Test
	public void handleShuffleCardAction() {
		Deck deck = EasyMock.createMock(Deck.class);
		ShuffleCardView cv = EasyMock.createMock(ShuffleCardView.class);
		TurnResult expected = TurnResult.CONTINUE;
		ShuffleCardController shuffleCardController = new ShuffleCardController(deck, cv);

		deck.shuffleDeck();
		cv.actionMessage();
		EasyMock.replay(deck, cv);

		TurnResult result = shuffleCardController.handleCardAction();
		assertEquals(expected, result);
		EasyMock.verify(deck, cv);
	}
}
