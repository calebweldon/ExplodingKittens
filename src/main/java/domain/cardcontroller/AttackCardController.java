package domain.cardcontroller;

import domain.TurnResult;
import ui.AttackCardView;

public class AttackCardController implements CardController, ActionCardController {
	private final AttackCardView view;

	public AttackCardController(AttackCardView view) {
		this.view = view;
	}

	public TurnResult handleCardAction() {
		view.actionMessage();
		return TurnResult.ATTACK;
	}

	public void getInfo() {
		this.view.getInfo();
	}
}
