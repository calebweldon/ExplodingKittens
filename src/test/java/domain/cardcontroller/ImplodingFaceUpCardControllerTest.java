
package domain.cardcontroller;

import domain.TurnResult;
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

	@Test
	public void getInfo_implodingFaceUpCardController() {
		ImplodingFaceUpCardView cv = EasyMock.createMock(ImplodingFaceUpCardView.class);
		cv.getInfo();
		EasyMock.replay(cv);

		ImplodingFaceUpCardController implodingFaceUpCardController = new ImplodingFaceUpCardController(cv);
		implodingFaceUpCardController.getInfo();

		EasyMock.verify(cv);
	}
}
