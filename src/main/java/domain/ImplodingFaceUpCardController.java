package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ImplodingFaceUpCardView;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
public class ImplodingFaceUpCardController implements DrawCardController {
	private final ImplodingFaceUpCardView view;

	ImplodingFaceUpCardController(ImplodingFaceUpCardView view) {
		this.view = view;
	}

	public TurnResult handleCardDraw() {
		view.actionMessage();
		return TurnResult.ELIMINATED;
	}
}
