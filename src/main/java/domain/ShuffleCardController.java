package domain;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
public class ShuffleCardController implements CardController, ActionCardController {
	private final Deck deck;

	public ShuffleCardController(Deck deck) {
		this.deck = deck;
	}

	public TurnResult handleCardAction() {
		deck.shuffleDeck();
		return TurnResult.CONTINUE;
	}
}