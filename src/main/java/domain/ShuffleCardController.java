package domain;

public class ShuffleCardController implements CardController {
	private Deck deck;

	public ShuffleCardController(Deck deck) {
		this.deck = deck;
	}

	public void handleCardAction() {
		deck.shuffleDeck();
	}
}
