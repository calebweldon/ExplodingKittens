package domain.cardcontroller;

import domain.Deck;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.FlipCardView;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
public class FlipCardController implements CardController, ActionCardController {
	private final FlipCardView view;
	private final Deck deck;

	public FlipCardController(FlipCardView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	public TurnResult handleCardAction() {
		view.actionMessage();
		deck.flipDeck();
		return TurnResult.CONTINUE;
	}
}
