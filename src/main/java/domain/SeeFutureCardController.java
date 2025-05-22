package domain;

import ui.CardView;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "Deck must be shared")
public class SeeFutureCardController implements CardController {
	private static final int NUM_CARDS_FROM_TOP = 3;

	private final CardView view;
	private final Deck deck;

	public SeeFutureCardController(CardView view, Deck deck) {
		this.view = view;
		this.deck = deck;
	}

	public TurnResult handleCardAction() {
		CardType[] topCards = new CardType[NUM_CARDS_FROM_TOP];
		
		for (int i = 0; i < NUM_CARDS_FROM_TOP; i++) {
			topCards[i] = deck.getCardAtIndex(i);
		}

		view.showTopCards(topCards);
		return TurnResult.CONTINUE;
	}
}
