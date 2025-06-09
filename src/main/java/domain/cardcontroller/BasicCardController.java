package domain.cardcontroller;

import domain.TurnResult;

public class BasicCardController implements CardController, ActionCardController,
        TurnObserver, ActivePlayersExcludingCurrentObserver {

	public TurnResult handleCardAction() {
		return null;
	}

	public void getInfo() {
	}
}
