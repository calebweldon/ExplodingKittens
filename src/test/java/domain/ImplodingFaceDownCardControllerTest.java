
package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.ImplodingFaceDownCardView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImplodingFaceDownCardControllerTest {
	@Test
	public void handleImplodingFaceDownCardDraw() {
		final int DECK_SIZE = 52;
		Deck deck = EasyMock.createMock(Deck.class);
		ImplodingFaceDownCardView cv = EasyMock.createMock(ImplodingFaceDownCardView.class);

		EasyMock.expect(deck.getSize()).andReturn(DECK_SIZE);
		EasyMock.expect(cv.getIndex(DECK_SIZE)).andReturn(0);
		cv.actionMessage();
		deck.insertCardAtIndex(CardType.IMPLODING_FACEUP,0);
		EasyMock.replay(deck, cv);

		ImplodingFaceDownCardController implodingFaceDownCardController = new ImplodingFaceDownCardController(cv, deck);
		TurnResult result = implodingFaceDownCardController.handleCardDraw();

		assertEquals(TurnResult.CONTINUE, result);
		EasyMock.verify(deck);
	}
}
