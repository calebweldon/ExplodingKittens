package domain.cardcontroller;

import domain.Deck;
import domain.TurnResult;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import ui.ImplodingFaceUpCardView;
import ui.ShuffleCardView;

public class ShuffleCardController implements CardController, ActionCardController {
	private final ShuffleCardView view;
	@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
	private final Deck deck;

	public ShuffleCardController(ShuffleCardView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	public TurnResult handleCardAction() {
		deck.shuffleDeck();
		view.actionMessage();
		return TurnResult.CONTINUE;
	}

	public void getInfo() {
		this.view.getInfo();
	}
}