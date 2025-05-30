package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.AttackCardView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackCardControllerTest {
	@Test
	public void handleAttackCardAction() {
		AttackCardView view = EasyMock.createMock(AttackCardView.class);
		view.actionMessage();
		EasyMock.replay(view);

		AttackCardController attackCardController = new AttackCardController(view);

		TurnResult expected = TurnResult.ATTACK;
		TurnResult actual = attackCardController.handleCardAction();

		assertEquals(expected, actual);
		EasyMock.verify(view);
	}
}
