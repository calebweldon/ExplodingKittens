package domain;

public class AttackCardController implements CardController {
	public AttackCardController() { }

	public TurnResult handleCardAction() {
		return TurnResult.ATTACK;
	}
}
