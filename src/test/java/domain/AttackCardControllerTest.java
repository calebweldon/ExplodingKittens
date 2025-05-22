package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttackCardControllerTest {
	@Test
	public void handleAttackCardAction() {
		AttackCardController attackCardController = new AttackCardController();

		TurnResult expected = TurnResult.ATTACK;
		TurnResult actual = attackCardController.handleCardAction();

		assertEquals(expected, actual);
	}
}
