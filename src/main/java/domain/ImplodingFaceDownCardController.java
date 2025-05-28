package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ImplodingFaceDownCardView;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
public class ImplodingFaceDownCardController implements CardController, DrawCardController {
	private final ImplodingFaceDownCardView view;
	private final Deck deck;

	ImplodingFaceDownCardController(ImplodingFaceDownCardView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	public TurnResult handleCardDraw() {
		view.actionMessage();
		int index = view.getIndex(this.deck.getSize());
		deck.insertCardAtIndex(CardType.IMPLODING_FACEUP, index);
		return TurnResult.CONTINUE;
	}
}
