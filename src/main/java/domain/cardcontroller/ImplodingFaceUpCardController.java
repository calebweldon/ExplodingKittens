package domain.cardcontroller;

import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ImplodingFaceUpCardView;

public class ImplodingFaceUpCardController implements CardController, DrawCardController {
	private final ImplodingFaceUpCardView view;

	public ImplodingFaceUpCardController(ImplodingFaceUpCardView view) {
		this.view = view;
	}

	public TurnResult handleCardDraw() {
		view.actionMessage();
		return TurnResult.ELIMINATED;
	}

	public void getInfo() {
		this.view.getInfo();
	}
}
