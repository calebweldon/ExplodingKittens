package domain;

public class FlipCardController implements CardController {
	private final Deck deck;

	public FlipCardController(Deck deck) {
		this.deck = deck;
	}

	public void handleCardAction() {
		deck.flipDeck();
	}
}
