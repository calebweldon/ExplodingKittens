package domain;

import domain.cardcontroller.SkipCardController;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.SkipCardView;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SkipCardControllerTest {
	@Test
	public void handleSkipCardAction() {
		SkipCardView view = EasyMock.createMock(SkipCardView.class);
		view.actionMessage();
		EasyMock.replay(view);

		SkipCardController skipCardController = new SkipCardController(view);

		TurnResult expected = TurnResult.SKIP;
		TurnResult actual = skipCardController.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(view);
	}
}
