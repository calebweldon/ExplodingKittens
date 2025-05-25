package domain;

public class SwapHandCardController implements CardController {
	private final SwapHandCardView view;

	public SwapHandCardController() { }

	public TurnResult handleCardAction() {
		return TurnResult.CONTINUE;
	}
}
