package domain;

public class SkipCardController implements CardController {

	public SkipCardController() { }

	public TurnResult handleCardAction() {
		return TurnResult.SKIP;
	}
}
