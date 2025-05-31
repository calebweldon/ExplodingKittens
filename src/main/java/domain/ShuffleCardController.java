package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ImplodingFaceUpCardView;
import ui.ShuffleCardView;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
public class ShuffleCardController implements CardController, ActionCardController {
	private final ShuffleCardView view;
	private final Deck deck;

	public ShuffleCardController(Deck deck, ShuffleCardView view) {
		this.deck = deck;
		this.view = view;
	}

	public TurnResult handleCardAction() {
		deck.shuffleDeck();
		view.actionMessage();
		return TurnResult.CONTINUE;
	}
}