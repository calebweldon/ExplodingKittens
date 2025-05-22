package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SkipCardControllerTest {
	@Test
	public void handleSkipCardAction() {
		SkipCardController skipCardController = new SkipCardController();

		TurnResult expected = TurnResult.SKIP;
		TurnResult actual = skipCardController.handleCardAction();

		assertEquals(expected, actual);
	}
}
