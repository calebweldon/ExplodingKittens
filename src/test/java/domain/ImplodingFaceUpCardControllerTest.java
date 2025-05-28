
package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.ImplodingFaceUpCardView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImplodingFaceUpCardControllerTest {
	@Test
	public void handleImplodingFaceUpCardDraw() {
		ImplodingFaceUpCardView cv = EasyMock.createMock(ImplodingFaceUpCardView.class);
		cv.actionMessage();
		EasyMock.replay(cv);

		ImplodingFaceUpCardController implodingFaceUpCardController = new ImplodingFaceUpCardController(cv);
		TurnResult result = implodingFaceUpCardController.handleCardDraw();

		assertEquals(TurnResult.ELIMINATED, result);
		EasyMock.verify(cv);
	}
}
