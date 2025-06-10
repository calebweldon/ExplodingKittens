package domain.cardcontroller;

import domain.TurnResult;
import ui.SkipCardView;

public class SkipCardController implements CardController, ActionCardController {
	private final SkipCardView view;

	public SkipCardController(SkipCardView view) {
		this.view = view;
	}

	public TurnResult handleCardAction() {
		view.actionMessage();
		return TurnResult.SKIP;
	}

	public void getInfo() {
		this.view.getInfo();
	}
}
