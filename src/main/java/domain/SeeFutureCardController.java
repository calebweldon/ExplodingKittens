package domain;

import ui.GameView;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
public class SeeFutureCardController implements CardController {
	private static final int NUM_CARDS_FROM_TOP = 3;

	private final GameView view;
	private final Deck deck;

	public SeeFutureCardController(GameView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	public void handleCardAction() {
		CardType[] topCards = new CardType[NUM_CARDS_FROM_TOP];
		
		for (int i = 0; i < NUM_CARDS_FROM_TOP; i++) {
			topCards[i] = deck.getCardAtIndex(i);
		}

		view.showTopCards(topCards);
	}
}
